package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import com.example.duantotnghiepgiaythethaonova.entity.DeGiay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeGiayRepository extends JpaRepository<DeGiay,Integer> {

    @Query(value = "SELECT * FROM DeGiay c WHERE  c.DaXoa = 0 ORDER BY c.IdDeGiay DESC ",nativeQuery = true)
    Page<DeGiay> selectAllChatLieuExist(Pageable pageable);

    @Query(value = "SELECT * FROM DeGiay c WHERE  c.DaXoa = 0 ORDER BY c.IdDeGiay DESC ",nativeQuery = true)
    List<DeGiay> selectAllChatLieuExist();

    @Query(value = "SELECT * FROM DeGiay c WHERE  c.DaXoa = 0 AND c.TenDeGiay like %:TenDeGiay% ORDER BY c.IdDeGiay DESC ",nativeQuery = true)
    Page<DeGiay> getChatLieuExistByName(@Param("TenDeGiay") String tenDeGiay, Pageable pageable);
}
