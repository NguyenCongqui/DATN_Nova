package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.TimeLineDTO;
import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TimeLineService {

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

    @Autowired
    TrangThaiRepository trangThaiRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

//    public TimeLineDTO getTimeLineChoXacNhan(Integer idHoaDon) {
//        HoaDon hoaDon = hoaDonRepository.getById(idHoaDon);
//        TrangThai trangThai = trangThaiRepository.findById(1).get();
//        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
//        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(idHoaDon);
//        List<String> tenAnhChinhList = new ArrayList<>();
//        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
//            MauSac mauSac = hoadonCT.getChiTietSanPham().getMauSac();
//            Integer sanPhamId = hoadonCT.getChiTietSanPham().getSanPham().getIdSanPham();
//            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getIdMauSac(), sanPhamId);
//            tenAnhChinhList.add(tenAnhChinh);
//        }
//
//        List<LichSuHoaDon> lichSuSuaDoi = lichSuHoaDonRepository.findLichSuByHDID(hoaDon.getIdHoaDon());
//        List<LichSuHoaDon> TimeLineChoXacNhan = lichSuHoaDonRepository.getTimeLine(hoaDon.getIdHoaDon(), trangThai.getIdTrangThai());
//
//
//        TimeLineDTO dto = new TimeLineDTO();
//        dto.setHinhAnh(tenAnhChinhList);
//        dto.setTimeLineChoXacNhan(TimeLineChoXacNhan);
//        dto.setLichSuSuaDoi(lichSuSuaDoi);
//        dto.setHoaDon(hoaDon);
//        dto.setTrangThai(trangThai);
//        dto.setThaoTac("Tao don hang");
//        dto.setTenNguoiTao(hoaDon.getNguoiTao());
//
////        LichSuHoaDon ls = new LichSuHoaDon();
////        ls.setHoaDon(hoaDon);
////        ls.setNguoiTao(hoaDon.getNguoiTao());
////        ls.setNguoiThaoTac(hoaDon.getNguoiNhan());
////        ls.setTrangThaiID(trangThai.getIdTrangThai());
////        lichSuHoaDonRepository.save(ls);
//        return dto;
//    }
}
