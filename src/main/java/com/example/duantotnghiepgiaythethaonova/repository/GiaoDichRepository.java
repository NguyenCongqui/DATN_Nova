package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich , Integer> {

    @Query(value = "select * from GiaoDich where trang_thai_id = ? and hoa_don_id = ? ORDER BY id DESC LIMIT 1", nativeQuery = true)
    List<GiaoDich> findByTrangThaiIdAndHoaDonId(int trangThai, Integer hoaDonId);
}
