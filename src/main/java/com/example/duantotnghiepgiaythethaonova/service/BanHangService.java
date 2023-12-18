package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.HoaDon;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface BanHangService {
    String getMauSac(Integer sanPhamId);

    //Bán hàng tại quầy
    ResponseEntity<String> updateXoaSanPhamBanHangTaiQuay(Integer id);

    ResponseEntity<String> updateSoLuongSanPhamBanHangTaiQuay(Integer id, int quantity);

    ResponseEntity<String> huyDonBanHangTaiQuay(Integer id);

    void thanhToanHoaDonBanHangTaiQuay(Integer id, BigDecimal tien_giam, String khuyenMai, BigDecimal tongTienHoaDon);

    ResponseEntity<Map<String, String>> themMaGiamGiaBanHangTaiQuay(String couponCode);

    ResponseEntity<Map<String, Integer>> taoHoaDonBanHangTaiQuay(HoaDon hoaDon);

    void themSanPhamVaoHoaDonBanHangTaiQuay(Integer kichThuocId, Integer mauSacId, Integer sanPhamId, int hoaDonID, Integer soLuongSanPham);

    ResponseEntity<Map<String, String>> themKhachHangVaoHoaDonTaiQuay(Integer IdHoaDon, Integer IDKhachHang, String TenKhachHang, String SDTKhachHang);

    //Bán hàng online
    void saveOrderBanHangOnline(BigDecimal totalAmount,
                                BigDecimal shippingFee,
                                BigDecimal tien_giam,
                                String tenGiamGia,
                                String emailNguoiNhan,
                                String diaChiGiaoHang,
                                String nguoiNhan,
                                String sdtNguoiNhan,
                                String ghiChu,
                                Integer id);


    ResponseEntity<Map<String, String>> themMaGiamGiaBanHangOnline(String couponCode);

    Integer  taoHoaDonBanHangOnline(List<Integer> selectedCartItemIds, RedirectAttributes redirectAttributes);

    void BanHangBanHangOnline(Integer id, Model model);

    void banHangBanHangTaiQuay(Integer id, SPAndSPCTSearchDto dataSearch, int pageHDCT, int sizeHDCT, Model model);

    //Mua ngay
    ResponseEntity<Integer> muanNgaySanPham(Integer sanPhamId, Integer mauSacId, Integer kichCoId, Integer soLuong);

    void saveOrderMuaNgay(BigDecimal totalAmount,
                                BigDecimal shippingFee,
                                BigDecimal tien_giam,
                                String tenGiamGia,
                                String emailNguoiNhan,
                                String diaChiGiaoHang,
                                String nguoiNhan,
                                String sdtNguoiNhan,
                                String ghiChu,
                          Integer id);
    List<String> kiemTraSoLuongHang(Integer idHoaDon);


}
