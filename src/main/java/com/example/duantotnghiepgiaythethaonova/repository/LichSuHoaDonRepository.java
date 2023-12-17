package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.GiaoDich;
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

    @Query(value = "SELECT TOP 1 * FROM LichSuHoaDon WHERE IdHoaDon = ?1 AND trangThai_id = ?2 ORDER BY IdLichSuHoaDon DESC", nativeQuery = true)
    List<LichSuHoaDon> getTimeLine(Integer idHoaDon, Integer trangThai_id);

//    @Query(value = "SELECT TOP 1 * FROM LichSuHoaDon WHERE trangThai_id = ?1 AND idHoaDon = ?2 ORDER BY idLichSuHoaDon DESC", nativeQuery = true)
//    List<LichSuHoaDon> findByTrangThaiIdAndHoaDonId(Integer trangThai_id, Integer hoaDonId);

}
