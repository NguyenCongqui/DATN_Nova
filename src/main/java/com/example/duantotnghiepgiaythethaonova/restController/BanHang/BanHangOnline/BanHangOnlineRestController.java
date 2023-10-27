package com.example.duantotnghiepgiaythethaonova.restController.BanHang.BanHangOnline;

import com.example.duantotnghiepgiaythethaonova.service.BanHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BanHangOnlineRestController {
    @Autowired
    BanHangService banHangService;

    @PostMapping("/save-order/{id}")
    public @ResponseBody Map<String, Object> saveOrder(@RequestParam("totalAmount") BigDecimal totalAmount,
                                                       @RequestParam("shippingFee") BigDecimal shippingFee,
                                                       @RequestParam("tien_giam") BigDecimal tien_giam,
                                                       @RequestParam String tenGiamGia,
                                                       @RequestParam("emailNguoiNhan") String emailNguoiNhan,
                                                       @RequestParam("diaChiGiaoHang") String diaChiGiaoHang,
                                                       @RequestParam("nguoiNhan") String nguoiNhan,
                                                       @RequestParam("sdtNguoiNhan") String sdtNguoiNhan,
                                                       @RequestParam("ghiChu") String ghiChu,
                                                       @PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();
        banHangService.saveOrderBanHangOnline(totalAmount, shippingFee, tien_giam, tenGiamGia, emailNguoiNhan, diaChiGiaoHang, nguoiNhan, sdtNguoiNhan, ghiChu, id);

        response.put("success", true);
        return response;
    }

    @PostMapping("/banHang/themMaGiamGiaOnline")
    public ResponseEntity<Map<String, String>> themMaGiamGia(@RequestParam String couponCode) {
        return banHangService.themMaGiamGiaBanHangOnline(couponCode);
    }
}
