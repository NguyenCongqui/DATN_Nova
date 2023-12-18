package com.example.duantotnghiepgiaythethaonova.controller.customer.HoaDon;

import com.example.duantotnghiepgiaythethaonova.service.HoaDonCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DaGiaoCustomerController {
    @Autowired
    HoaDonCustomerService hoaDonCustomerService;

    @RequestMapping("khach-hang/don-hang/da-giao-hang")
    public String DaGiaoCustomer(Model model,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "3") int size) {
        hoaDonCustomerService.danhSachDaGiaoCustomer(page, size, model);
        return "customer/HoaDon/DanhSach/daGiaoCustomer";
    }

    @RequestMapping("khach-hang/don-hang/chi-tiet-hoa-don/da-giao-hang/hoa-don-id={id}")
    public String DaGiaoHang(@PathVariable("id") Integer id, Model model) {
        hoaDonCustomerService.chiTietDaGiaoCustomer(id, model);
        return "customer/HoaDon/ChiTietHoaDon/CTDaoGiaoCustomer";
    }
}
