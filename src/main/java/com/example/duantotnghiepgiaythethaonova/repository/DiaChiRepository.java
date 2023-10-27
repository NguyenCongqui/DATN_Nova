package com.example.duantotnghiepgiaythethaonova.repository;


import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi,Integer> {
	/*@Query(value="SELECT d FROM DiaChi d WHERE d.khachHang.id=?1")
	DiaChi findByKhachHangId(Long id);*/

	@Query(value="SELECT count(*) FROM dia_chi WHERE khach_hang_id=:khachHangId",nativeQuery=true)
	int countByMaKhachHang(@Param("khachHangId")Integer khachHangId);

	@Query(value="SELECT * FROM dia_chi  WHERE khach_hang_id=:khachHangId",nativeQuery=true)
	Page<DiaChi> findAllByMaKhachHang(@Param("khachHangId")Integer khachHangId, Pageable pageale);

	@Query(value = "select * from dia_chi where khach_hang_id = ? and la_dia_chi_mac_dinh = true", nativeQuery = true)
	DiaChi findDiaChiByKhachHang(int id);
}
