package com.example.duantotnghiepgiaythethaonova.repository;


import com.example.duantotnghiepgiaythethaonova.entity.KieuDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KieuDangRepository extends JpaRepository<KieuDang,Integer> {
	@Query(value = "SELECT * FROM KieuDang c WHERE c.DaXoa = 0 ORDER BY c.IdKieuDang DESC", nativeQuery = true)
	List<KieuDang> selectAllKieuDangExist();

	@Query(value = "SELECT * FROM KieuDang c WHERE c.DaXoa = 0 ORDER BY c.IdKieuDang DESC", nativeQuery = true)
	Page<KieuDang> selectAllKieuDangExist(Pageable page);
	
	@Query(value = "SELECT * FROM KieuDang c WHERE c.DaXoa = 0 AND c.TenKieuDang like %:TenKieuDang% ORDER BY c.IdKieuDang DESC", nativeQuery = true)
	Page<KieuDang> getKieuDangExistByName(@Param("TenKieuDang") String tenKieuDang, Pageable page);
	
}
