package com.example.duantotnghiepgiaythethaonova.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/home")
public class TestController {

//    @GetMapping()
//    public String dashboard() {
//        return "/admin/pages/landing_page";
//
//    }
    @GetMapping()
    public String loginSubmit(){
        return "/admin/home/index";
    }


}
