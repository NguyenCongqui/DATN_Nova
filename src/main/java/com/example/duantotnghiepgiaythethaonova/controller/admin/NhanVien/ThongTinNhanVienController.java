package com.example.duantotnghiepgiaythethaonova.controller.admin.NhanVien;

import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDungRepository;
import com.example.duantotnghiepgiaythethaonova.repository.NguoiDung_VaiTroRepository;
import com.example.duantotnghiepgiaythethaonova.security.NguoiDungDetails;
import com.example.duantotnghiepgiaythethaonova.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThongTinNhanVienController {

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    NguoiDung_VaiTroRepository nguoiDung_vaiTroRepository;

    @RequestMapping("admin/thongTinNhanVienDangDangNhap")
    public String thongTinDangDangNhap(Model model) {
        NguoiDungDetails nd = SecurityUtil.getPrincipal();
        String maNguoiDung = nd.getMaNguoiDung();
        NguoiDung nguoiDung = nguoiDungRepository.findNguoiDungByMaNguoiDung(maNguoiDung);
        NguoiDung_VaiTro phanQuyen = nguoiDung_vaiTroRepository.findByNguoiDungId(nguoiDung.getIdNguoiDung());
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("phanQuyen", phanQuyen);
        return "admin/NhanVien/ThongTinNhanVien/thongTinNhanVien";
    }

//    @RequestMapping("admin/thongTinNhanVien/MaNhanVien={maNhanVien}")
//    public String thongTinNhanVien(@PathVariable("maNhanVien") String maNhanVien, Model model) {
//        NguoiDung nguoiDung = nguoiDungRepository.findNguoiDungByMaNguoiDung(maNhanVien);
//        NguoiDung_VaiTro phanQuyen = nguoiDung_vaiTroRepository.findByNguoiDungId(nguoiDung.getIdNguoiDung());
//        model.addAttribute("nguoiDung", nguoiDung);
//        model.addAttribute("phanQuyen", phanQuyen);
//        return "admin/NhanVien/ThongTinNhanVien/thongTinNhanVien";
//    }
    @RequestMapping("admin/thongTinNhanVien/MaNhanVien={maNhanVien}")
    public String thongTinNhanVien(@PathVariable("maNhanVien") String maNhanVien, Model model) {
        NguoiDung nguoiDung = nguoiDungRepository.findNguoiDungByMaNguoiDung(maNhanVien);
        NguoiDung_VaiTro phanQuyen = nguoiDung_vaiTroRepository.findByNguoiDungId(nguoiDung.getIdNguoiDung());
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("phanQuyen", phanQuyen);
        return "admin/NhanVien/ThongTinNhanVien/thongTinNhanVien";
    }

}
