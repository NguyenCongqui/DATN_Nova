package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.GioHang;
import com.example.duantotnghiepgiaythethaonova.entity.GioHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang,Integer> {
	
	@Query("SELECT gh from GioHang gh where gh.khachHang.idKhachHang=?1")
	GioHang findGioHangByEmail(String email);
	
	@Query("SELECT gh from GioHang gh where gh.khachHang.idKhachHang=?1 ")
	GioHang findGioHangByKhachHangId(Integer id);

	@Query(value = "SELECT * FROM dbo.GioHangCT WHERE IdGioHangCT = ?", nativeQuery = true)
	Page<GioHangChiTiet> getGHCTByHDID(Integer id, Pageable pageable);

	@Query(value = "SELECT * FROM dbo.GioHang WHERE IdKhachHang = ?", nativeQuery = true)
	GioHang findGioHangsByKhachHangId(Integer id);




}
