package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
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
    Page<LotGiay> selectAllChatLieuExist(Pageable pageable);

    @Query(value = "SELECT * FROM LotGiay c WHERE  c.DaXoa = 0 ORDER BY c.IdLotGiay DESC ",nativeQuery = true)
    List<LotGiay> selectAllChatLieuExist();

    @Query(value = "SELECT * FROM LotGiay c WHERE  c.DaXoa = 0 AND c.TenLotGiay like %:TenLotGiay% ORDER BY c.IdLotGiay DESC ",nativeQuery = true)
    Page<LotGiay> getChatLieuExistByName(@Param("TenLotGiay") String tenLotGiay, Pageable pageable);
}
