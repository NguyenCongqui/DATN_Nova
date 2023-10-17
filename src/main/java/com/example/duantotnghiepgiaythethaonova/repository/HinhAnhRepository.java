package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Integer> {
    @Query(value = """
            SELECT ha.* FROM dbo.HinhAnh ha LEFT JOIN dbo.MauSac ms
            ON ms.IdMauSac = ha.IdMauSac LEFT JOIN dbo.SanPham sp
            ON sp.IdSanPham = ha.IdSanPham WHERE ha.IdSanPham = :sanPhamId
            ORDER BY ha.IdMauSac DESC
            """, nativeQuery = true)
    List<HinhAnh> getLstHinhAnhMauSacBySanPhamId(@Param("sanPhamId") Integer sanPhamId);

    @Query(value = """
            SELECT DISTINCT (ha.IdMauSac) FROM dbo.HinhAnh ha LEFT JOIN dbo.MauSac ms
            ON ms.IdMauSac = ha.IdMauSac LEFT JOIN dbo.SanPham sp
            ON sp.IdSanPham = ha.IdSanPham WHERE ha.IdSanPham = :sanPhamId
            ORDER BY ha.IdMauSac DESC
            """, nativeQuery = true)
    List<Integer> getDistinctMauSacInHinhAnhBySanPhamId(@Param("sanPhamId") Integer sanPhamId);

    @Query(value = """
            SELECT ha.*FROM dbo.HinhAnh ha JOIN dbo.SanPham sp
            ON sp.IdSanPham = ha.IdSanPham WHERE ha.LaAnhChinh = true
            AND ha.CoHienThi = true AND sp.DaXoa = false
            """, nativeQuery = true)
    List<HinhAnh> getHinhAnhChinhExist();

    @Query(value = "SELECT ha.* FROM dbo.HinhAnh ha WHERE ha.TenAnh = tenAnh LIMIT 1", nativeQuery = true)
    Optional<HinhAnh> getHinhAnhByName(@Param("tenAnh") String tenAnh);

    @Query(value = "SELECT ha.* FROM dbo.HinhAnh ha WHERE ha.IdSanPham = :sanPhamId \n" +
            "AND ha.IdMauSac =:mauSacId AND ha.LaAnhChinh = true LIMIT 1 ", nativeQuery = true)
    Optional<HinhAnh> getHinhAnhChinhBySanPhamIdAndMauSacId(@Param("sanPhamId") Integer sanPhamId, @Param("mauSacId") Integer mauSacId);

    @Query(value = "SELECT count(*) FROM dbo.HinhAnh ha WHERE \n" +
            "ha.IdSanPham = :sanPhamId AND \n" +
            "ha.IdMauSac =:mauSacId AND ha.LaAnhChinh = true", nativeQuery = true)
    int getCountHinhAnhChinhBySanPhamIdAndMauSacId(@Param("sanPhamId") Integer sanPhamId, @Param("mauSacId") Integer mauSacId);

    @Query(value = "SELECT ha.* FROM dbo.HinhAnh ha WHERE ha.\n" +
            "IdSanPham = :sanPhamId AND ha.CoHienThi = \n" +
            "true AND ha.LaAnhChinh = true\n" +
            "AND ha.IdMauSac IN (:mauSacIds) ORDER BY ha.IdMauSac DESC", nativeQuery = true)
    List<HinhAnh> getHinhAnhChinhBySanPhamIdAndMauSacIds(@Param("sanPhamId") Integer sanPhamId, @Param("mauSacIds") List<Integer> mauSacIds);

    @Query(value = """
            SELECT ha.* FROM dbo.HinhAnh ha WHERE ha.
            IdSanPham = :sanPhamId AND ha.CoHienThi =\s
            true AND ha.IdMauSac IN (:mauSacIds) ORDER BY ha.IdMauSac DESC
            """, nativeQuery = true)
    List<HinhAnh> getHinhAnhBySanPhamIdAndMauSacIds(@Param("sanPhamId") Integer sanPhamId, @Param("mauSacIds") List<Integer> mauSacIds);

    @Query(value = "SELECT ha.* FROM dbo.HinhAnh ha WHERE ha.IdSanPham = :sanPhamId AND ha.LaAnhChinh = true", nativeQuery = true)
    List<HinhAnh> getHinhAnhChinhBySanPhamId(@Param("sanPhamId") Integer sanPhamId);

    @Query(value = "SELECT ha.* FROM dbo.HinhAnh ha WHERE ha.IdSanPham = :sanPhamId", nativeQuery = true)
    List<HinhAnh> getHinhAnhBySanPhamId(@Param("sanPhamId") Integer sanPhamId);

    @Query(value = "SELECT ha.* FROM dbo.HinhAnh ha WHERE ha.IdSanPham = :sanPhamId AND ha.IdMauSac = :mauSacId ORDER BY\n" +
            "ha.IdHinhAnh DESC", nativeQuery = true)
    List<HinhAnh> getHinhAnhBySanPhamIdAndMauSacId(@Param("sanPhamId") Integer sanPhamId, @Param("mauSacId") Integer mauSacId);

    @Query(value = "SELECT ha.* FROM dbo.HinhAnh ha WHERE ha.IdMauSac = :mauSacId AND\n" +
            "ha.IdSanPham = :sanPhamId AND ha.LaAnhChinh = true", nativeQuery = true)
    HinhAnh findByHinhAnhByMauSacIdVaLaAnhChinh(@Param("mauSacId") Integer mauSacId, @Param("sanPhamId") Integer sanPhamId);

    @Query(value = "SELECT ten_anh FROM hinh_anh WHERE mau_sac_id = ?1 AND san_pham_id = ?2 AND la_anh_chinh = true", nativeQuery = true)
    String findTenAnhChinhByMauSacIdAndSanPhamId(Integer mauSacId, Integer sanPhamId);

    @Query(value = "SELECT count(*) from hinh_anh  WHERE san_pham_id = :sanPhamId", nativeQuery = true)
    int getCountHinhAnhBySanPhamId(@Param("sanPhamId") Integer sanPhamId);

    @Query(value = "SELECT sum(9 + (SELECT count(distinct(mau_sac_id)) from san_pham_chi_tiet WHERE san_pham_id = :sanPhamId AND da_xoa = false)) as 'countHinhAnhToiDaDuocThem'", nativeQuery = true)
    int getCountHinhAnhChoPhepThemBySanPhamId(@Param("sanPhamId") Integer sanPhamId);

}

