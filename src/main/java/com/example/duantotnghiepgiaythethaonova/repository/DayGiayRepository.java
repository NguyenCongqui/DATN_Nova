package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayGiayRepository extends JpaRepository<DayGiay,Integer> {
    @Query(value = "SELECT * FROM DayGiay c WHERE  c.DaXoa = 0 ORDER BY c.IdDayGiay DESC ",nativeQuery = true)
    Page<DayGiay> selectAllKichCoExist(Pageable pageable);

    @Query(value = "SELECT * FROM DayGiay c WHERE  c.DaXoa = 0 ORDER BY c.IdDayGiay DESC ",nativeQuery = true)
    List<DayGiay> selectAllKichCoExist();

    @Query(value = "SELECT * FROM DayGiay c WHERE  c.DaXoa = 0 AND c.TenDayGiay like %:TenDayGiay% ORDER BY c.IdDayGiay DESC ",nativeQuery = true)
    Page<DayGiay> getKichCoExistByName(@Param("TenDayGiay") String tenKichCo, Pageable pageable);

    @Query(value = "SELECT  c.* FROM DayGiay c join SanPhamCT s1 on s1.IdDayGiay = c.IdDayGiay WHERE c.DaXoa = false and s1.IdSanPham = :IdSanPham group by c.IdDayGiay", nativeQuery = true)
    List<DayGiay> selectAllKichCoBySanPhamId(@Param("IdSanPham") Integer sanPhamId);
}
