package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.service.SanPhamChiTietSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CTSPRepository extends JpaRepository<ChiTietSanPham, Integer>, SanPhamChiTietSearchRepository {
    @Query(value = "SELECT * FROM SanPhamCT c WHERE c.DaXoa = 0", nativeQuery = true)
    List<ChiTietSanPham> getLstSanPhamChiTietExist();

    @Query(value = "SELECT SoLuong FROM SanPhamCT WHERE IdSanPhamCT = :id AND DaXoa = 0", nativeQuery = true)
    BigDecimal soLuongSPCT(@Param("id") Integer id);

    @Query(value = "SELECT * FROM SanPhamCT s WHERE s.DaXoa = 0 AND s.IdSanPham = :id ORDER BY s.IdMauSac", nativeQuery = true)
    List<ChiTietSanPham> getLstSanPhamChiTietBySanPhamId(@Param("id") Integer id);

    @Query(value = "SELECT DISTINCT s.* FROM SanPhamCT s WHERE s.IdSanPham = :id AND s.DaXoa = 0", nativeQuery = true)
    List<ChiTietSanPham> getLstSanPhamChiTietAddImg(@Param("id") Integer id);

    @Query(value = "SELECT s1.* FROM SanPhamCT s1 where s1.DaXoa = 0 and s1.CoHienThi = 1 order by s1.IdSanPhamCT", nativeQuery = true)
    Page<ChiTietSanPham> getAllProductDetailIsShowTrue(Pageable pageable);

    @Query(value = "SELECT DISTINCT s.IdMauSac FROM SanPhamCT s WHERE s.IdSanPham = :id AND s.DaXoa = 0 ORDER BY s.IdMauSac DESC", nativeQuery = true)
    List<Integer> getLstMauSacBySanPhamId(@Param("id") Integer sanPhamId);

    @Query(value = "SELECT * FROM SanPhamCT s WHERE s.IdSanPham = :IdSanPham AND s.IdMauSac = :IdMauSac AND s.IdKichCo = :IdKichCo AND s.DaXoa = 0", nativeQuery = true)
    Optional<ChiTietSanPham> getSanPhamChiTietByMauSacSizeSanPhamId(@Param("IdSanPham") Integer sanPhamId, @Param("IdMauSac") Integer mauSacId, @Param("IdKichCo") Integer kichCoId);

    @Query(value = "SELECT spt.SoLuong\n" +
            "FROM SanPhamCT spt\n" +
            "JOIN KichCo kc ON spt.IdKichCo = kc.IdKichCo\n" +
            "JOIN MauSac ms ON spt.IdMauSac = ms.IdMauSac\n" +
            "WHERE kc.TenKichCo = :TenKichCo AND ms.IdMauSac = :IdMauSac AND spt.IdSanPham = :IdSanPham AND spt.CoHienThi = 1 AND spt.DaXoa = 0\n",
            nativeQuery = true)
    Integer laySoLuongSanPhamChiTiet(@Param("TenKichCo") String tenKichCo,
                                     @Param("IdMauSac") Integer mauSacId,
                                     @Param("IdSanPham") Integer sanPhamId);

    @Query(value = "SELECT spt.SoLuong\n" +
            "FROM SanPhamCT spt\n" +
            "JOIN KichCo kc ON spt.IdKichCo = kc.IdKichCo\n" +
            "JOIN MauSac ms ON spt.IdMauSac = ms.IdMauSac\n" +
            "WHERE kc.IdKichCo = :IdKichCo AND ms.MauSac = :IdMauSac AND spt.IdSanPham = :IdSanPham AND spt.CoHienThi = 1 AND spt.DaXoa = 0\n",
            nativeQuery = true)
    Integer laySoLuongSanPhamChiTiet2(@Param("IdKichCo") Integer tenKichCo,
                                      @Param("IdMauSac") Integer mauSacId,
                                      @Param("IdSanPham") Integer sanPhamId);

    @Query(value = "SELECT count(*) from SanPhamCT spct where spct.IdMauSac = :IdMauSac and spct.IdKichCo = :IdKichCo and spct.IdSanPham = :IdSanPham AND spct.DaXoa = 0", nativeQuery = true)
    int selectCountSanPhamChiTietDuplicate(@Param("IdMauSac") Integer mauSacId, @Param("IdKichCo") Integer kichCoId, @Param("IdSanPham") Integer sanPhamId);

    @Query(value = "SELECT * from SanPhamCT spct where spct.IdMauSac = :IdMauSac and spct.IdKichCo = :IdKichCo and spct.IdSanPham = :IdSanPham AND spct.DaXoa = 0", nativeQuery = true)
    Optional<ChiTietSanPham> selectSanPhamChiTietDuplicate(@Param("IdMauSac") Integer mauSacId, @Param("IdKichCo") Integer kichCoId, @Param("IdSanPham") Integer sanPhamId);

    @Query(value="SELECT count(*) FROM SanPhamCT WHERE IdSanPham = :IdSanPham AND IdMauSac = :IdMauSac AND DaXoa = 0", nativeQuery = true)
    int getCountSanPhamChiTietExistBySanPhamIdAndMauSacId(@Param("IdSanPham") Integer sanPhamId, @Param("IdMauSac") Integer mauSacId);

    @Query(value="SELECT count(*) FROM SanPhamCT WHERE IdSanPham = :IdSanPham AND DaXoa = 0", nativeQuery = true)
    int getCountSanPhamChiTietExistBySanPhamId(@Param("IdSanPham") Integer sanPhamId);

    @Query("""
            select new com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO(sp.idCTSP, sum(hd.soLuong))
            from ChiTietSanPham sp
            join HoaDonChiTiet hd on sp = hd.chiTietSanPham
            where hd.hoaDon.idHoaDon in (:listHoaDon)
            group by sp.idCTSP
            order by sum(hd.soLuong) desc
            """)
    List<BestSellerDTO> layIdSanPhamChiTietBanChay(List<Integer> listHoaDon, Pageable pageable);

    @Query("select sp from ChiTietSanPham sp where sp.idCTSP in (:ids) order by field(sp.id, :ids)")
    List<ChiTietSanPham> laySanPhamChiTietBanChay(List<Integer> ids);

    @Query(value="SELECT COALESCE(sum(spct.SoLuong),0) FROM SanPhamCT spct WHERE spct.IdSanPham = :IdSanPham AND spct.DaXoa = 0", nativeQuery = true)
    int getSumSoLuongBySanPhamId(@Param("IdSanPham") Integer id);
}
