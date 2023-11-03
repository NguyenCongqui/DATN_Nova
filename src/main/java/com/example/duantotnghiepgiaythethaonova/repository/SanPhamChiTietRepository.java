package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Primary

public interface SanPhamChiTietRepository extends JpaRepository<ChiTietSanPham, Integer> {
    @Query(value = "SELECT * FROM SanPhamCT c WHERE c.Daxoa = 0", nativeQuery = true)
    List<ChiTietSanPham> getLstChiTietSanPhamExist();

    @Query(value = "SELECT SoLuong FROM SanPhamCT WHERE IdSanPhamCT = :IdSanPhamCT AND Daxoa = 0", nativeQuery = true)
    BigDecimal soLuongSPCT(@Param("IdSanPhamCT") Integer id);

    @Query(value = "SELECT * FROM SanPhamCT s WHERE s.Daxoa = 0 AND s.IdSanPham = :sanPhamId ORDER BY s.IdMauSac", nativeQuery = true)
    List<ChiTietSanPham> getLstChiTietSanPhamBySanPhamId(@Param("sanPhamId") Integer id);

    @Query(value = "SELECT DISTINCT s.* FROM SanPhamCT s WHERE s.IdSanPham = :sanPhamId AND s.Daxoa = 0", nativeQuery = true)
    List<ChiTietSanPham> getLstChiTietSanPhamAddImg(@Param("sanPhamId") Integer id);

    @Query(value = "SELECT s1.* FROM SanPhamCT s1 where s1.Daxoa = 0 and s1.CoHienThi = 1 order by s1.IdSanPhamCT", nativeQuery = true)
    Page<ChiTietSanPham> getAllProductDetailIsShowTrue(Pageable pageable);

    @Query(value = "SELECT DISTINCT s.IdMauSac FROM SanPhamCT s WHERE s.IdSanPham = :sanPhamId AND s.Daxoa = 0 ORDER BY s.IdMauSac DESC", nativeQuery = true)
    List<Integer> getLstMauSacBySanPhamId(@Param("sanPhamId") Integer sanPhamId);

    @Query(value = "SELECT * FROM SanPhamCT s WHERE s.IdSanPham = :sanPhamId AND s.IdMauSac = :mauSacId AND s.IdKichCo = :kichCoId AND s.Daxoa = 0", nativeQuery = true)
    Optional<ChiTietSanPham> getChiTietSanPhamByMauSacSizeSanPhamId(@Param("sanPhamId") Integer sanPhamId, @Param("mauSacId") Integer mauSacId, @Param("kichCoId") Integer kichCoId);

    @Query(value = """
            SELECT spt.SoLuong FROM SanPhamCT spt
            			JOIN KichCo kc ON spt.IdKichCo = kc.IdKichCo
            			JOIN MauSac ms ON spt.IdMauSac = ms.IdMauSac
            			WHERE kc.TenKichCo = :tenKichCo AND ms.IdMauSac = :mauSacId AND spt.IdSanPham = :sanPhamId AND spt.CoHienThi = 1 AND spt.Daxoa = 0
            """,
            nativeQuery = true)
    Integer laySoLuongChiTietSanPham(@Param("tenKichCo") String tenKichCo,
                                     @Param("mauSacId") Integer mauSacId,
                                     @Param("sanPhamId") Integer sanPhamId);

    @Query(value = """
            			SELECT spt.SoLuong
            			FROM SanPhamCT spt
            	JOIN KichCo kc ON spt.IdKichCo = kc.IdKichCo
            			JOIN MauSac ms ON spt.IdMauSac = ms.IdMauSac
            			WHERE kc.IdKichCo = :kichCoId AND ms.IdMauSac = :mauSacId AND spt.IdSanPham = :sanPhamId  AND spt.CoHienThi = 1 AND spt.Daxoa = 0
            """,
            nativeQuery = true)
    Integer laySoLuongChiTietSanPham2(@Param("kichCoId") Integer tenKichCo,
                                      @Param("mauSacId") Integer mauSacId,
                                      @Param("sanPhamId") Integer sanPhamId);

    @Query(value = "SELECT count(*) from SanPhamCT spct where spct.IdMauSac = :mauSacId and spct.IdKichCo = :kichCoId and spct.IdSanPham = :sanPhamId AND spct.Daxoa = 0", nativeQuery = true)
    int selectCountChiTietSanPhamDuplicate(@Param("mauSacId") Integer mauSacId, @Param("kichCoId") Integer kichCoId, @Param("sanPhamId") Integer sanPhamId);

    @Query(value = "SELECT * from SanPhamCT spct where spct.IdMauSac = :mauSacId and spct.IdKichCo = :kichCoId and spct.IdSanPham = :sanPhamId AND spct.Daxoa = 0", nativeQuery = true)
    Optional<ChiTietSanPham> selectChiTietSanPhamDuplicate(@Param("mauSacId") Integer mauSacId, @Param("kichCoId") Integer kichCoId, @Param("sanPhamId") Integer sanPhamId);

    @Query(value = "SELECT count(*) FROM SanPhamCT WHERE IdSanPham = :sanPhamId AND IdMauSac = :mauSacId AND Daxoa = 0", nativeQuery = true)
    int getCountChiTietSanPhamExistBySanPhamIdAndMauSacId(@Param("sanPhamId") Integer sanPhamId, @Param("mauSacId") Integer mauSacId);

    @Query(value = "SELECT count(*) FROM SanPhamCT WHERE IdSanPham = :sanPhamId AND Daxoa = 0", nativeQuery = true)
    int getCountChiTietSanPhamExistBySanPhamId(@Param("sanPhamId") Integer sanPhamId);

    @Query("""
            select new com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO(sp.idCTSP, sum(hd.soLuong))
            			from ChiTietSanPham sp
            			join HoaDonChiTiet hd on sp = hd.chiTietSanPham
            			where hd.hoaDon.idHoaDon in (:listHoaDon)
            			group by sp.idCTSP
            			order by sum(hd.soLuong) desc
            """)
    List<BestSellerDTO> layIdChiTietSanPhamBanChay(List<Integer> listHoaDon, Pageable pageable);

    @Query("select sp from ChiTietSanPham sp where sp.id in (:ids) order by field(sp.id, :ids)")
    List<ChiTietSanPham> layChiTietSanPhamBanChay(List<Integer> ids);

//    @Query(value = "SELECT sp.* FROM SanPhamChiTiet sp " +
//            "WHERE sp.id IN (:ids) " +
//            "ORDER BY " +
//            "CASE sp.id " +
//            "WHEN :ids[0] THEN 0 " +
//            "WHEN :ids[1] THEN 1 " +
//            "WHEN :ids[2] THEN 2 " +
//            "ELSE 3 " +
//            "END", nativeQuery = true)
//    List<ChiTietSanPham> layChiTietSanPhamBanChay(@Param("ids") List<Integer> ids);


    @Query(value = "SELECT COALESCE(sum(spct.SoLuong),0) FROM SanPhamCT spct WHERE spct.IdSanPham = :sanPhamId AND spct.Daxoa = 0", nativeQuery = true)
    int getSumSoLuongBySanPhamId(@Param("sanPhamId") Integer id);

}	
