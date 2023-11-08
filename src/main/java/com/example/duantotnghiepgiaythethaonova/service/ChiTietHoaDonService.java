package com.example.duantotnghiepgiaythethaonova.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface ChiTietHoaDonService {
    void choXacNhan(Integer id, Model model);

    void choGiaoHang(Integer id, Model model);

    void dangGiaoHang(Integer id, Model model);

    void daGiaoHang(Integer id, Model model);

    void daHuy(Integer id, Model model);

}
