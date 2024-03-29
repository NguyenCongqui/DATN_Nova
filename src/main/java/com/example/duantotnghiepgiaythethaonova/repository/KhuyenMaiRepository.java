package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Integer> {

    @Query("SELECT p FROM KhuyenMai p WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR p.tenKhuyenMai LIKE CONCAT('%', :keyword, '%')) " +
            "AND (:status = 'ALL' OR (:status = 'ON' AND p.trangThai = true) OR (:status = 'off' AND p.trangThai = false)) " +
            "AND p.phanTramGiam <= :end AND p.phanTramGiam> :start " +
            "AND (:startDate IS NULL AND :endDate IS NULL OR (p.ngayBatDau <= :startDate AND p.ngayKetThuc >= :endDate)) " +
            "AND p.xoa = false")
    Page<KhuyenMai> findVoucher(String keyword, String status, Integer start, Integer end, Date startDate, Date endDate, Pageable pageable);

    @Modifying
    @Query("update KhuyenMai v set v.trangThai = :status where v.idKhuyenMai in :ids")
    void updateStatusByDate(List<Integer> ids, boolean status);

    @Query("SELECT k FROM KhuyenMai k WHERE k.tenKhuyenMai =:tenKhuyenMai")
    KhuyenMai findByTenKhuyenMai(@Param("tenKhuyenMai") String maGiamGia);

    @Query(value = "select * from KhuyenMai where Xoa = 0 and TrangThai = 1", nativeQuery = true)
    List<KhuyenMai> getAllKhuyenMai();

    @Query(value = "SELECT * FROM KhuyenMai WHERE TenKhuyenMai = ?1 AND Xoa = 0", nativeQuery = true)
    Optional<KhuyenMai> findKhuyenMaiByTenKhuyenMai(String tenKhuyenMai);

    boolean existsByTenKhuyenMai(String name);

    @Query(value = "select * from KhuyenMai where Xoa = 0",nativeQuery = true)
    public List<KhuyenMai> findAll();
}



