package com.example.duantotnghiepgiaythethaonova.controller.khachHang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("khachhang")
public class banhang {

    @GetMapping
    public String view (){
        return "client/test";
    }
}
