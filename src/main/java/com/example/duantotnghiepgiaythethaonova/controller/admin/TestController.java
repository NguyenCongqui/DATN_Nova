package com.example.duantotnghiepgiaythethaonova.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/test")
public class TestController {

    @GetMapping()
    public String test(){
        return "/admin/test/view";
    }
}
