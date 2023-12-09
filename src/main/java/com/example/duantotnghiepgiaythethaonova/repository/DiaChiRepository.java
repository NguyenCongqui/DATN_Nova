package com.example.duantotnghiepgiaythethaonova.repository;


import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi,Integer> {
	/*@Query(value="SELECT d FROM DiaChi d WHERE d.khachHang.id=?1")
	DiaChi findByKhachHangId(Long id);*/

	@Query("Select e from DiaChi e where e.laDiaChiMacDinh = false and e.khachHang.idKhachHang = :id")
	List<DiaChi> getAddressByCustomer(@Param("id") Integer id);

	@Query(value = "SELECT count(*) FROM DiaChi WHERE idKhachHang=:idKhachHang", nativeQuery = true)
	int countByMaKhachHang(@Param("idKhachHang") Integer idKhachHang);

	@Query(value = "SELECT * FROM DiaChi  WHERE idKhachHang=:idKhachHang", nativeQuery = true)
	Page<DiaChi> findAllByMaKhachHang(@Param("idKhachHang") Integer idKhachHang, Pageable pageale);

	@Query(value = "select * from DiaChi where idKhachHang = ? and laDiaChiMacDinh = 1 ", nativeQuery = true)
	DiaChi findDiaChiByKhachHang(Integer id);
}
