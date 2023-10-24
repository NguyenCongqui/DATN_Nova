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
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    @Query(value = "SELECT * FROM dbo.SanPham sp WHERE sp.DaXoa = false ORDER BY sp.IdSanPham", nativeQuery = true)
    Page<SanPham> getSanPhamExist(Pageable pageable);

    @Query(value = """
            SELECT sp.* FROM dbo.SanPham sp JOIN dbo.SanPhamCT spct ON
            spct.IdSanPham = sp.IdSanPham WHERE sp.DaXoa = false AND spct.CoHienThi = true
            GROUP BY sp.IdSanPham ORDER BY sp.IdSanPham DESC
            """, nativeQuery = true)
    Page<SanPham> showSanPhamExistHomePage(Pageable pageable);

    @Query(value = """
SELECT sp.* FROM dbo.SanPham sp LEFT JOIN dbo.DanhMuc DM ON DM.IdDanhMuc = sp.IdDanhMuc
WHERE sp.IdDanhMuc = :loaiSanPhamId AND dm.DaXoa = false AND sp.DaXoa = false
""", nativeQuery = true)
    int selectCountSanPhamByLoaiSanPhamId(@Param("loaiSanPhamId") Integer loaiSanPhamId);

    @Query(value = """
SELECT sp.* FROM dbo.SanPham sp LEFT JOIN dbo.ThuongHieu th
ON th.IdThuongHieu = sp.IdThuongHieu WHERE sp.IdSanPham = :phongCachId
AND th.DaXoa = false AND sp.DaXoa = false
""", nativeQuery = true)
    int selectCountSanPhamByPhongCachId(@Param("phongCachId") Integer phongCachId);

    @Query(value = """
select count(*) from SanPham s left join ChatLieu p 
on s.IdChatLieu = p.IdChatLieu where s.IdChatLieu = :IdChatLieu and p.DaXoa = 0 and s.DaXoa = 0
""",
            nativeQuery = true)
    int selectCountSanPhamByChatLieuId(@Param("IdChatLieu") Integer chatLieuId);
}
