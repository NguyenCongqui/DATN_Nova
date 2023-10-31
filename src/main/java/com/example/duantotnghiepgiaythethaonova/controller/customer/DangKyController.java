package com.example.duantotnghiepgiaythethaonova.controller.customer;


import com.example.duantotnghiepgiaythethaonova.dto.KhachHangDTO;
import com.example.duantotnghiepgiaythethaonova.service.DiaChiService;
import com.example.duantotnghiepgiaythethaonova.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@Controller
public class DangKyController {

    @Autowired
    private KhachHangService khachHangService;


    @Autowired
    private DiaChiService diaChiService;

    @RequestMapping("/security/register/create")
    public String registerForm(Model model) {
        model.addAttribute("khachHangDTO", new KhachHangDTO());
        return "/customer/auth/register";
    }

    @SuppressWarnings("unlikely-arg-type")
    @PostMapping("/security/register/create")
    public String register(@Validated @ModelAttribute("khachHangDTO") KhachHangDTO khachHangDTO, BindingResult result,
                           Model md) {
        if (result.hasErrors()) {
            return "/customer/auth/register";
        } else {
            if (khachHangService.findByEmail(khachHangDTO.getEmail()) != null) {
                if (khachHangService.findByEmailAndTrangThai(khachHangDTO.getEmail(), 0) != null) {
                    md.addAttribute("messageError", "Tài khoản đã bị vô hiệu hóa vui lòng liên hệ 0368028006 !");
                    return "/customer/auth/register";
                } else if (khachHangService.findByEmailAndTrangThai(khachHangDTO.getEmail(), 1) != null) {
                    md.addAttribute("messageError", "Email đã được đăng ký !");
                    return "/customer/auth/register";
                }
            } else {
                if (!khachHangDTO.getEmail().equals(khachHangService.findByEmail(khachHangDTO.getEmail()))) {
                    khachHangService.register(khachHangDTO);
                    khachHangDTO.setId(khachHangService.findByEmail(khachHangDTO.getEmail()).getId());
                    diaChiService.save(khachHangDTO);
                    md.addAttribute("message", "Đăng ký thành công !");
                }
            }
            return "/customer/auth/register";
        }
    }


}
