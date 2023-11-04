package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon,Integer> {
    @Query(value = "select * from LichSuHoaDon where idLichSuHoaDon = ?",nativeQuery = true)
    List<LichSuHoaDon> findLichSuByHDID(Integer hoaDonId);
}
