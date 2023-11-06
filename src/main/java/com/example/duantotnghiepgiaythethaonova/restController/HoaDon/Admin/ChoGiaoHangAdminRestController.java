package com.example.duantotnghiepgiaythethaonova.restController.HoaDon.Admin;

import com.example.duantotnghiepgiaythethaonova.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChoGiaoHangAdminRestController {
    @Autowired
    HoaDonService hoaDonService;

    //UPDATE TRẠNG THÁI CỦA ĐƠN HÀNG LÀ ĐANG GIAO HÀNG
    @RequestMapping("/updateGiaoHang/{id}")
    public ResponseEntity<String> updateGiaoHang(@PathVariable("id") Integer hoaDonId) {
        return hoaDonService.updateGiaoHangChoGiaoHang(hoaDonId);
    }

    //GIAO TẤT CẢ
    @RequestMapping("/updateGiaoHangAll")
    public ResponseEntity<String> updateGiaoHangAll() {
        return hoaDonService.updateGiaoHangAllChoGiaoHang();
    }
}
