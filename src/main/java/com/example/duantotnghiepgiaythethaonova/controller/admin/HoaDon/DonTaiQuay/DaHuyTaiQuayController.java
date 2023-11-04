package com.example.duantotnghiepgiaythethaonova.controller.admin.HoaDon.DonTaiQuay;

import com.example.duantotnghiepgiaythethaonova.service.DonHangTaiQuayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DaHuyTaiQuayController {
    @Autowired
    DonHangTaiQuayService donHangTaiQuayService;

    @RequestMapping("admin/DaHuyTaiQuay/danhSach")
    public String daHuyTaiQuay(Model model,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "5") int size) {
        donHangTaiQuayService.daHuyTaiQuay(model, page, size);
        return "admin/hoadon/DonTaiQuay/daHuyTaiQuay";
    }
}
