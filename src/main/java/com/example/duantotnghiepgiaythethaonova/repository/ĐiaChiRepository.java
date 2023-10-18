package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ĐiaChiRepository extends JpaRepository<DiaChi, Integer> {


    @Query(value = "SELECT count(*) FROM DiaChi WHERE idKhachHang=:idKhachHang", nativeQuery = true)
    int countByMaKhachHang(@Param("idKhachHang") Integer idKhachHang);

    @Query(value = "SELECT * FROM DiaChi  WHERE idKhachHang=:idKhachHang", nativeQuery = true)
    Page<DiaChi> findAllByMaKhachHang(@Param("idKhachHang") Integer idKhachHang, Pageable pageale);

    @Query(value = "select * from DiaChi where idKhachHang = ? and laDiaChiMacDinh = true ", nativeQuery = true)
    DiaChi findDiaChiByKhachHang(Integer id);
}
