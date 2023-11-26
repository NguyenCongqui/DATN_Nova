package com.example.duantotnghiepgiaythethaonova.controller.vnpay.VnpayMuaNgay;

import com.example.duantotnghiepgiaythethaonova.dto.PaymentDTO;
import com.example.duantotnghiepgiaythethaonova.service.VNPayService;
import com.example.duantotnghiepgiaythethaonova.service.VNPayService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VnpayMuaNgayController {

    @Autowired
    VNPayService2 vnPayService2;

    @PostMapping("/MuaNgay/payment/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) {

//        String vnpayUrl = vnPayService.createOrderMuaNgay(amount, orderCode, emailNguoiNhan, tienGiamGia, nameGiamGia, sdtNguoiNhan, tienShipHD, orderCode, nguoiNhan, diaChiGiaoHang, ghiChu);
        return ResponseEntity.ok().body(vnPayService2.createOrderMuaNgay(paymentDTO));
    }

    @GetMapping("/MuaNgay/payment/return")
    public String handleReturn(Model model, HttpServletRequest request) {
        int paymentStatus = vnPayService2.orderReturn(request);

        if (paymentStatus == 1) {
            vnPayService2.saveOrderReturnMuaNgay(request, model);
            // Trả về tên trang HTML hoặc đường dẫn đến trang HTML thành công
            return "/vnp/MuaNgay/SuccessMuaNgay";
        } else {
            // Trả về tên trang HTML hoặc đường dẫn đến trang HTML lỗi
            return "/vnp/MuaNgay/errorMuaNgay";
        }
    }

}
