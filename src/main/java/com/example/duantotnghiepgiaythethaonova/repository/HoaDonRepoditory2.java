package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HoaDonRepoditory2 extends PagingAndSortingRepository<HoaDon, Integer> {
    Page<HoaDon> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE loaiHoaDon = :loai and idTrangThai = 6 and daXoa = 0 ORDER BY ngayTao DESC", nativeQuery = true)
    Page<HoaDon> finHDByLoaiHDTaiQuay(@Param("loai") Integer loai, PageRequest pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE loaiHoaDon = :loai and daXoa = 0 ORDER BY ngayTao DESC", nativeQuery = true)
    Page<HoaDon> finHDByLoaiHD(@Param("loai") Integer loai, PageRequest pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = ?1 and loaiHoaDon = 0 and daXoa = 0 ORDER BY ngayTao DESC", countQuery = "SELECT COUNT(*) FROM hoadon WHERE idtrangthai = ?1", nativeQuery = true)
    Page<HoaDon> findByTrangThaiHoaDonListTrangThai(int trangThai, Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = ? and idKhachHang = ?", nativeQuery = true)
    Page<HoaDon> findHoaDonByTrangThaiAndKhachHangId(int trangThai, Integer khachHangId, Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = ? ORDER BY ngayTao DESC", nativeQuery = true)
    Page<HoaDon> findHoaDonbyId(int trangThai, Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE loaiHoaDon = :loai", nativeQuery = true)
    Page<HoaDon> finHDByLoaiHD(@Param("loai") Integer loai, Pageable pageable);

    @Query(value = "select * from HoaDon where idHoaDon = ?", nativeQuery = true)
    Page<HoaDon> findByIDD(@Param("idHoaDon") Integer id, Pageable pageable);

    @Query(value = "select * from HoaDon where idTrangThai = 7 and loaiHoaDon = 1 and daXoa = 0 order by ngayTao desc", nativeQuery = true)
    Page<HoaDon> findHoaDonDaThanhToan(Pageable pageable);

    @Query(value = "select * from HoaDon where idTrangThai = 8 and loaiHoaDon = 1 and daXoa = 0 order by ngayTao desc", nativeQuery = true)
    Page<HoaDon> findHoaDonDaHuy(Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = 7 and loaiHoaDon = 1 and daXoa = 0 and (maDonHang LIKE %:input% OR tongTienHoaDon LIKE %:input% OR ghiChu LIKE %:input%)", nativeQuery = true)
    Page<HoaDon> findAllHoaDonDaThanhToanCoPhanTrang(@Param("input") String input, Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = 7 and loaiHoaDon = 1 and daXoa = 0 and CONVERT(DATE, ngayTao) = CONVERT(DATE, :ngayTao)", nativeQuery = true)
    Page<HoaDon> findHoaDoDaThanhToanByNgayTao(@Param("ngayTao") LocalDate ngayTao, Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = 8 and loaiHoaDon = 1 and daXoa = 0 and (maDonHang LIKE %:input% OR tongTienHoaDon LIKE %:input% OR ghiChu LIKE %:input%)", nativeQuery = true)
    Page<HoaDon> findAllHoaDonDaHuyCoPhanTrang(@Param("input") String input, Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = 8 and loaiHoaDon = 1 and daXoa = 0 and CONVERT(DATE, ngayTao) = CONVERT(DATE, :ngayTao)", nativeQuery = true)
    Page<HoaDon> findHoaDoDaHuyByNgayTao(@Param("ngayTao") LocalDate ngayTao, Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = ?1 AND loaiHoaDon = 0 AND daXoa = 0 AND (maDonHang LIKE %?2% OR tongTienHoaDon LIKE %?2% OR ghiChu LIKE %?2% OR nguoiNhan LIKE %?2% OR soDienThoaiNguoiNhan LIKE %?2%)", nativeQuery = true)
    Page<HoaDon> timKiemHoaDonDatHang(int trangThai, String input, Pageable pageable);

    @Query(value = "SELECT * FROM HoaDon WHERE idTrangThai = ?1 AND loaiHoaDon = 0 AND daXoa = 0 AND DATE(ngayTao) = ?2", nativeQuery = true)
    Page<HoaDon> timKiemHoaDonTheoNgay(int trangThai, LocalDate ngayTao, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM HoaDon WHERE loaiHoaDon = 1 AND idTrangThai = 6 AND daXoa = 0", nativeQuery = true)
    Integer soLuongHoaDonCho();

    @Query(value = "SELECT * FROM HoaDon WHERE loaiHoaDon = :loai and idTrangThai = 6 and daXoa = 0 ORDER BY ngayTao DESC", nativeQuery = true)
    List<HoaDon> danhSachHoaDonTaiQuay(@Param("loai") Integer loai);
}
