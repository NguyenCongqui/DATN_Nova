package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.GioHangDTO;
import org.springframework.http.ResponseEntity;

public interface GioHangService {

	GioHangDTO findByKhachHangId(Integer i);

	void capNhatTongTien(Integer i);

	int tinhTienGioHangTheoMaGioHangChiTiet(Integer[] idGioHangChiTiet);

	ResponseEntity<String> addToCart(Integer sanPhamId, Integer mauSacId, Integer kichCoId, Integer soLuong);

	void xoaSachGioHang(Integer id);
}
