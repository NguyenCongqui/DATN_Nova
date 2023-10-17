package com.example.duantotnghiepgiaythethaonova.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface NhanVienService {

    Map<String, Object> themMoiNhanVien(String email, String diaChi, String soDienThoai, String ho, String ten, String anhNhanVien, Integer ChucVu, Map<String, Object> response);

    Map<String, Object> ChinhSuaNhanVien(Integer idNhanVien, String email, String diaChi, String soDienThoai, String hoTen, String anhNhanVien, Integer ChucVu, Map<String, Object> response);

    ResponseEntity<String> XoaNhanVien(Integer id);

}
