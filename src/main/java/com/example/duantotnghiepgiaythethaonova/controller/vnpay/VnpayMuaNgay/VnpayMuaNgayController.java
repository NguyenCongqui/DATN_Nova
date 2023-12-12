package com.example.duantotnghiepgiaythethaonova.controller.vnpay.VnpayMuaNgay;

import com.example.duantotnghiepgiaythethaonova.dto.PaymentDTO;
import com.example.duantotnghiepgiaythethaonova.service.VNPayService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin("http://127.0.0.1:8080")
@RestController
public class VnpayMuaNgayController {

    @Autowired
    VNPayService2 vnPayService2;

    @Autowired
    private ThymeleafViewResolver viewResolver;

    @PostMapping("/MuaNgay/payment/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok().body(vnPayService2.createOrderMuaNgay(paymentDTO));
    }

    @GetMapping("/MuaNgay/payment/return")
    public ModelAndView handleReturn(Model model, HttpServletRequest request) {
        int paymentStatus = vnPayService2.orderReturn(request);
        ModelAndView mav = new ModelAndView();
        if (paymentStatus == 1) {
            vnPayService2.saveOrderReturnMuaNgay(request, model);
            mav.setViewName("/vnp/MuaNgay/SuccessMuaNgay");
        } else {
            mav.setViewName("/vnp/MuaNgay/errorMuaNgay");
        }
        return mav;
    }


}
