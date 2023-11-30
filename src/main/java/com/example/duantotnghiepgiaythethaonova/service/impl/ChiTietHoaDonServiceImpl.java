package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import com.example.duantotnghiepgiaythethaonova.service.ChiTietHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Override
    public void choXacNhan(Integer id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();

        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getChiTietSanPham().getMauSac();
            Integer sanPhamId = hoadonCT.getChiTietSanPham().getSanPham().getIdSanPham();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getIdMauSac(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }
        model.addAttribute("tenAnhChinhList", tenAnhChinhList);

        List<LichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);

        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);

        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void choGiaoHang(Integer id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getChiTietSanPham().getMauSac();
            Integer sanPhamId = hoadonCT.getChiTietSanPham().getSanPham().getIdSanPham();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getIdMauSac(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }

        List<LichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);

        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void dangGiaoHang(Integer id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getChiTietSanPham().getMauSac();
            Integer sanPhamId = hoadonCT.getChiTietSanPham().getSanPham().getIdSanPham();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getIdMauSac(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }

        List<LichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);

        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void daGiaoHang(Integer id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        List<GiaoDich> timeLineDaGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(4, id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getChiTietSanPham().getMauSac();
            Integer sanPhamId = hoadonCT.getChiTietSanPham().getSanPham().getIdSanPham();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getIdMauSac(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }

        List<LichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);

        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineDaGiaoHang", timeLineDaGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void daHuy(Integer id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
        List<GiaoDich> timeLineChoGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(2, id);
        List<GiaoDich> timeLineDangGiaoHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(3, id);
        List<GiaoDich> timeLineHuyDonHang = giaoDichRepository.findByTrangThaiIdAndHoaDonId(5, id);
        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getChiTietSanPham().getMauSac();
            Integer sanPhamId = hoadonCT.getChiTietSanPham().getSanPham().getIdSanPham();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getIdMauSac(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }

        List<LichSuHoaDon> lichSuHD = lichSuHoaDonRepository.findLichSuByHDID(id);
        model.addAttribute("lichSuHD", lichSuHD);

        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
        model.addAttribute("timeLineChoXacNhan", timeLineChoXacNhan);
        model.addAttribute("timeLineHuyDonHang", timeLineHuyDonHang);
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("hoaDon", hoaDon);
    }
}
