package com.example.duantotnghiepgiaythethaonova.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

//    @GetMapping()
//    public String dashboard() {
//        return "/admin/pages/landing_page";
//
//    }
    @RequestMapping("/admin/home")
    public String loginSubmit(){
        return "/admin/home/index";
    }

    @RequestMapping("/403")
    public String get403(){
        return "/customer/403";
    }


}
