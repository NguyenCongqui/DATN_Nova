package com.example.duantotnghiepgiaythethaonova.controller.admin.NhanVien;

import com.example.duantotnghiepgiaythethaonova.repository.NguoiDungRepository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDung_VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThongTinNhanVienController {

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    NguoiDung_VaiTroRepository nguoiDung_vaiTroRepository;

    @RequestMapping("admin/thongTinNhanVienDangDangNhap")
    public String thongTinDangDangNhap(Model model) {


        return "admin/NhanVien/ThongTinNhanVien/thongTinNhanVien";
    }

}
