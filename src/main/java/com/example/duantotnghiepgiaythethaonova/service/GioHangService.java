package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.GioHangDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GioHangService {

	GioHangDTO findByKhachHangId(Integer i);

	void capNhatTongTien(Integer i);

	int tinhTienGioHangTheoMaGioHangChiTiet(int[] idGioHangChiTiet);

	ResponseEntity<String> addToCart(Integer sanPhamId, Integer mauSacId, Integer kichCoId, Integer soLuong);

	void xoaSachGioHang(Integer id);
}
