package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import com.example.duantotnghiepgiaythethaonova.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, Integer> {


    @Query(value = "SELECT * FROM ThuongHieu c WHERE  c.DaXoa = 0 ORDER BY c.IdThuongHieu DESC ",nativeQuery = true)
    Page<ThuongHieu> selectAllChatLieuExist(Pageable pageable);

    @Query(value = "SELECT * FROM ThuongHieu c WHERE  c.DaXoa = 0 ORDER BY c.IdThuongHieu DESC ",nativeQuery = true)
    List<ThuongHieu> selectAllChatLieuExist();

    @Query(value = "SELECT * FROM ThuongHieu c WHERE  c.DaXoa = 0 AND c.TenThuongHieu like %:TenThuongHieu% ORDER BY c.IdThuongHieu DESC ",nativeQuery = true)
    Page<ThuongHieu> getChatLieuExistByName(@Param("TenThuongHieu") String tenThuongHieu, Pageable pageable);
}
