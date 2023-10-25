package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDung_VaiTroRepository extends JpaRepository<NguoiDung_VaiTro, Integer> {

    @Query(value = "select * from NguoiDung_VaiTro where idNguoiDung_VaiTro = ?", nativeQuery = true)
    NguoiDung_VaiTro findByNguoiDungId(Integer id);
}
