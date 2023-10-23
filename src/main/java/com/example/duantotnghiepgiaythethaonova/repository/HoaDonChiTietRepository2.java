package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository2 extends JpaRepository<HoaDonChiTiet , Integer> {

    @Query(value = "select * from HoaDonChiTiet where idHoaDonCT = ? and daXoa = 0", nativeQuery = true)
    Page<HoaDonChiTiet> findHDCTByHoaDonId(Integer hoaDonId, Pageable pageable);

    @Query(value = "select * from HoaDonChiTiet where idHoaDonCT = ? and daXoa = 0", nativeQuery = true)
    List<HoaDonChiTiet> findHDCT(Integer hoaDonId);

    @Query(value = "select * from HoaDonChiTiet where idHoaDonCT = ? and daXoa = 0", nativeQuery = true)
    List<HoaDonChiTiet> findHDCT2(Integer hoaDonId);
}
