package com.example.duantotnghiepgiaythethaonova.service;


import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SanPhamChiTietSearchRepository {

	Page<ChiTietSanPham> searchProductDetailExist(SPAndSPCTSearchDto data, Pageable pageable);

}
