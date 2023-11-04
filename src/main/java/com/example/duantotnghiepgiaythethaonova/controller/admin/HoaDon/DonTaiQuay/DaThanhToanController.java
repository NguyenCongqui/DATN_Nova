package com.example.duantotnghiepgiaythethaonova.controller.admin.HoaDon.DonTaiQuay;

import com.example.duantotnghiepgiaythethaonova.service.DonHangTaiQuayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DaThanhToanController {

    @Autowired
    DonHangTaiQuayService donHangTaiQuayService;

    @RequestMapping("admin/DaThanhToan/danhSach")
    public String DanhSachDaThanhToan(Model model,
                                      @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        donHangTaiQuayService.DanhSachDaThanhToan(model, page, size);
        return "admin/hoadon/DonTaiQuay/daThanhToan";
    }

}
