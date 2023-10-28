package com.example.duantotnghiepgiaythethaonova.restController.BanHang.GioHangRest;

import com.example.duantotnghiepgiaythethaonova.repository.CTSPRepository;
import com.example.duantotnghiepgiaythethaonova.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GioHangRestController {
    @Autowired
    CTSPRepository sanPhamChiTietRepository;

    @Autowired(required = false)
    GioHangService gioHangService;

    @RequestMapping("/khachhang/SoLuongSanPhamChiTiet")
    public Map<String, Object> laySoLuongSanPhamChiTiet(@RequestParam("tenKichCo") String tenKichCo,
                                                        @RequestParam("mauSacId") Integer mauSacId,
                                                        @RequestParam("sanPhamId") Integer sanPhamId) {
        Map<String, Object> response = new HashMap<>();

        Integer soLuongSanPhamChiTiet = sanPhamChiTietRepository.laySoLuongSanPhamChiTiet(tenKichCo, mauSacId, sanPhamId);
        response.put("soLuongSanPhamChiTiet", soLuongSanPhamChiTiet);
        return response;
    }

    @PostMapping("/khachhang/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam("sanPhamId") Integer sanPhamId,
                                            @RequestParam("mauSacId") Integer mauSacId,
                                            @RequestParam("kichCoId") Integer kichCoId,
                                            @RequestParam("soLuong") Integer soLuong) {

        return gioHangService.addToCart(sanPhamId, mauSacId, kichCoId, soLuong);
    }

    @RequestMapping("/khachhang/gio-hang-chi-tiet/xoa-sach-gio-hang")
    public @ResponseBody Map<String, Object> xoaSachGioHang(@RequestParam Integer id) {
        Map<String, Object> response = new HashMap<>();
        try {
            gioHangService.xoaSachGioHang(id);
            // Gửi phn hồi thành công về cho AJAX
            response.put("success", true);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            response.put("success", false);
            response.put("error", "Giỏ hàng không tồn tại");
        }
        return response;
    }
}
