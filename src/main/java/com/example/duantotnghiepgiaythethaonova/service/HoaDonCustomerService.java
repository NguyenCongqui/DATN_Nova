package com.example.duantotnghiepgiaythethaonova.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface HoaDonCustomerService {
    //Chờ giao hàng
    void danhSachChoGiaoHangCustomer(int page, int size, Model model);

    void chiTietChoGiaoHangCustomer(Integer id, Model model);

    //Chờ xác nhận
    void danhSachChoXacNhanCustomer(int page, int size, Model model);

    void chiTietChoXacNhanCustomer(Integer id, Model model);

    ResponseEntity<String> updateHuyDonHangCustomer(Integer hoaDonId);

    //Đã giao
    void danhSachDaGiaoCustomer(int page, int size, Model model);

    void chiTietDaGiaoCustomer(Integer id, Model model);

    //Đã hủy
    void danhSachDaHuyCustomer(int page, int size, Model model);

    void chiTietDaHuyCustomer(Integer id, Model model);

    //Đang giao
    void danhSachDangGiaoCustomer(int page, int size, Model model);

    void chiTietDangGiaoCustomer(Integer id, Model model);

    ResponseEntity<String> updateGiaoHangThanhCongCustomer(Integer hoaDonId);

    ResponseEntity<String> updateThanhCongAllCustomer();
}
