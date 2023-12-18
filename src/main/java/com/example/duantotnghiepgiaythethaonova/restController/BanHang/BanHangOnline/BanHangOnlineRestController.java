package com.example.duantotnghiepgiaythethaonova.restController.BanHang.BanHangOnline;

import com.example.duantotnghiepgiaythethaonova.service.BanHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BanHangOnlineRestController {
    @Autowired
    BanHangService banHangService;

    @PostMapping("/save-order/{id}")
    public @ResponseBody Map<String, Object> saveOrder(@RequestParam("totalAmount") BigDecimal totalAmount,
                                                       @RequestParam("shippingFee") BigDecimal shippingFee,
                                                       @RequestParam("tien_giam") BigDecimal tien_giam,
                                                       @RequestParam String tenGiamGia,
                                                       @RequestParam("emailNguoiNhan") String emailNguoiNhan,
                                                       @RequestParam("diaChiGiaoHang") String diaChiGiaoHang,
                                                       @RequestParam("nguoiNhan") String nguoiNhan,
                                                       @RequestParam("sdtNguoiNhan") String sdtNguoiNhan,
                                                       @RequestParam("ghiChu") String ghiChu,
                                                       @PathVariable("id") Integer id) {
        try {
            // Kiểm tra số lượng trước khi lưu thanh toán
            List<String> thongBao = banHangService.kiemTraSoLuongHang(id);
            if (!thongBao.isEmpty()) {
                // Nếu có vấn đề với số lượng sản phẩm, trả về thông báo lỗi
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("message", String.join("\n", thongBao));
                return errorResponse;
            }

            // Lưu thanh toán
            banHangService.saveOrderBanHangOnline(totalAmount, shippingFee, tien_giam, tenGiamGia, emailNguoiNhan, diaChiGiaoHang, nguoiNhan, sdtNguoiNhan, ghiChu, id);


            // Trả về phản hồi thành công
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("success", true);
            return successResponse;
        } catch (RuntimeException e) {
            // Xử lý ngoại lệ, trả về thông báo lỗi
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", e.getMessage());
            return errorResponse;
        }
    }

    @PostMapping("/banHang/themMaGiamGiaOnline")
    public ResponseEntity<Map<String, String>> themMaGiamGia(@RequestParam String couponCode) {
        return banHangService.themMaGiamGiaBanHangOnline(couponCode);
    }

    @GetMapping("/khach-hang/kiem-tra-so-luong/{idHoaDon}")
    public ResponseEntity<Map<String, Object>> kiemTraSoLuongSanPham(@PathVariable("idHoaDon") Integer idHoaDon) {
        try {
            List<String> thongBao = banHangService.kiemTraSoLuongHang(idHoaDon);
            Map<String, Object> response = new HashMap<>();
            response.put("thongBao", thongBao);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


}
