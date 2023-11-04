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

    @Query(value = "SELECT count(*) FROM HoaDonCT WHERE idHoaDonCT = :id AND daXoa=0", nativeQuery = true)
    Integer countByHoaDonId(@Param("id") Integer id);

    @Query(value = "SELECT * FROM HoaDonCT WHERE idHoaDonCT = :id AND daXoa=0", nativeQuery = true)
    Page<HoaDonChiTiet> findAllByHoaDonId(@Param("id") Integer id, Pageable pageable);

    @Query(value = "update HoaDonCT set daXoa = 1 where id = ? and idHoaDonCT = ?", nativeQuery = true)
    void xoaHoaDonCT(int HoaDonChiTietID, int hoaDonId);

    @Query(value = "select * from HoaDonCT where idSanPhamCT = ? and idHoaDonCT = ? and daXoa = 0", nativeQuery = true)
    Optional<HoaDonChiTiet> findBySanPhamChiTietAndHoaDon(int sanPhamCTId, int hoaDonId);

    @Query(value = "select * from HoaDonCT where idHoaDonCT = ? and daXoa = 0", nativeQuery = true)
    List<HoaDonChiTiet> findByHoaDonIdAndDaXoa(int id);
}
