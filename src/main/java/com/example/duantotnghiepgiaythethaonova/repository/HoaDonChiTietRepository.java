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
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet,Integer> {
    
    @Query(value = "SELECT count(*) FROM hoa_don_chi_tiet WHERE hoa_don_id = :id AND da_xoa=false", nativeQuery = true)
	Integer countByHoaDonId(@Param("id") Integer id);
    
    @Query(value = "SELECT * FROM hoa_don_chi_tiet WHERE hoa_don_id = :id AND da_xoa=false", nativeQuery = true)
	Page<HoaDonChiTiet> findAllByHoaDonId(@Param("id")Integer id, Pageable pageable);

    @Query(value = "update hoa_don_chi_tiet set da_xoa = true where id = ? and hoa_don_id = ?", nativeQuery = true)
    void xoaHoaDonCT(int HoaDonChiTietID, int hoaDonId);

    @Query(value = "select * from hoa_don_chi_tiet where san_pham_chi_tiet_id = ? and hoa_don_id = ? and da_xoa = false", nativeQuery = true)
    Optional<HoaDonChiTiet> findBySanPhamChiTietAndHoaDon(int sanPhamCTId, int hoaDonId);

    @Query(value = "select * from hoa_don_chi_tiet where hoa_don_id = ? and da_xoa = false", nativeQuery = true)
    List<HoaDonChiTiet> findByHoaDonIdAndDaXoa(int id);
}
