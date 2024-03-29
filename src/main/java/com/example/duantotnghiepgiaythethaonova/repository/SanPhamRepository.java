package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface SanPhamRepository extends JpaRepository<SanPham, Integer>, SanPhamSearchRepository{
    @Query(value = "SELECT * FROM dbo.SanPham sp WHERE sp.DaXoa = 0 ORDER BY sp.IdSanPham", nativeQuery = true)
    Page<SanPham> getSanPhamExist(Pageable pageable);

    @Query(value = "select MAX(idSanPham) from SanPham", nativeQuery = true)
    Integer getMaxId();

    @Query(value = """
SELECT sp.*
FROM dbo.SanPham sp
JOIN dbo.SanPhamCT spct ON spct.IdSanPham = sp.IdSanPham
WHERE sp.DaXoa = 0 AND spct.CoHienThi = 1
GROUP BY sp.IdSanPham,sp.MaSanPham, sp.TenSanPham, sp.NgayCapNhat, sp.NgayTao, sp.NguoiCapNhat, sp.NguoiTao, sp.DaXoa, sp.Gia, sp.MoTa, sp.IdChatLieu, sp.IdKieuDang, sp.IdThuongHieu
ORDER BY sp.IdSanPham DESC
""", nativeQuery = true)
    Page<SanPham> showSanPhamExistHomePage(Pageable pageable);

    @Query(value = """
            SELECT count(*) FROM dbo.SanPham sp LEFT JOIN dbo.ThuongHieu th ON th.IdThuongHieu = sp.IdThuongHieu
            WHERE sp.IdThuongHieu = :thuongHieuId AND th.DaXoa = 0 AND sp.DaXoa=0
            """, nativeQuery = true)
    int selectCountSanPhamByThuongHieuId(@Param("thuongHieuId") Integer thuongHieuId);

    @Query(value = """
            SELECT COUNT(*) FROM dbo.SanPham sp LEFT JOIN dbo.KieuDang kd ON kd.IdKieuDang = sp.IdKieuDang
            WHERE sp.IdKieuDang = :kieuDangId AND kd.DaXoa = 0 AND sp.DaXoa = 0
            """, nativeQuery = true)
    int selectCountSanPhamByKieuDangId(@Param("kieuDangId") Integer kieuDangId);

    @Query(value = """
            select count(*) from SanPham s left join ChatLieu p 
            on s.IdChatLieu = p.IdChatLieu where s.IdChatLieu = :IdChatLieu and p.DaXoa = 0 and s.DaXoa = 0
            """,
            nativeQuery = true)
    int selectCountSanPhamByChatLieuId(@Param("IdChatLieu") Integer chatLieuId);
}
