package com.example.duantotnghiepgiaythethaonova.controller.vnpay;

import com.example.duantotnghiepgiaythethaonova.dto.PaymentDTO;
import com.example.duantotnghiepgiaythethaonova.service.VNPayService;
import com.example.duantotnghiepgiaythethaonova.service.VNPayService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


@CrossOrigin("http://127.0.0.1:8080")
@RestController
public class VnpayPaymentController {

    @Autowired
    VNPayService2 vnPayService2;

    @Autowired
    private ThymeleafViewResolver viewResolver;

    @PostMapping("/payment/create")
    public ResponseEntity<?> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok().body(vnPayService2.createOrder(paymentDTO));
    }

    @GetMapping("/payment/return")
    public ModelAndView handleReturn(@RequestParam int nguoiDungId, Model model, HttpServletRequest request) {
        int paymentStatus = vnPayService2.orderReturn(request);
        ModelAndView mav = new ModelAndView();
        if (paymentStatus == 1) {
            vnPayService2.saveOrderReturn(request, model, nguoiDungId);
            mav.setViewName("/vnp/success");
        } else {
            mav.setViewName("/vnp/error");
        }
        return mav;
    }

}
