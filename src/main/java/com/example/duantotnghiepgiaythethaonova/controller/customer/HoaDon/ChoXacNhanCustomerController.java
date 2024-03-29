package com.example.duantotnghiepgiaythethaonova.controller.customer.HoaDon;

import com.example.duantotnghiepgiaythethaonova.service.HoaDonCustomerService;
import com.example.duantotnghiepgiaythethaonova.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChoXacNhanCustomerController {
    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonCustomerService hoaDonCustomerService;

    @RequestMapping("khach-hang/don-hang/cho-xac-nhan")
    public String choXacNhan(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "3") int size,
                             Model model) {
        hoaDonCustomerService.danhSachChoXacNhanCustomer(page, size, model);
        return "customer/HoaDon/DanhSach/choXacNhanCustomer";
    }
    
    @RequestMapping("khach-hang/updateHuyDon/{id}")
    public ResponseEntity<String> updateHuyDon(@PathVariable("id") Integer hoaDonId) {
        return hoaDonCustomerService.updateHuyDonHangCustomer(hoaDonId);
    }

    @RequestMapping("khach-hang/don-hang/chi-tiet-hoa-don/cho-xac-nhan/hoa-don-id={id}")
    public String CTChoGiaoHang(@PathVariable("id") Integer id, Model model) {
        hoaDonCustomerService.chiTietChoXacNhanCustomer(id, model);
        return "customer/HoaDon/ChiTietHoaDon/CTChoXacNhanCustomer";
    }

}
