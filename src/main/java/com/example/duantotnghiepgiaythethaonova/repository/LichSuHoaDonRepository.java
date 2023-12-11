package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.LichSuHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, Integer> {
    @Query(value = "select * from LichSuHoaDon where idHoaDon = ?", nativeQuery = true)
    List<LichSuHoaDon> findLichSuByHDID(Integer hoaDonId);

    @Query(value = "select * from LichSuHoaDon where IdHoaDon = ?1 and trangThai_id = ?2", nativeQuery = true)
    List<LichSuHoaDon> getTimeLine(Integer idHoaDon, Integer trangThai_id);

}
