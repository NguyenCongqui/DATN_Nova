package com.example.duantotnghiepgiaythethaonova.repository;


import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SanPhamSearchRepository {
    Page<SanPham> searchProductExist(SPAndSPCTSearchDto data, Pageable pageable);
}
