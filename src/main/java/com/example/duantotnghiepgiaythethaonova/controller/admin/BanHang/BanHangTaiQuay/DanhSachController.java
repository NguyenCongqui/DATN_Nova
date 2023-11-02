package com.example.duantotnghiepgiaythethaonova.controller.admin.BanHang.BanHangTaiQuay;

import com.example.duantotnghiepgiaythethaonova.entity.HoaDon;
import com.example.duantotnghiepgiaythethaonova.repository.HoaDonRepoditory2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DanhSachController {
    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @RequestMapping("admin/BanHangTaiQuay")
    public String DanhSachHoaDonTaiQuay(Model model) {
        List<HoaDon> danhSachBanHang = hoaDonRepoditory2.danhSachHoaDonTaiQuay(1);
        model.addAttribute("danhSachBanHang", danhSachBanHang);
        return "admin/banHang/banHangTaiQuay/DanhSach";
    }
}
