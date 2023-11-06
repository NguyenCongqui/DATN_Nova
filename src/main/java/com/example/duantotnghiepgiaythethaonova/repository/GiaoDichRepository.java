package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiaoDichRepository extends JpaRepository<GiaoDich , Integer> {

    @Query(value = "select TOP 1 * from GiaoDich where idtrangthai = ? and idhoadon = ? ORDER BY idgiaodich" +
            " DESC", nativeQuery = true)
    List<GiaoDich> findByTrangThaiIdAndHoaDonId(int trangThai, Integer hoaDonId);
}
