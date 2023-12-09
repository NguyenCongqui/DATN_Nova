package com.example.duantotnghiepgiaythethaonova.restController.BanHang.GioHangRest;

import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import com.example.duantotnghiepgiaythethaonova.repository.CTSPRepository;
import com.example.duantotnghiepgiaythethaonova.repository.KichCoRepository;
import com.example.duantotnghiepgiaythethaonova.repository.MauSacRepository;
import com.example.duantotnghiepgiaythethaonova.service.GioHangService;
import org.springframework.beans.BeanInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class GioHangRestController {
    @Autowired
    CTSPRepository sanPhamChiTietRepository;

    @Autowired
    GioHangService gioHangService;

    @Autowired
    MauSacRepository mauSacRepository;

    @Autowired
    KichCoRepository kichCoRepository;

    @RequestMapping("/khachhang/SoLuongSanPhamChiTiet")
    public Map<String, Object> laySoLuongSanPhamChiTiet(@RequestParam("tenKichCo") String tenKichCo,
                                                        @RequestParam("mauSacId") String mauSacId,
                                                        @RequestParam("sanPhamId") Integer sanPhamId) {
        Map<String, Object> response = new HashMap<>();

        Optional<MauSac> optMauSac = mauSacRepository.finMauSacByMa(mauSacId);
        Optional<KichCo> optKichCO = kichCoRepository.findByTenKichCo(tenKichCo);
        if(optKichCO.isPresent() && optMauSac.isPresent()){
            MauSac mauSac = optMauSac.get();
            KichCo kichCo = optKichCO.get();
            Integer soLuongSanPhamChiTiet = sanPhamChiTietRepository.laySoLuongSanPhamChiTiet(tenKichCo, mauSac.getIdMauSac(), sanPhamId);
            System.out.println("bcjds"+sanPhamChiTietRepository.laySoLuongSanPhamChiTiet(tenKichCo, mauSac.getIdMauSac(), sanPhamId));
            response.put("soLuongSanPhamChiTiet", soLuongSanPhamChiTiet);
            return response;
        }
        return null;
    }

    @RequestMapping("/GiaBan")
    public Map<String, Object> layGiaBanSanPhamChiTiet(@RequestParam("tenKichCo") String tenKichCo,
                                                        @RequestParam("mauSacId") String mauSacId,
                                                        @RequestParam("sanPhamId") Integer sanPhamId) {
        Map<String, Object> response = new HashMap<>();

        Optional<MauSac> optMauSac = mauSacRepository.finMauSacByMa(mauSacId);
        Optional<KichCo> optKichCO = kichCoRepository.findByTenKichCo(tenKichCo);
        System.out.println("jajajajaja");
        if(optKichCO.isPresent() && optMauSac.isPresent()){
            MauSac mauSac = optMauSac.get();
            KichCo kichCo = optKichCO.get();
            BigDecimal giaBanSanPhamChiTiet = sanPhamChiTietRepository.layGiaBanSanPhamChiTiet(tenKichCo, mauSac.getIdMauSac(), sanPhamId);
            System.out.println(sanPhamChiTietRepository.layGiaBanSanPhamChiTiet(tenKichCo, mauSac.getIdMauSac(), sanPhamId));
            System.out.println("jhdsknsdlkv");
            String giaBanFormatted = giaBanSanPhamChiTiet != null ? giaBanSanPhamChiTiet.setScale(0).toString() : "0";
            response.put("giaBanSanPhamChiTiet", giaBanFormatted);
//            BigDecimal giaBan = sanPhamChiTietService.getTienBan(sp.getIdSanPham());
//            dto.setGiaBan(giaBan);
            return response;
        }
        return null;
    }

    @PostMapping("/khachhang/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam("sanPhamId") Integer sanPhamId,
                                            @RequestParam("mauSacId") String mauSacId,
                                            @RequestParam("kichCoId") String kichCoId,
                                            @RequestParam("soLuong") Integer soLuong) {

        Optional<MauSac> optMauSac = mauSacRepository.finMauSacByMa(mauSacId);
        Optional<KichCo> optKichCO = kichCoRepository.findByTenKichCo(kichCoId);
        Integer id_kichCo = optKichCO.get().getIdKichCo();
        Integer id_Ms = optMauSac.get().getIdMauSac();
        return gioHangService.addToCart(sanPhamId, id_Ms, id_kichCo, soLuong);
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
