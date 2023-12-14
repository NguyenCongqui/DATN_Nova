package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.service.SanPhamChiTietSearchRepository;
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

    @Query(value = " SELECT spt.SoLuong\n" +
            "FROM SanPhamCT spt\n" +
            			"JOIN KichCo kc ON spt.IdKichCo = kc.IdKichCo\n" +
            			"JOIN MauSac ms ON spt.IdMauSac = ms.IdMauSac\n" +
            			"WHERE kc.TenKichCo = :tenKichCo AND ms.IdMauSac = :mauSacId AND spt.IdSanPham = :sanPhamId AND spt.CoHienThi = 1 AND spt.Daxoa = 0\n",

            nativeQuery = true)
    Integer laySoLuongChiTietSanPham(@Param("tenKichCo") String tenKichCo,
                                     @Param("mauSacId") Integer mauSacId,
                                     @Param("sanPhamId") Integer sanPhamId);


    @Query(value = """
SELECT spt.Gia FROM dbo.SanPhamCT spt JOIN dbo.KichCo kc ON kc.IdKichCo = spt.IdKichCo
			JOIN dbo.MauSac ms ON ms.IdMauSac = spt.IdMauSac
			WHERE kc.TenKichCo = :tenKichCo AND ms.IdMauSac = :mauSacId
			AND spt.IdSanPham = :sanPhamId AND spt.CoHienThi = 1 AND spt.DaXoa = 0
""",
            nativeQuery = true)
    BigDecimal layGiaBanSanPhamChiTiet(@Param("tenKichCo") String tenKichCo,
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
            select new com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO(sp.idCTSP, sum(hct.soLuong))
            			from ChiTietSanPham sp
            			JOIN HoaDon hd ON sp.idCTSP = hd.idHoaDon
                        JOIN HoaDonChiTiet hct ON hct.idHoaDonCT = hd.idHoaDon
            			group by sp.idCTSP
            			order by sum(hct.soLuong) desc
            """)
//    @Query(value = "SELECT spct.IdSanPhamCT, ha.LaAnhChinh, sp.TenSanPham, spct.IdKichCo, spct.IdMauSac, sp.Gia , sum(hct.DonGia * hct.SoLuong) as DoanhThu\n" +
//            "FROM SanPhamCT spct\n" +
//            "    JOIN HoaDon hd ON spct.IdSanPhamCT = hd.IdHoaDon\n" +
//            "    JOIN HoaDonCT hct ON hd.IdHoaDon = hct.IdHoaDonCT\n" +
//            "    JOIN SanPham sp ON spct.IdSanPham = sp.IdSanPham\n" +
//            "    JOIN HinhAnh ha ON sp.IdSanPham = ha.IdSanPham\n" +
//            "GROUP BY spct.IdSanPhamCT, ha.LaAnhChinh, sp.Gia, spct.IdKichCo, sp.TenSanPham, spct.IdMauSac\n" +
//            "order by sum(hct.DonGia * hct.SoLuong) desc", nativeQuery = true)
    List<BestSellerDTO> layIdChiTietSanPhamBanChay(List<Integer> listHoaDon, Pageable pageable);
//
    @Query("select sp from ChiTietSanPham sp where sp.idCTSP in (:ids) order by sp.idCTSP")
    List<ChiTietSanPham> layChiTietSanPhamBanChay(List<Integer> ids);

//    @Query("SELECT sp \n" +
//            "FROM ChiTietSanPham sp \n" +
//            "WHERE sp.idCTSP IN (:ids) \n" +
//            "ORDER BY \n" +
//            "    CASE sp.idCTSP\n" +
//            "        WHEN :id1 THEN 1\n" +
//            "        WHEN :id2 THEN 2\n" +
//            "        ELSE NULL\n" +
//            "    END\n")
//    List<ChiTietSanPham> layChiTietSanPhamBanChay(List<Integer> ids);


    @Query(value = "SELECT COALESCE(sum(spct.SoLuong),0) FROM SanPhamCT spct WHERE spct.IdSanPham = :sanPhamId AND spct.Daxoa = 0", nativeQuery = true)
    int getSumSoLuongBySanPhamId(@Param("sanPhamId") Integer id);

    @Query(value = "SELECT max(Gia) FROM SanPhamCT spct WHERE spct.IdSanPham = :sanPhamId AND spct.Daxoa = 0", nativeQuery = true)
    BigDecimal getTienBan(@Param("sanPhamId") Integer id);

    @Query(value = "select SoLuong from  SanPhamCT where IdMauSac = ?1 and IdKichCo = ?2 and IdSanPham = ?3", nativeQuery = true)
    Integer getSoLuong(Integer IdMauSac, Integer IdKichCo, Integer IdSanPham);
}	
