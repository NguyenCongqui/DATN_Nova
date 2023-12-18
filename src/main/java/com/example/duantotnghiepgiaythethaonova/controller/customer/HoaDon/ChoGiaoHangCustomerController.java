package com.example.duantotnghiepgiaythethaonova.controller.customer.HoaDon;

import com.example.duantotnghiepgiaythethaonova.service.HoaDonCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChoGiaoHangCustomerController {
    @Autowired
    HoaDonCustomerService hoaDonCustomerService;

    //CHỜ GIAO HÀNG
    @RequestMapping("khach-hang/don-hang/cho-giao-hang")
    public String choGiaoHang(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "3") int size,
                              Model model) {
        hoaDonCustomerService.danhSachChoGiaoHangCustomer(page, size, model);
        return "customer/HoaDon/DanhSach/choGiaoHangCustomer";
    }

    @RequestMapping("khach-hang/don-hang/chi-tiet-hoa-don/cho-giao-hang/hoa-don-id={id}")
    public String CTChoGiaoHang(@PathVariable("id") Integer id, Model model) {
        hoaDonCustomerService.chiTietChoGiaoHangCustomer(id, model);
        return "customer/HoaDon/ChiTietHoaDon/CTChoGiaoHangCustomer";
    }
}
