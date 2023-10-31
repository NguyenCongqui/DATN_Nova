package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {

    @Query(value = "SELECT * FROM KhachHang  ", nativeQuery = true)
    Page<KhachHang> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM KhachHang WHERE trangThai =:trangThai ", nativeQuery = true)
    Page<KhachHang> findAllByTrangThaiCoPhanTrang(@Param("trangThai") int trangThai,
                                                  Pageable pageable);

    @Query(value = "SELECT * FROM KhachHang  WHERE trangThai =:trangThai "
            + "AND soDienThoai LIKE %:input% "
            + "OR  email LIKE %:input% "
            + "OR hoTen LIKE %:input% "
            , nativeQuery = true)
    Page<KhachHang> findAllByTrangThaiVaSoDienThoaiCoPhanTrang(@Param("trangThai") int trangThai
            , @Param("input") String input
            , Pageable pageable);


    @Query(value = "SELECT * FROM KhachHang  WHERE email =:email", nativeQuery = true)
    KhachHang findByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "UPDATE KhachHang SET trangThai = 1 WHERE idKhachHang=:id", nativeQuery = true)
    void capNhatTrangThaiThanhHoatDongTheoMa(@Param("id") Integer id);


    @Modifying
    @Query(value = "UPDATE KhachHang  SET trangThai = 0 WHERE idKhachHang=:id", nativeQuery = true)
    void capNhatTrangThaiThanhKhongHoatDongTheoMa(@Param("id") Integer id);


    @Query(value = "SELECT * FROM KhachHang  WHERE  soDienThoai LIKE %:input% "
            + "OR  email LIKE %:input% "
            + "OR hoTen LIKE %:input% "
            , nativeQuery = true)
    Page<KhachHang> findAllBySoDienThoaiCoPhanTrang(@Param("input") String input
            , Pageable pageable);

    @Query(value = "SELECT count(*) FROM KhachHang  WHERE trangThai =:trangThai", nativeQuery = true)
    int countByTrangThai(@Param("trangThai") Integer trangThai);

    @Query(value = "SELECT count(*) FROM KhachHang  WHERE"
            + " soDienThoai LIKE %:input% "
            + "OR  email LIKE %:input% "
            + "OR hoTen LIKE %:input% "
            , nativeQuery = true)
    int countByInput(@Param("input") String input);

    @Query(value = "SELECT count(*) FROM KhachHang  WHERE trangThai =:trangThai AND "
            + "soDienThoai LIKE %:input%   "
            + "OR  email LIKE %:input% "
            + "OR hoTen LIKE %:input% "
            , nativeQuery = true)
    int countByInputVaTrangThai(@Param("input") String input, @Param("trangThai") Integer trangThai);

    @Query(value = "SELECT * FROM KhachHang  WHERE email =:email AND trangThai =:trangThai ", nativeQuery = true)
    KhachHang findByEmailAndTrangThai(@Param("email") String email, @Param("trangThai") int i);

    @Query(value = "select * from KhachHang where idKhachHang = ?", nativeQuery = true)
    KhachHang findKhachHangByID(Integer IdKhachHang);

    @Query(value = "select * from KhachHang where soDienThoai = ?", nativeQuery = true)
    Optional<KhachHang> findKhachHangBySDT(String SDT);
}
