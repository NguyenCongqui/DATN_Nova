package com.example.duantotnghiepgiaythethaonova.service;


import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamChiTietSearchRepository {
//	List<ChiTietSanPham> searchChiTietSanPhamsExist();

	Page<ChiTietSanPham> searchProductDetailExist(SPAndSPCTSearchDto data, Pageable pageable);

}
