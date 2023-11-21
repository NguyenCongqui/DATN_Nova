package com.example.duantotnghiepgiaythethaonova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller403 {

    @GetMapping("/403")
    public String get403(){
        return "/customer/403";
    }

    @GetMapping("/test")
    public String test(){
        return "/customer/view/test";
    }


}
