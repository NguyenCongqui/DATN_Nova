package com.example.duantotnghiepgiaythethaonova.controller.admin.BanHang.BanHangOnline;

import com.example.duantotnghiepgiaythethaonova.service.BanHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class banHangOnlineController {
    @Autowired
    BanHangService banHangService;

    @PostMapping("/khach-hang/gio-hang-chi-tiet/tao-hoa-don")
    public String taoHoaDon(@RequestBody List<Integer> selectedCartItemIds, RedirectAttributes redirectAttributes) {
        Integer hoaDonID = banHangService.taoHoaDonBanHangOnline(selectedCartItemIds, redirectAttributes);
        return "redirect:/khach-hang/checkout/" + hoaDonID;
    }

    @RequestMapping("khach-hang/checkout/{id}")
    public String banHangBanHangOnline(@PathVariable("id") Integer id, Model model) {
        banHangService.BanHangBanHangOnline(id, model);
        return "customer/BanHang/DatHang";
    }
}
