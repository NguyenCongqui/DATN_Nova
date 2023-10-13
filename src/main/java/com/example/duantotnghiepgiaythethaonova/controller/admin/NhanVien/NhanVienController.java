package com.example.duantotnghiepgiaythethaonova.controller.admin.NhanVien;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {

    @GetMapping("/view")
    public String getNV(){
        return "/admin/NhanVien/danhsach";
    }
}
