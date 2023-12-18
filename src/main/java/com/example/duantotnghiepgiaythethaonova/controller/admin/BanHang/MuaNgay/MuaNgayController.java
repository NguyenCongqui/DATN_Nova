package com.example.duantotnghiepgiaythethaonova.controller.admin.BanHang.MuaNgay;

import com.example.duantotnghiepgiaythethaonova.service.BanHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MuaNgayController {
    @Autowired
    BanHangService banHangService;

    @RequestMapping("mua-ngay-san-pham/checkout/{id}")
    public String banHangBanHangOnline(@PathVariable("id") Integer id, Model model) {
        banHangService.BanHangBanHangOnline(id, model);
        return "customer/MuaNgay/MuaNgayCheckOut";
    }
}
