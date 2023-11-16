package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Integer> {

    @Query(value = "SELECT * FROM MauSac c WHERE c.DaXoa = 0 ORDER BY c.IdMauSac DESC", nativeQuery = true)
    List<MauSac> selectAllMauSacExist();

    @Query(value = "SELECT * FROM MauSac c WHERE c.DaXoa = 0 ORDER BY c.IdMauSac DESC", nativeQuery = true)
    Page<MauSac> selectAllMauSacExist(Pageable page);

    @Query(value = "SELECT * FROM MauSac c WHERE c.DaXoa = 0 AND c.TenMauSac like %:TenMauSac% ORDER BY c.IdMauSac DESC", nativeQuery = true)
    Page<MauSac> getMauSacExistByName(@Param("TenMauSac") String tenMauSac, Pageable page);

    @Query(value = "SELECT DISTINCT m.* FROM MauSac m LEFT JOIN SanPhamCT s ON m.IdMauSac = s.IdMauSac WHERE s.IdSanPham = :spId AND s.DaXoa = 0 ORDER BY m.IdMauSac DESC", nativeQuery = true)
    List<MauSac> getAllMauSacExistBySPId(@Param("spId") Integer spId);

    @Query(value = "SELECT pc.MaMauSac AS color_name FROM SanPhamCT pd " +
            "JOIN MauSac pc ON pd.IdMauSac = pc.IdMauSac WHERE pd.IdSanPham = :IdSanPham" +
            " GROUP BY pc.MaMauSac, pd.IdMauSac",nativeQuery = true)
    List<String> getMauSauBySanPhamId(@Param("IdSanPham") Integer idSanPham);

    @Query(value = "select * from MauSac where MaMauSac = ?", nativeQuery = true)
    Optional<MauSac> finMauSacByMa(String MaMauSac);

}
