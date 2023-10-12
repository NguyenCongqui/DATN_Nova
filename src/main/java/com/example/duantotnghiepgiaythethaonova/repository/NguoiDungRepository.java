package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

    @Query(value = "select e from NguoiDung e where e.DaXoa = false" , nativeQuery = true)
    List<NguoiDung> getAll();

    @Query(value = "select e from NguoiDung e where e.IdNguoiDung= :id")
    NguoiDung getById(@Param("id") Integer id);

    @Query(value = "select e from NguoiDung e where e.TrangThai = 0 and e.TenNguoiDung like :name")
    List<NguoiDung> searchByName(@Param("name") String name);





}
