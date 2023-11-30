package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import com.example.duantotnghiepgiaythethaonova.service.EmailService;
import com.example.duantotnghiepgiaythethaonova.service.HoaDonCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonCustomerServiceImpl implements HoaDonCustomerService {
    @Autowired
    HoaDonRepoditory2 hoaDonRepoditory2;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    EmailService emailService;

    @Override
    public void danhSachChoGiaoHangCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> ChoGiaoHang = hoaDonRepoditory2.findHoaDonbyId(2, pageable);
        model.addAttribute("customerChoGiaoHang", ChoGiaoHang.getContent());
        model.addAttribute("pageChoGiaoHang", ChoGiaoHang.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietChoGiaoHangCustomer(Integer id, Model model) {
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
    public void danhSachChoXacNhanCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> choXacNhan = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(1, pageable);
        model.addAttribute("choXacNhanCustomer", choXacNhan.getContent());
        model.addAttribute("pageChoXacNhan", choXacNhan.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietChoXacNhanCustomer(Integer id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        List<GiaoDich> timeLineChoXacNhan = giaoDichRepository.findByTrangThaiIdAndHoaDonId(1, id);
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
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public ResponseEntity<String> updateHuyDonHangCustomer(Integer hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setIdTrangThai(5);
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("linh");
            hoaDonRepository.save(hoaDon);
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("ABC");
            gd.setNguoiTao("ABC");
            gd.setTrangThai(tt);
            giaoDichRepository.save(gd);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                gd.setNguoiDung(nguoiDung);
                giaoDichRepository.save(gd);
            }

            String emailNguoiNhan = hoaDon.getEmailNguoiNhan();

            try {
                emailService.sendMailDangGiaoHang(emailNguoiNhan, hoaDon);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            String message = "Hủy đơn thành công";
            return ResponseEntity.ok(message);

        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void danhSachDaGiaoCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> DaGiao = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(4, pageable);
        model.addAttribute("DaGiaoCustomer", DaGiao.getContent());
        model.addAttribute("pageDaGiao", DaGiao.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietDaGiaoCustomer(Integer id, Model model) {
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
    public void danhSachDaHuyCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> DaHuy = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(5, pageable);
        model.addAttribute("DaHuyCustomer", DaHuy.getContent());
        model.addAttribute("pageDaHuy", DaHuy.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietDaHuyCustomer(Integer id, Model model) {
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
        model.addAttribute("timeLineChoGiaoHang", timeLineChoGiaoHang);
        model.addAttribute("timeLineDangGiaoHang", timeLineDangGiaoHang);
        model.addAttribute("timeLineHuyDonHang", timeLineHuyDonHang);
        model.addAttribute("hoaDon", hoaDon);
    }

    @Override
    public void danhSachDangGiaoCustomer(int page, int size, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<HoaDon> dangtGiao = hoaDonRepoditory2.findByTrangThaiHoaDonListTrangThai(3, pageable);
        model.addAttribute("dangtGiaoCustomer", dangtGiao.getContent());
        model.addAttribute("pageDangGiao", dangtGiao.getTotalPages());
        model.addAttribute("page", page);
    }

    @Override
    public void chiTietDangGiaoCustomer(Integer id, Model model) {
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
    public ResponseEntity<String> updateGiaoHangThanhCongCustomer(Integer hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setIdTrangThai(4);
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("linh");
            hoaDonRepository.save(hoaDon);
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("linh");
            gd.setNguoiTao("linh");
            gd.setTrangThai(tt);
            giaoDichRepository.save(gd);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                gd.setNguoiDung(nguoiDung);
                giaoDichRepository.save(gd);
            }
            String emailNguoiNhan = hoaDon.getEmailNguoiNhan();

            try {
                emailService.sendMailDangGiaoHang(emailNguoiNhan, hoaDon);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            String message = "Đã xác nhận đơn hàng là đang giao";
            return ResponseEntity.ok(message);

        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> updateThanhCongAllCustomer() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(3);
        if (!hoaDonList.isEmpty()) {
            for (HoaDon hoaDon : hoaDonList) {
                TrangThai tt = new TrangThai();
                tt.setIdTrangThai(4);
                hoaDon.setTrangThai(tt);
                hoaDon.setNgayCapNhat(new Date());
                hoaDon.setNguoiCapNhat("linh");
                hoaDonRepository.save(hoaDon);
                GiaoDich gd = new GiaoDich();
                gd.setHoaDon(hoaDon);
                gd.setNgayCapNhat(new Date());
                gd.setNgayTao(new Date());
                gd.setNguoiCapNhat("linh");
                gd.setNguoiTao("linh");
                gd.setTrangThai(tt);
                giaoDichRepository.save(gd);

                String email = SecurityContextHolder.getContext().getAuthentication().getName();
                Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
                if (OptNguoiDung.isPresent()) {
                    NguoiDung nguoiDung = OptNguoiDung.get();
                    gd.setNguoiDung(nguoiDung);
                    giaoDichRepository.save(gd);
                }
                String emailNguoiNhan = hoaDon.getEmailNguoiNhan();

                try {
                    emailService.sendMailDangGiaoHang(emailNguoiNhan, hoaDon);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            
            String message = "Xác nhận tất cả thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn chưa xác nhận";
            return ResponseEntity.notFound().build();
        }
    }
}
