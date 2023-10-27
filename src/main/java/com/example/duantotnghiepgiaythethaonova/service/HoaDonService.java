package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.HoaDon;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface HoaDonService {
    List<HoaDon> getAll();

    Integer getMaxId();

    //Chỉnh sửa hóa đơn
    void capNhatTrangThaiHuyDon(String maDonHang);

    ResponseEntity<String> xoaHoaDonCTChinhSuaHoaDon(Integer hoaDonCTId);

    ResponseEntity<String> chinhSuaSoLuongSanPhamChinhSuaHoaDon(Integer id, int quantity);

    void ThemSanPhamVaoHoaDonChoXacNhanChinhSuaHoaDon(Integer kichThuocId, Integer mauSacId, Integer sanPhamId, int hoaDonID, Integer soLuongSanPham);

    void XoaHoaDonCXNChinhSuaHoaDon(Integer hoaDonID);

    void ChinhSuaHoaDonView(Integer hoaDonId, SPAndSPCTSearchDto dataSearch, Optional<Integer> page, Optional<Integer> size, Model model, Optional<String> messageSuccess, Optional<String> messageDanger);

    //Chờ giao hàng
    ResponseEntity<String> updateGiaoHangChoGiaoHang(Integer hoaDonId);

    ResponseEntity<String> updateGiaoHangAllChoGiaoHang();

    //Chờ xác nhận
    ResponseEntity<String> updateXacNhanChoXacNhan(Integer hoaDonId);

    ResponseEntity<String> updateHuyDonChoXacNhan(Integer hoaDonId);

    ResponseEntity<String> updateXacNhanAllChoXacNhan();

    ResponseEntity<String> updateHuyAllChoXacNhan();

    //Đang giao hàng
    ResponseEntity<String> updateGiaoHangThanhCongDangGiaoHang(Integer hoaDonId);

    ResponseEntity<String> updateThanhCongAllDangGiaoHang();
}
