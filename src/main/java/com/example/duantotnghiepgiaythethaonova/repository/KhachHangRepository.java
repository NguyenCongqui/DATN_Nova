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
public interface KhachHangRepository extends JpaRepository<KhachHang , Integer> {

    @Query(value="SELECT * FROM KhachHang  ",nativeQuery=true)
    Page<KhachHang> findAll(Pageable pageable);

    @Query(value="SELECT * FROM KhachHang  WHERE trang_thai=:trangThai ",nativeQuery=true)
    Page<KhachHang> findAllByTrangThaiCoPhanTrang(@Param("trangThai") int trangThai,
                                                  Pageable pageable);

    @Query(value="SELECT * FROM KhachHang  WHERE  trang_thai =:trangThai "
            + "AND so_dien_thoai LIKE %:input% "
            + "OR  email LIKE %:input% "
            + "OR ho_ten LIKE %:input% "
            ,nativeQuery=true)
    Page<KhachHang> findAllByTrangThaiVaSoDienThoaiCoPhanTrang(@Param("trangThai")int trangThai
            ,@Param("input") String input
            , Pageable pageable);


    @Query(value="SELECT * FROM KhachHang  WHERE Email=:email",nativeQuery=true)
    KhachHang findByEmail(@Param("email") String  email);

    @Modifying
    @Query(value="UPDATE KhachHang SET TrangThai = 1 WHERE IdKhachHang=:id",nativeQuery=true)
    void capNhatTrangThaiThanhHoatDongTheoMa(@Param("id") Integer id);


    @Modifying
    @Query(value="UPDATE KhachHang  SET TrangThai = 0 WHERE IdKhachHang=:id",nativeQuery=true)
    void capNhatTrangThaiThanhKhongHoatDongTheoMa(@Param("id") Integer id);


    @Query(value="SELECT * FROM KhachHang  WHERE  SoDienThoai LIKE %:input% "
            + "OR  Email LIKE %:input% "
            + "OR HoTen LIKE %:input% "
            ,nativeQuery=true)
    Page<KhachHang> findAllBySoDienThoaiCoPhanTrang(@Param("input")String input
            , Pageable pageable);

    @Query(value="SELECT count(*) FROM KhachHang  WHERE TrangThai=:trangThai",nativeQuery=true)
    int countByTrangThai(@Param("trangThai") Integer trangThai);

    @Query(value="SELECT count(*) FROM KhachHang  WHERE"
            + " SoDienThoai LIKE %:input% "
            + "OR  Email LIKE %:input% "
            + "OR HoTen LIKE %:input% "
            ,nativeQuery=true)
    int countByInput(@Param("input")String input);

    @Query(value="SELECT count(*) FROM KhachHang  WHERE TrangThai =:trangThai AND "
            + "SoDienThoai LIKE %:input%   "
            + "OR  Email LIKE %:input% "
            + "OR HoTen LIKE %:input% "
            ,nativeQuery=true)
    int countByInputVaTrangThai(@Param("input")String input, @Param("trangThai")Integer trangThai);

    @Query(value="SELECT * FROM KhachHang  WHERE Email =:email AND TrangThai =:trangThai ",nativeQuery=true)
    KhachHang findByEmailAndTrangThai(@Param("email")String email, @Param("trangThai") int i);

    @Query(value = "select * from KhachHang where IdKhachHang = ?", nativeQuery = true)
    KhachHang findKhachHangByID(Integer IdKhachHang);

    @Query(value = "select * from KhachHang where SoDienThoai = ?", nativeQuery = true)
    Optional<KhachHang> findKhachHangBySDT(String SDT);
}
