package com.example.duantotnghiepgiaythethaonova.controller.admin.HoaDon.DonTaiQuay;

import com.example.duantotnghiepgiaythethaonova.service.DonHangTaiQuayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChiTietDonDaThanhToan {
    @Autowired
    DonHangTaiQuayService donHangTaiQuayService;

    @RequestMapping("DonTaiQuay/ChiTietHoaDon/hoa-don-id={id}")
    public String ChiTietHoaDonTaiQuayDaThanhToan(@PathVariable("id") Integer id, Model model) {
        donHangTaiQuayService.ChiTietHoaDonTaiQuayDaThanhToan(id, model);
        return"admin/hoadon/DonTaiQuay/ChiTietHoaDonTaiQuay";
    }


    @RequestMapping("DonTaiQuay/ChiTietHoaDon/DonDaHuy/hoa-don-id={id}")
    public String ChiTietHoaDonTaiQuayDaHuy(@PathVariable("id") Integer id, Model model) {
        donHangTaiQuayService.ChiTietHoaDonTaiQuayDaThanhToan(id, model);
        return "admin/hoadon/DonTaiQuay/ChiTietHoaDonTaiQuayDaHuy";
    }

}
