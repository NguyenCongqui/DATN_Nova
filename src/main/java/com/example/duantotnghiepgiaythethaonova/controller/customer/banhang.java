package com.example.duantotnghiepgiaythethaonova.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("khach-hang")
public class banhang {

    @GetMapping
    public String view (){
        return "customer/view/shop-details";
    }
}
