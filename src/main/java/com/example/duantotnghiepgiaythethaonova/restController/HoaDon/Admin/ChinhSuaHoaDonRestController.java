package com.example.duantotnghiepgiaythethaonova.restController.HoaDon.Admin;

import com.example.duantotnghiepgiaythethaonova.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ChinhSuaHoaDonRestController {
    @Autowired
    HoaDonService hoaDonService;

    @Transactional
    @RequestMapping("/admin/xoaHoaDonChiTiet/{id}")
    public ResponseEntity<String> xoaHoaDonCT(@PathVariable("id") Integer hoaDonCTId) {
        return hoaDonService.xoaHoaDonCTChinhSuaHoaDon(hoaDonCTId);
    }

    @PostMapping("/admin/chinhSuaHoaDon/update-SoLuong/{id}")
    public ResponseEntity<String> chinhSuaSoLuongSanPhamHDCT(@PathVariable("id") Integer id, @RequestParam("quantity") int quantity) {
        return hoaDonService.chinhSuaSoLuongSanPhamChinhSuaHoaDon(id, quantity);
    }

    @PostMapping("/HoaDon/themSanPhamVaoHoaDonChoXacNhan")
    public @ResponseBody Map<String, Object> ThemSanPhamVaoHoaDonChoXacNhan(@RequestParam("kichThuocId") Integer kichThuocId, @RequestParam("mauSacId") Integer mauSacId, @RequestParam("sanPhamId") Integer sanPhamId, @RequestParam("hoaDonId") int hoaDonID, @RequestParam("soLuongSanPham") Integer soLuongSanPham) {
        Map<String, Object> response = new HashMap<>();
        try {
            hoaDonService.ThemSanPhamVaoHoaDonChoXacNhanChinhSuaHoaDon(kichThuocId, mauSacId, sanPhamId, hoaDonID, soLuongSanPham);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Hóa đơn không tồn tại");
        }
        return response;
    }

    @RequestMapping("/ChinhSuaHoaDon/XoaHoaDon")
    public @ResponseBody Map<String, Object> XoaHoaDonCXN(@RequestParam("hoaDonID") Integer hoaDonID) {
        Map<String, Object> response = new HashMap<>();
        try {
            hoaDonService.XoaHoaDonCXNChinhSuaHoaDon(hoaDonID);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Hóa đơn không tồn tại");
        }
        return response;
    }
}
