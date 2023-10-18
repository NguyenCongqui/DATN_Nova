package com.example.duantotnghiepgiaythethaonova.repository;

import
com.example.duantotnghiepgiaythethaonova.entity.GioHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Integer> {
	
	@Query("SELECT ghct FROM GioHangChiTiet ghct where  ghct.gioHang.idGioHang =:id")
	public List<GioHangChiTiet> findAllByGioHangId(@Param("id") Integer id);

	@Query("SELECT ghct FROM GioHangChiTiet ghct where  ghct.gioHang.idGioHang =:id")
	public Optional<GioHangChiTiet> findByGioHangId(@Param("id") Integer id);

	@Query("SELECT SUM(ghct.thanhTien) FROM GioHangChiTiet ghct WHERE ghct.gioHang.idGioHang =:id")
	public Integer tongTien(@Param("id")Integer id);

	@Query(value = "SELECT * FROM dbo.GioHangCT WHERE IdGioHang = ?", nativeQuery = true)
	Page<GioHangChiTiet> getGHCTByHDID(Integer id, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE dbo.GioHangCT SET DaXoa = true WHERE IdSanPhamCT = :id", nativeQuery = true)
	void xoaGioHangChiTiet(@Param("id") Integer id);

	@Query(value = "SELECT * FROM dbo.GioHangCT WHERE IdSanPhamCT = ? AND IdGioHang = ? AND DaXoa = false", nativeQuery = true)
	Optional<GioHangChiTiet> findBySanPhamChiTietAndGioHang(Integer sanPhamCTId, Integer gioHangId);

	@Query(value = "SELECT * FROM dbo.GioHangCT WHERE IdGioHang = ? AND DaXoa = false", nativeQuery = true)
	List<GioHangChiTiet> findbyGiohangIdAndDaXoa(Integer id);
}
