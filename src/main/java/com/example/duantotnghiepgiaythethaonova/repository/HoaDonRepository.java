package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.GiaoDich;
import com.example.duantotnghiepgiaythethaonova.entity.HoaDon;
import com.example.duantotnghiepgiaythethaonova.request.HoaDonRequest;
import com.example.duantotnghiepgiaythethaonova.response.HoaDonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {
    @Query(value = "SELECT * FROM HoaDon WHERE loaiHoaDon = 1 and daXoa = 0", nativeQuery = true)
    HoaDon findHoaDonBanHang();

    @Query(value = "select * from HoaDon where idTrangThai = ?1", nativeQuery = true)
    List<HoaDon> findByTrangThaiHoaDonListTrangThai(int trangThai);

    @Query(value = "select Max(IdHoaDon) from HoaDon", nativeQuery = true)
    Integer getMaxId();

    List<HoaDon> findByNgayTao(Date ngayTao);

    @Query(value = "SELECT * FROM HoaDon WHERE loaiHoaDon = :loaiHoaDon AND idKhachHang = :khachHangId", nativeQuery = true)
    Page<HoaDon> findAllByLoaiHoaDonAndMaKhachHang(@Param("loaiHoaDon") Integer loaiHoaDon,
                                                   @Param("khachHangId") Integer khachHangId, Pageable pageable);

    @Query(value = "SELECT count(*) FROM HoaDon WHERE loaiHoaDon = :loaiHoaDon", nativeQuery = true)
    Integer countByLoaiHoaDon(@Param("loaiHoaDon") Integer loaiHoaDon);

    @Query(value = "SELECT * FROM HoaDon WHERE maDon = :maDonHang", nativeQuery = true)
    HoaDon findByMaDonHang(@Param("maDonHang") String maDonHang);

    @Modifying
    @Query(value = "UPDATE HoaDon SET idTrangThai= 5 WHERE idHoaDon = :id", nativeQuery = true)
    void capNhatTrangThaiThanhHuyDon(@Param("id") Integer id);

    @Query(value = "SELECT TOP 1 * FROM GiaoDich WHERE idTrangThai = ? AND idHoaDon = ? ORDER BY idGiaoDich DESC", nativeQuery = true)
    List<GiaoDich> timeLine(int trangThai, Integer hoaDonId);

    @Query("select hd from HoaDon hd where hd.ngayTao <= :endDate and hd.ngayTao >= :startDate")
    List<HoaDon> getHoaDonInRangeDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

//    @Query(value = "SELECT hd.IdHoaDon, hd.GhiChu, hd.DaXoa FROM HoaDon hd",nativeQuery = true)
//    List<HoaDonResponse> getIdHDGhiChu();
}
