package com.example.duantotnghiepgiaythethaonova.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface DonHangTaiQuayService {

    //Đã thanh toán
    void DanhSachDaThanhToan(Model model, int page, int size);

    void timKiemHoaDonDaThanhToan(Model model, int page, int size, String duLieuTimKiem);

    void timKiemHoaDonDaThanhToanTheoNgayTao(Model model, int page, int size, String duLieuTimKiemString);

    void ChiTietHoaDonTaiQuayDaThanhToan(Integer id, Model model);

    //Đã hủy đơn
    void daHuyTaiQuay(Model model, int page, int size);

    void timKiemHoaDOnTaiQuayDaHuy(Model model, int page, int size, String duLieuTimKiem);

    void timKiemTheoNgayHoaDontaiQuayDaHuy(Model model, int page, int size, String duLieuTimKiemString);

    void ChiTietHoaDonTaiQuayDaHuy(Integer id, Model model);
}
