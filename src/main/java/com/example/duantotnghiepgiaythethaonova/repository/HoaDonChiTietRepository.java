package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {

    @Query(value = "SELECT count(*) FROM HoaDonCT WHERE idHoaDon = :id AND daXoa= 0", nativeQuery = true)
    Integer countByHoaDonId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM HoaDonCT WHERE idHoaDon = :id AND daXoa= 0", nativeQuery = true)
    Page<HoaDonChiTiet> findAllByHoaDonId(@Param("id") Integer id, Pageable pageable);

    @Query(value = "update HoaDonCT set daXoa = 1 where idHoaDon = ? and idHoaDonCT = ?", nativeQuery = true)
    void xoaHoaDonCT(Integer HoaDonChiTietID, Integer hoaDonId);

    @Query(value = "select * from HoaDonCT where idSanPhamCT = ? and idHoaDon = ? and daXoa = 0", nativeQuery = true)
    Optional<HoaDonChiTiet> findBySanPhamChiTietAndHoaDon(Integer sanPhamCTId, Integer hoaDonId);

    @Query(value = "select * from HoaDonCT where idHoaDon = ? and daXoa = 0", nativeQuery = true)
    List<HoaDonChiTiet> findByHoaDonIdAndDaXoa(Integer id);
}
