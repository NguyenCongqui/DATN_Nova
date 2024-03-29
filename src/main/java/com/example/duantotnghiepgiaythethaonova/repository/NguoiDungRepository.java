package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

    @Query(value = "select * from NguoiDung where daXoa = 0", nativeQuery = true)
    List<NguoiDung> GetAll();

//    @Query(value = "select e from NguoiDung e where e.TrangThai = 0 and e.TenNguoiDung like :name")
//    List<NguoiDung> searchByName(@Param("name") String name);

    @Query(value = "select MAX(idNguoiDung) from NguoiDung", nativeQuery = true)
    Integer getMaxId();

    @Modifying
    @Query(value = "UPDATE NguoiDung SET daXoa = 1 where idNguoiDung= :id", nativeQuery = true)
    void xoaNguoiDung(@Param("id") Integer id);

    @Query(value = "select * from NguoiDung where trangThai= ?1", nativeQuery = true)
    List<NguoiDung> findByTrangThai(Integer trangThai);

    @Query(value = "SELECT * FROM NguoiDung where Email= ?1 AND DaXoa= 0", nativeQuery = true)
    NguoiDung findByEmail(String email);

    @Query(value = "select * from NguoiDung where email= ?1 AND daXoa= 0", nativeQuery = true)
    Optional<NguoiDung> findByEmail2(String email);

    @Query(value = "select * from NguoiDung where soDienThoai  = ?1 AND daXoa=0", nativeQuery = true)
    NguoiDung findBysoDienThoai(String sdt);

    @Modifying
    @Query(value = "UPDATE NguoiDung SET trangThai = 0 where idNguoiDung = :id", nativeQuery = true)
    void capNhatTrangThaiThanhHoatDongTheoMa(@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE NguoiDung SET trangThai = 1 where idNguoiDung = :id", nativeQuery = true)
    void capNhatTrangThaiThanhKhongHoatDongTheoMa(@Param("id") Integer id);

    @Query(value = "select * from NguoiDung where trangThai = 0 AND daXoa=0 AND email =:email", nativeQuery = true)
    NguoiDung findByEmailAndTrangThaiAndDaXoa(@Param("email") String email);

    @Query(value = "SELECT * FROM NguoiDung where daXoa = 0 ORDER BY ngayTao DESC", nativeQuery = true)
    Page<NguoiDung> findAllNguoiDung(Pageable pageable);

    @Query(value = "SELECT * FROM NguoiDung where daXoa = 0 and trangThai = ?", nativeQuery = true)
    Page<NguoiDung> timKiemNguoiDungByTrangThai(Integer trangThai, Pageable pageable);

    @Query(value = "SELECT * FROM NguoiDung WHERE maNguoiDung = :maNguoiDung", nativeQuery = true)
    NguoiDung findNguoiDungByMaNguoiDung(@Param("maNguoiDung") String maNguoiDung);

    @Query(value = "select * from NguoiDung where daXoa = 0 and trangThai = ?", nativeQuery = true)
    Page<NguoiDung> timKiemNguoiDungByTrangThai(int trangThai, Pageable pageable);

//    @Query(value = "select * from NguoiDung where daXoa = 0 and DATE(ngayTao) = :ngayTao", nativeQuery = true)
//    Page<NguoiDung> timKiemNguoiDungByNgayTao(@Param("ngayTao") LocalDate ngayTao, Pageable pageable);

    @Query(value = "SELECT * FROM NguoiDung WHERE daXoa = 0 AND CONVERT(DATE, ngayTao) = :ngayTao", nativeQuery = true)
    Page<NguoiDung> timKiemNguoiDungByNgayTao(@Param("ngayTao") LocalDate ngayTao, Pageable pageable);

    @Query(value = "select * from NguoiDung where daXoa = 0 and (maNguoiDung like %:input% or soDienThoai like %:input% or tenNguoiDung like %:input% or email like %:input%)", nativeQuery = true)
    Page<NguoiDung> timKiemNguoiDung(@Param("input") String input, Pageable pageable);



}
