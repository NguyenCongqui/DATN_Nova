package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich , Integer> {

    @Query(value = "SELECT TOP 1 * FROM GiaoDich WHERE idTrangThai = ? AND idHoaDon = ? ORDER BY idGiaoDich DESC", nativeQuery = true)
    List<GiaoDich> findByTrangThaiIdAndHoaDonId(int trangThai, Integer hoaDonId);
}
