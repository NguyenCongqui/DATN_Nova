package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
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
    Page<DayGiay> selectAllChatLieuExist(Pageable pageable);

    @Query(value = "SELECT * FROM DayGiay c WHERE  c.DaXoa = 0 ORDER BY c.IdDayGiay DESC ",nativeQuery = true)
    List<DayGiay> selectAllChatLieuExist();

    @Query(value = "SELECT * FROM DayGiay c WHERE  c.DaXoa = 0 AND c.TenDayGiay like %:TenDayGiay% ORDER BY c.IdDayGiay DESC ",nativeQuery = true)
    Page<DayGiay> getChatLieuExistByName(@Param("TenDayGiay") String tenDayGiay, Pageable pageable);


}
