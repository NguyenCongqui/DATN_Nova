package com.example.duantotnghiepgiaythethaonova.repository;

import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INguoiDungPaginRespository extends PagingAndSortingRepository<NguoiDung , Integer> {
    @Query(value = "select * from NguoiDung where daXoa = 0" , nativeQuery = true)
    Page<NguoiDung> findAll(Pageable pageable);

    @Query(value = "select * from NguoiDung where daXoa = 0" , nativeQuery = true)
    Page<NguoiDung> GetAll(Pageable pageable);

}
