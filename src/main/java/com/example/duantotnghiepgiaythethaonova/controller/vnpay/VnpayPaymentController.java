package com.example.duantotnghiepgiaythethaonova.controller.vnpay;

import com.example.duantotnghiepgiaythethaonova.dto.PaymentDTO;
import com.example.duantotnghiepgiaythethaonova.service.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


@Controller
public class VnpayPaymentController {
    @Autowired
    VNPayService vnPayService;

    @PostMapping("payment/create")
    public String createPayment(@RequestParam("orderCode") String orderCode,
                                @RequestParam("amount") long amount,
                                @RequestParam("diaChiGiaoHang") String diaChiGiaoHang,
                                @RequestParam("nguoiNhan") String nguoiNhan,
                                @RequestParam("emailNguoiNhann") String emailNguoiNhan,
                                @RequestParam("tienGiamGia") BigDecimal tienGiamGia,
                                @RequestParam("nameGiamGia") String nameGiamGia,
                                @RequestParam("sdtNguoiNhan") String sdtNguoiNhan,
                                @RequestParam("ghiChu") String ghiChu,
                                @RequestParam("tienShipHD") BigDecimal tienShipHD, RedirectAttributes redirectAttributes) {

        String vnpayUrl = vnPayService.createOrder(amount, orderCode, emailNguoiNhan, tienGiamGia, nameGiamGia, sdtNguoiNhan, tienShipHD, orderCode, nguoiNhan, diaChiGiaoHang, ghiChu);
        System.out.println(vnPayService.createOrder(amount, orderCode, emailNguoiNhan, tienGiamGia, nameGiamGia, sdtNguoiNhan, tienShipHD, orderCode, nguoiNhan, diaChiGiaoHang, ghiChu));
        System.out.println("dbcdkbc");
        return "redirect:" + vnpayUrl;
    }
    @RequestMapping("payment/return")
    public String handleReturn(@RequestParam Integer nguoiDungId, Model model, HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);

        if (paymentStatus == 1) {
            vnPayService.saveOrderReturn(request, model, nguoiDungId);
            return "vnp/success";
        } else {
            return "vnp/error";
        }
    }
}
