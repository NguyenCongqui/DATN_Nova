package com.example.duantotnghiepgiaythethaonova.controller.admin.HoaDon.DonTaiQuay;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChiTietDonDaThanhToan {

    @RequestMapping("DonTaiQuay/ChiTietHoaDon/hoa-don-id={id}")
    public String ChiTietHoaDonTaiQuayDaThanhToan(@PathVariable("id") Integer id, Model model) {

        return"admin/hoadon/DonTaiQuay/ChiTietDonTaiQuay";
    }


    @RequestMapping("DonTaiQuay/ChiTietHoaDon/DonDaHuy/hoa-don-id={id}")
    public String ChiTietHoaDonTaiQuayDaHuy(@PathVariable("id") Integer id, Model model) {

        return "admin/hoadon/DonTaiQuay/ChiTietHoaDonTaiQuay";
    }

}
