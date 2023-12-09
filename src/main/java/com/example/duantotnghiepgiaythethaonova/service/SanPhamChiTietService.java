package com.example.duantotnghiepgiaythethaonova.service;


import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamProductManageDTO;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public interface SanPhamChiTietService {

	List<ChiTietSanPham> getLstChiTietSanPhamExist();

	Optional<ChiTietSanPham> findById(Integer id);

	List<ChiTietSanPham> getLstChiTietSanPhamBySanPhamId(Integer id);

	Page<ChiTietSanPham> searchProductDetailExist(SPAndSPCTSearchDto data, Pageable pageable);

	void delete(ChiTietSanPham entity);

	<S extends ChiTietSanPham> S save(S entity);

	List<ChiTietSanPham> getLstChiTietSanPhamAddImg(Integer id);

	List<Integer> getLstMauSacBySanPhamId(Integer sanPhamId);

	Optional<ChiTietSanPham> getChiTietSanPhamByMauSacSizeSanPhamId(Integer sanPhamId, Integer mauSacId, Integer kichCoId);

	int selectCountChiTietSanPhamDuplicate(Integer mauSacId, Integer kichCoId, Integer sanPhamId);

	int getCountChiTietSanPhamExistBySanPhamIdAndMauSacId(Integer sanPhamId, Integer mauSacId);

	int getCountChiTietSanPhamExistBySanPhamId(Integer sanPhamId);

	int getSumSoLuongBySanPhamId(Integer id);

	BigDecimal getTienBan(Integer id);

	Optional<ChiTietSanPham> selectChiTietSanPhamDuplicate(Integer mauSacId, Integer kichCoId, Integer sanPhamId);

	void WritingToExcelProduct(List<SanPhamProductManageDTO> lstDto) throws IOException;

}
