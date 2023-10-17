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

    @Query(value = """"
SELECT sp.* FROM dbo.SanPham sp LEFT JOIN dbo.DanhMuc DM ON DM.IdDanhMuc = sp.IdDanhMuc
WHERE sp.IdDanhMuc = :loaiSanPhamId AND dm.DaXoa = false AND sp.DaXoa = false
""", nativeQuery = true)
    int selectCountSanPhamByLoaiSanPhamId(@Param("loaiSanPhamId") Long loaiSanPhamId);

    @Query(value = "", nativeQuery = true)
    int selectCountSanPhamByPhongCachId(@Param("phongCachId") Long phongCachId);

    @Query(value = "select count(*) from `san_pham` s left join `chat_lieu` p on s.chat_lieu_id = p.id where s.chat_lieu_id = :chatLieuId and p.da_xoa = false and s.da_xoa = false", nativeQuery = true)
    int selectCountSanPhamByChatLieuId(@Param("chatLieuId") Long chatLieuId);
}
