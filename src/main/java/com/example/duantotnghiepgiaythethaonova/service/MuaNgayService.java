package com.example.duantotnghiepgiaythethaonova.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MuaNgayService {
    ResponseEntity<String> MuaNgaySanPham(Integer sanPhamId, Integer mauSacId, Integer kichCoId, Integer soLuong);
}
