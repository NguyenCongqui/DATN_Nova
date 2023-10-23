package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotGiayRepository extends JpaRepository<LotGiay,Integer> {
    @Query(value = "SELECT * FROM LotGiay c WHERE  c.DaXoa = 0 ORDER BY c.IdLotGiay DESC ",nativeQuery = true)
    Page<LotGiay> selectAllKichCoExist(Pageable pageable);

    @Query(value = "SELECT * FROM LotGiay c WHERE  c.DaXoa = 0 ORDER BY c.IdLotGiay DESC ",nativeQuery = true)
    List<LotGiay> selectAllKichCoExist();

    @Query(value = "SELECT * FROM LotGiay c WHERE  c.DaXoa = 0 AND c.TenLotGiay like %:TenLotGiay% ORDER BY c.IdLotGiay DESC ",nativeQuery = true)
    Page<LotGiay> getKichCoExistByName(@Param("TenLotGiay") String tenKichCo, Pageable pageable);

    @Query(value = "SELECT  c.* FROM LotGiay c join SanPhamCT s1 on s1.IdLotGiay = c.IdLotGiay WHERE c.DaXoa = false and s1.IdSanPham = :IdSanPham group by c.IdLotGiay", nativeQuery = true)
    List<LotGiay> selectAllKichCoBySanPhamId(@Param("IdSanPham") Integer sanPhamId);
}
