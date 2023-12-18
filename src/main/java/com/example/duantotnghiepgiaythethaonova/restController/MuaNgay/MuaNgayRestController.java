package com.example.duantotnghiepgiaythethaonova.restController.MuaNgay;

import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import com.example.duantotnghiepgiaythethaonova.repository.KichCoRepository;
import com.example.duantotnghiepgiaythethaonova.repository.MauSacRepository;
import com.example.duantotnghiepgiaythethaonova.service.BanHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MuaNgayRestController {
    @Autowired
    BanHangService banHangService;

    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    KichCoRepository kichCoRepository;

    @PostMapping("/mua-ngay/checkout")
    public ResponseEntity<Integer> MuaNgaySanPham(@RequestParam("sanPhamId") Integer sanPhamId,
                                               @RequestParam("mauSacId") String mauSacId,
                                               @RequestParam("kichCoId") String kichCoId,
                                               @RequestParam("soLuong") Integer soLuong) {
        Optional<MauSac> optMauSac = mauSacRepository.finMauSacByMa(mauSacId);
        Optional<KichCo> optKichCO = kichCoRepository.findByTenKichCo(kichCoId);
        Integer id_kichCo = optKichCO.get().getIdKichCo();
        Integer id_Ms = optMauSac.get().getIdMauSac();
        return banHangService.muanNgaySanPham(sanPhamId, id_Ms, id_kichCo, soLuong);
    }

    @PostMapping("mua-ngay/save-order/{id}")
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
            banHangService.saveOrderMuaNgay(totalAmount, shippingFee, tien_giam, tenGiamGia, emailNguoiNhan, diaChiGiaoHang, nguoiNhan, sdtNguoiNhan, ghiChu, id);

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
}
