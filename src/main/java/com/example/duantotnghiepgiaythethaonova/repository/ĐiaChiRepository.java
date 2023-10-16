package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ĐiaChiRepository extends JpaRepository<DiaChi , Integer> {


    @Query(value="SELECT count(*) FROM DiaChi WHERE IdKhachHang=:IdKhachHang",nativeQuery=true)
    int countByMaKhachHang(@Param("IdKhachHang")Integer IdKhachHang);

    @Query(value="SELECT * FROM DiaChi  WHERE IdKhachHang=:IdKhachHang",nativeQuery=true)
    Page<DiaChi> findAllByMaKhachHang(@Param("IdKhachHang")Long IdKhachHang, Pageable pageale);

    @Query(value = "select * from DiaChi where IdKhachHang = ? and LaDiaChiMacDinh = true ", nativeQuery = true)
    DiaChi findDiaChiByKhachHang(Integer id);
}
