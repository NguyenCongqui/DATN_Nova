package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import com.example.duantotnghiepgiaythethaonova.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class BanHangServiceIpml implements BanHangService {
    private final BanHangRepository banHangRepository;

    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    CTSPRepository sanPhamChiTietRepository;

    @Autowired
    TrangThaiService trangThaiService;

    @Autowired
    CTSPService sanPhamChiTietService;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;

    @Autowired
    DiaChiRepository diaChiRepository;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository1;



    public BanHangServiceIpml(BanHangRepository banHangRepository) {
        this.banHangRepository = banHangRepository;
    }

    @Override
    public String getMauSac(Integer sanPhamId) {
        return banHangRepository.getMauSac(sanPhamId);
    }

    @Override
    public void saveOrderBanHangOnline(BigDecimal totalAmount, BigDecimal shippingFee, BigDecimal tien_giam, String tenGiamGia, String emailNguoiNhan, String diaChiGiaoHang, String nguoiNhan, String sdtNguoiNhan, String ghiChu, Integer id) {
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(id);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (optHoaDon.isPresent()) {

            List<String> thongBaoBanHangOnline = kiemTraSoLuongHang(id);
            if (thongBaoBanHangOnline != null && !thongBaoBanHangOnline.isEmpty()) {
                // Nếu có vấn đề với số lượng sản phẩm, xử lý hoặc ném ngoại lệ tùy thuộc vào yêu cầu
                // Ví dụ: nếu sử dụng ngoại lệ
                throw new RuntimeException(String.join("\n", thongBaoBanHangOnline));
            }
            Optional<KhuyenMai> optionalKhuyenMai = khuyenMaiRepository.findKhuyenMaiByTenKhuyenMai(tenGiamGia);
            if (optionalKhuyenMai.isPresent()) {
                KhuyenMai khuyenMai = optionalKhuyenMai.get();
                HoaDon hoaDon = optHoaDon.get();
                if (tenGiamGia != null) {
                    hoaDon.setKhuyenMai(khuyenMai);
                } else {
                    hoaDon.setKhuyenMai(null);
                }
            }



            //LƯU HÓA ĐƠN
            HoaDon hoaDon = optHoaDon.get();
            if (tien_giam != null) {
                hoaDon.setTienGiam(tien_giam);
            } else {
                hoaDon.setTienGiam(BigDecimal.ZERO);
            }
            TrangThai tt = new TrangThai();
            tt.setIdTrangThai(1);
            hoaDon.setTrangThai(tt);
            hoaDon.setNguoiNhan(nguoiNhan);
            hoaDon.setSdtNguoiNhan(sdtNguoiNhan);
            hoaDon.setGhiChu(ghiChu);
            hoaDon.setDiaChiGiaoHang(diaChiGiaoHang);
            hoaDon.setLoaiHoaDon(0);
            hoaDon.setTongTienHoaDon(totalAmount);
            hoaDon.setTienShip(shippingFee);
            hoaDon.setEmailNguoiNhan(emailNguoiNhan);
            hoaDon.setDaXoa(false);
            hoaDonRepository.save(hoaDon);

            System.out.println(hoaDonRepository.save(hoaDon));

            List<HoaDonChiTiet> hoaDonChiTiets = optHoaDon.get().getHoaDonChiTiets();

            // Lặp qua danh sách hoaDonChiTiets và xóa từng bản ghi trong bảng gio_hang_chi_tiet
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
                Integer sanPhamChiTietId = hoaDonChiTiet.getChiTietSanPham().getIdCTSP();
                gioHangChiTietRepository.xoaGioHangChiTiet(sanPhamChiTietId);
            }

            //LƯU TIMELINE
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("Linh Update");
            gd.setNguoiTao("Linh Create");
            gd.setTrangThai(tt);
            giaoDichRepository.save(gd);


            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                gd.setNguoiDung(nguoiDung);
                giaoDichRepository.save(gd);

                //Lưu lịch sử hóa đơn
                LichSuHoaDon ls = new LichSuHoaDon();
                ls.setNguoiThaoTac(nguoiDung.getTenNguoiDung());
                ls.setHoaDon(hoaDon);
                ls.setThaoTac("Đặt hàng thanh toán khi nhận hàng");
                lichSuHoaDonRepository.save(ls);
            }

            List<HoaDonChiTiet> hoaDonChiTiets1 = hoaDonChiTietRepository.findByHoaDonIdAndDaXoa(id);

            for (HoaDonChiTiet gioHangChiTiet : hoaDonChiTiets1) {

                ChiTietSanPham sanPhamChiTiet = gioHangChiTiet.getChiTietSanPham();
                Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
                Integer soLuongNhapVao = gioHangChiTiet.getSoLuong();
                Integer soLuongcapNhat = soLuongSPCTBanDau - soLuongNhapVao;
                sanPhamChiTiet.setSoLuong(soLuongcapNhat);
                sanPhamChiTietRepository.save(sanPhamChiTiet);
            }

            if (email == null) {
                gd.setNguoiTao(nguoiNhan);
                giaoDichRepository.save(gd);
            }

            try {
                emailService.sendOrderConfirmationEmail(emailNguoiNhan, hoaDon);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> themMaGiamGiaBanHangOnline(String couponCode) {
        Optional<KhuyenMai> optkhuyenMai = khuyenMaiRepository.findKhuyenMaiByTenKhuyenMai(couponCode);
        if (optkhuyenMai.isPresent()) {
            KhuyenMai khuyenMai = optkhuyenMai.get();
            Integer tienGiam = khuyenMai.getPhanTramGiam();
            Integer tienGiamToiDa = khuyenMai.getGiaTriToiThieu();
            String tenGiamGia = khuyenMai.getTenKhuyenMai();
            Date ngayKetThuc = khuyenMai.getNgayKetThuc();
            Date ngayBatDau= khuyenMai.getNgayBatDau();

            Map<String, String> response = new HashMap<>();
            response.put("tenGiamGia", tenGiamGia.toString());
            response.put("tienGiamToiDa", tienGiamToiDa.toString());
            response.put("tienGiam", tienGiam.toString());
            response.put("ngayKetThuc", ngayKetThuc.toString());
            response.put("ngayBatDau", ngayBatDau.toString());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public Integer taoHoaDonBanHangOnline(List<Integer> selectedCartItemIds, RedirectAttributes redirectAttributes) {
        List<GioHangChiTiet> selectedCartItemDetails = new ArrayList<>();

        Optional<GioHangChiTiet> optionalCartItemDetail = null;
        for (Integer cartItemId : selectedCartItemIds) {
            optionalCartItemDetail = gioHangChiTietRepository.findById(cartItemId);
            if (optionalCartItemDetail.isPresent()) {
                selectedCartItemDetails.add(optionalCartItemDetail.get());
            }
        }

        HoaDon hoaDon = new HoaDon();
        Integer maxId = hoaDonService.getMaxId();
        int idMax;
        String ma;

        if (maxId != null) {
            idMax = maxId + 1;
        } else {
            idMax = 1;
        }

        DecimalFormat df = new DecimalFormat("00");
        String formattedId = df.format(idMax);
        ma = "HD" + formattedId;

        hoaDon.setMaDon(ma);
        hoaDon.setNgayTao(new Date());
        hoaDon.setNguoiTao("quinc");
        hoaDon.setKhachHang(optionalCartItemDetail.get().getGioHang().getKhachHang());
        hoaDon.setLoaiHoaDon(2);
        hoaDon.setDaXoa(false);
        hoaDonRepository.save(hoaDon);

        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();

        BigDecimal tongTienDonhang = BigDecimal.ZERO;

        for (GioHangChiTiet gioHangChiTiet : selectedCartItemDetails) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setChiTietSanPham(gioHangChiTiet.getChiTietSanPham());
            hoaDonChiTiet.setSoLuong(gioHangChiTiet.getSoLuong());
            hoaDonChiTiet.setDonGia(gioHangChiTiet.getChiTietSanPham().getGia());
            hoaDonChiTiet.setTongTien(gioHangChiTiet.getThanhTien());
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setDaXoa(false);
            hoaDonChiTietList.add(hoaDonChiTiet);
            hoaDonChiTietRepository2.save(hoaDonChiTiet);

            tongTienDonhang = tongTienDonhang.add(gioHangChiTiet.getThanhTien()); // Cộng dồn tổng tiền đơn hàng
        }
        hoaDon.setTongTienDonHang(tongTienDonhang);
        hoaDon.setHoaDonChiTiets(hoaDonChiTietList);
        hoaDonRepository.save(hoaDon);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);

        if (OptNguoiDung.isPresent()) {
            NguoiDung nguoiDung = OptNguoiDung.get();
            //Lưu lịch sử hóa đơn
            LichSuHoaDon ls = new LichSuHoaDon();
            ls.setNguoiThaoTac(nguoiDung.getTenNguoiDung());
            ls.setHoaDon(hoaDon);
            ls.setThaoTac("Tạo đơn hàng");
            lichSuHoaDonRepository.save(ls);
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Chi tiết hóa đơn đã được lưu thành công");
        return hoaDon.getIdHoaDon();
    }

    @Override
    public void BanHangBanHangOnline(Integer id, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        model.addAttribute("hoaDon", hoaDon);
        KhachHang khachHang = hoaDon.getKhachHang();
        if (khachHang != null) {
            int idKhachHang = khachHang.getIdKhachHang();
            DiaChi diaChi = diaChiRepository.findDiaChiByKhachHang(idKhachHang);
            model.addAttribute("diaChi", diaChi);
        }

        // Giảm giá
        List<KhuyenMai> giamGia = khuyenMaiRepository.getAllKhuyenMai();
        model.addAttribute("giamGia", giamGia);

        List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCT(id);
        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet);

        // Lấy danh sách ảnh chính của tất cả sản phẩm và lưu vào List
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoadonCT : hoaDonChiTiet) {
            MauSac mauSac = hoadonCT.getChiTietSanPham().getMauSac();
            Integer sanPhamId = hoadonCT.getChiTietSanPham().getSanPham().getIdSanPham();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getIdMauSac(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }

        // Lưu danh sách tên ảnh chính vào model để sử dụng trong template
        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
    }

    @Override
    public void banHangBanHangTaiQuay(Integer id, SPAndSPCTSearchDto dataSearch, int pageHDCT, int sizeHDCT, Model model) {
        HoaDon hoaDon = hoaDonRepository.findById(id).get();
        model.addAttribute("hoaDon", hoaDon);

        // HÓA ĐƠN CHI TIẾT VÀ PHÂN TRANG HÓA ĐƠN CHI TIẾT
        PageRequest pageableHDCT = PageRequest.of(pageHDCT - 1, sizeHDCT);
        Page<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository2.findHDCTByHoaDonId(id, pageableHDCT);
        model.addAttribute("hoaDonChiTiet", hoaDonChiTiet.getContent());
        System.out.println("id hoa don ct"+hoaDonChiTiet.getContent());
        System.out.println("id hoa don"+ hoaDonRepository.findById(id));
        System.out.println(hoaDonChiTiet);
        model.addAttribute("pageHoaDonChiTiet", hoaDonChiTiet.getTotalPages());
        model.addAttribute("pageHDCT", pageHDCT);
        model.addAttribute("sizeHDCT", sizeHDCT);

        // Lấy danh sách tên ảnh chính của tất cả sản phẩm và lưu vào List
        List<String> tenAnhChinhList = new ArrayList<>();
        for (HoaDonChiTiet hoaDonCT : hoaDonChiTiet.getContent()) {
            MauSac mauSac = hoaDonCT.getChiTietSanPham().getMauSac();
            Integer sanPhamId = hoaDonCT.getChiTietSanPham().getSanPham().getIdSanPham();

            String tenAnhChinh = hinhAnhRepository.findTenAnhChinhByMauSacIdAndSanPhamId(mauSac.getIdMauSac(), sanPhamId);
            tenAnhChinhList.add(tenAnhChinh);
        }

        // Lưu danh sách tên ảnh chính vào model để sử dụng trong template
        model.addAttribute("tenAnhChinhList", tenAnhChinhList);
    }

    @Override
    public ResponseEntity<Integer> muanNgaySanPham(Integer sanPhamId, Integer mauSacId, Integer kichCoId, Integer soLuong) {
        HoaDon hoaDon = new HoaDon();
        Integer maxId = hoaDonService.getMaxId();
        int idMax;
        String ma;

        if (maxId != null) {
            idMax = maxId + 1;
        } else {
            idMax = 1;
        }

        DecimalFormat df = new DecimalFormat("00");
        String formattedId = df.format(idMax);
        ma = "HD" + formattedId;

        hoaDon.setMaDon(ma);
        hoaDon.setNgayTao(new Date());
        hoaDon.setNguoiTao("Linh create");
        hoaDon.setLoaiHoaDon(2);
        hoaDon.setDaXoa(false);
        hoaDonRepository.save(hoaDon);

        List<HoaDonChiTiet> hoaDonChiTietList = new ArrayList<>();

        BigDecimal tongTienDonhang = BigDecimal.ZERO;
//        Integer soLuongBanDau = sanPhamChiTietRepository1.laySoLuongChiTietSanPham2(kichCoId, mauSacId, sanPhamId);

        Optional<ChiTietSanPham> optionalSanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietByMauSacSizeSanPhamId(sanPhamId, mauSacId, kichCoId);
        if (optionalSanPhamChiTiet.isPresent()) {
            ChiTietSanPham sanPhamChiTiet = optionalSanPhamChiTiet.get();
            BigDecimal tongTienSanPham = sanPhamChiTiet.getGia();
            BigDecimal soLuongDecimal = BigDecimal.valueOf(soLuong);
            tongTienDonhang = tongTienSanPham.multiply(soLuongDecimal);
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setChiTietSanPham(sanPhamChiTiet);
            hoaDonChiTiet.setSoLuong(soLuong);
            hoaDonChiTiet.setDonGia(sanPhamChiTiet.getGia());
            hoaDonChiTiet.setTongTien(tongTienDonhang);
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setDaXoa(false);
            hoaDonChiTietList.add(hoaDonChiTiet);
            hoaDonChiTietRepository2.save(hoaDonChiTiet);


//            Integer soLuongThemVao = soLuong;
//            Integer soLuongCapNhat = soLuongBanDau - soLuongThemVao;
//            sanPhamChiTiet.setSoLuong(soLuongCapNhat);
//            sanPhamChiTietRepository.save(sanPhamChiTiet);
        }

        hoaDon.setTongTienDonHang(tongTienDonhang);
        hoaDon.setHoaDonChiTiets(hoaDonChiTietList);
        hoaDonRepository.save(hoaDon);




        //Lưu lịch sử hóa đơn
        LichSuHoaDon ls = new LichSuHoaDon();
        ls.setHoaDon(hoaDon);
        ls.setThaoTac("Tạo đơn hàng");
        lichSuHoaDonRepository.save(ls);

        return ResponseEntity.ok().body(hoaDon.getIdHoaDon());
    }

    @Override
    public void saveOrderMuaNgay(BigDecimal totalAmount, BigDecimal shippingFee, BigDecimal tien_giam, String tenGiamGia, String emailNguoiNhan, String diaChiGiaoHang, String nguoiNhan, String sdtNguoiNhan, String ghiChu, Integer id) {
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(id);
        if (optHoaDon.isPresent()) {
            List<String> thongBao = kiemTraSoLuongHang(id);
            if (thongBao != null && !thongBao.isEmpty()) {
                // Nếu có vấn đề với số lượng sản phẩm, xử lý hoặc ném ngoại lệ tùy thuộc vào yêu cầu
                // Ví dụ: nếu sử dụng ngoại lệ
                throw new RuntimeException(String.join("\n", thongBao));
            }
            Optional<KhuyenMai> optionalKhuyenMai = khuyenMaiRepository.findKhuyenMaiByTenKhuyenMai(tenGiamGia);
            if (optionalKhuyenMai.isPresent()) {
                KhuyenMai khuyenMai = optionalKhuyenMai.get();
                HoaDon hoaDon = optHoaDon.get();
                if (tenGiamGia != null) {
                    hoaDon.setKhuyenMai(khuyenMai);
                } else {
                    hoaDon.setKhuyenMai(null);
                }
            }

            //LƯU HÓA ĐƠN
            HoaDon hoaDon = optHoaDon.get();
            if (tien_giam != null) {
                hoaDon.setTienGiam(tien_giam);
            } else {
                hoaDon.setTienGiam(BigDecimal.ZERO);
            }
            TrangThai tt = new TrangThai();
            tt.setIdTrangThai(1);
            hoaDon.setTrangThai(tt);
            hoaDon.setNguoiNhan(nguoiNhan);
            hoaDon.setSdtNguoiNhan(sdtNguoiNhan);
            hoaDon.setGhiChu(ghiChu);
            hoaDon.setDiaChiGiaoHang(diaChiGiaoHang);
            hoaDon.setLoaiHoaDon(0);
            hoaDon.setTongTienHoaDon(totalAmount);
            hoaDon.setTienShip(shippingFee);
            hoaDon.setEmailNguoiNhan(emailNguoiNhan);
            hoaDon.setDaXoa(false);
            hoaDonRepository.save(hoaDon);

            List<HoaDonChiTiet> hoaDonChiTiets = optHoaDon.get().getHoaDonChiTiets();

            // Lặp qua danh sách hoaDonChiTiets và xóa từng bản ghi trong bảng gio_hang_chi_tiet
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
                Integer sanPhamChiTietId = hoaDonChiTiet.getChiTietSanPham().getIdCTSP();
                gioHangChiTietRepository.xoaGioHangChiTiet(sanPhamChiTietId);
            }

            List<HoaDonChiTiet> hoaDonChiTiets1 = hoaDonChiTietRepository.findByHoaDonIdAndDaXoa(id);

            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets1) {

                ChiTietSanPham sanPhamChiTiet = hoaDonChiTiet.getChiTietSanPham();
                Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
                Integer soLuongTrongHoaDon = hoaDonChiTiet.getSoLuong();
                Integer soLuongcapNhat = soLuongSPCTBanDau - soLuongTrongHoaDon;
                sanPhamChiTiet.setSoLuong(soLuongcapNhat);
                sanPhamChiTietRepository.save(sanPhamChiTiet);
            }

            //LƯU TIMELINE
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNgayCapNhat(new Date());
            gd.setNguoiTao(nguoiNhan);
            gd.setTrangThai(tt);
            giaoDichRepository.save(gd);

            //Lưu lịch sử hóa đơn
            LichSuHoaDon ls = new LichSuHoaDon();
            ls.setNguoiThaoTac(nguoiNhan);
            ls.setHoaDon(hoaDon);
            ls.setThaoTac("Đặt hàng thanh toán khi nhận hàng");
            lichSuHoaDonRepository.save(ls);

            try {
                emailService.sendOrderConfirmationEmail(emailNguoiNhan, hoaDon);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> kiemTraSoLuongHang(Integer idHoaDon) {
        List<String> thongBao = new ArrayList<>();
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(idHoaDon);

        if (optHoaDon.isPresent()) {
            HoaDon hoaDon = optHoaDon.get();
            List<HoaDonChiTiet> hoaDonChiTiets = hoaDon.getHoaDonChiTiets();

            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
                ChiTietSanPham sanPhamChiTiet = hoaDonChiTiet.getChiTietSanPham();
                Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
                Integer soLuongTrongHoaDon = hoaDonChiTiet.getSoLuong();

                if (soLuongTrongHoaDon > soLuongSPCTBanDau) {
                    // Nếu có sản phẩm vượt quá số lượng, thêm thông báo vào danh sách
                    System.out.println(" thong bao qua so luong");
                    System.out.println("Sản phẩm " + sanPhamChiTiet.getSanPham().getTenSanPham() +
                            " (Màu: " + sanPhamChiTiet.getMauSac().getTenMauSac() +
                            ", Kích thước: " + sanPhamChiTiet.getKichCo().getTenKichCo() +
                            ") vượt quá số lượng cho phép.");
                    thongBao.add("Sản phẩm " + sanPhamChiTiet.getSanPham().getTenSanPham() +
                            " (Màu: " + sanPhamChiTiet.getMauSac().getTenMauSac() +
                            ", Kích thước: " + sanPhamChiTiet.getKichCo().getTenKichCo() +
                            ") vượt quá số lượng của san phẩm:" + sanPhamChiTiet.getSoLuong());
                }
            }
        }

        return thongBao;
    }



    @Override
    public ResponseEntity<String> updateXoaSanPhamBanHangTaiQuay(Integer id) {
        Optional<HoaDonChiTiet> optionalHoaDon = hoaDonChiTietRepository.findById(id);
        if (optionalHoaDon.isPresent()) {
            HoaDonChiTiet hoaDonCT = optionalHoaDon.get();
            hoaDonCT.setDaXoa(true);
            hoaDonChiTietRepository.save(hoaDonCT);

            HoaDon hoaDon = hoaDonCT.getHoaDon();
            hoaDon.getHoaDonChiTiets().remove(hoaDonCT);

            BigDecimal tongTien = hoaDon.getHoaDonChiTiets().stream().filter(hdct -> !hdct.getDaXoa()) // Lọc chỉ các hóa đơn chi tiết chưa bị xóa
                    .map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);

            hoaDon.setTongTienDonHang(tongTien);
            hoaDon.setTongTienHoaDon(tongTien);
            hoaDon.setTienShip(BigDecimal.valueOf(1));
            hoaDonRepository.save(hoaDon);

            //CẬP NHẬT SỐ LƯỢNG SẢN PHẨM CHI TIẾT
            ChiTietSanPham sanPhamChiTiet = optionalHoaDon.get().getChiTietSanPham();
            Integer soLuongSPCTBanDau = optionalHoaDon.get().getChiTietSanPham().getSoLuong();
            Integer soLuongNhapVao = optionalHoaDon.get().getSoLuong();
            Integer soLuongcapNhat = soLuongSPCTBanDau + soLuongNhapVao;

            sanPhamChiTiet.setSoLuong(soLuongcapNhat);
            sanPhamChiTietRepository.save(sanPhamChiTiet);

            String message = "Xác nhận thành công!";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn!";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> updateSoLuongSanPhamBanHangTaiQuay(Integer id, int quantity) {
        Optional<HoaDonChiTiet> optionalHoaDonCT = hoaDonChiTietRepository.findById(id);
        if (optionalHoaDonCT.isPresent()) {
            HoaDonChiTiet hoaDonCT = optionalHoaDonCT.get();
            ChiTietSanPham sanPhamChiTiet = optionalHoaDonCT.get().getChiTietSanPham();
            int soLuongBanDau = sanPhamChiTiet.getSoLuong();

            int soLuongCapNhat = soLuongBanDau - (quantity - hoaDonCT.getSoLuong());

            sanPhamChiTiet.setSoLuong(soLuongCapNhat);
            sanPhamChiTietRepository.save(sanPhamChiTiet);

            hoaDonCT.setSoLuong(quantity);

            // Cập nhật tổng tiền của hóa đơn chi tiết
            BigDecimal donGia = hoaDonCT.getDonGia();
            BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(quantity));
            hoaDonCT.setTongTien(thanhTien);

            hoaDonChiTietRepository.save(hoaDonCT);

            // Cập nhật tổng tiền của hóa đơn
            HoaDon hoaDon = hoaDonCT.getHoaDon();
            BigDecimal tongTienHoaDon = hoaDon.getHoaDonChiTiets().stream().filter(hdct -> !hdct.getDaXoa()).map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);
            hoaDon.setTongTienDonHang(tongTienHoaDon);
            hoaDon.setTongTienHoaDon(tongTienHoaDon);
            hoaDon.setTienShip(BigDecimal.valueOf(1));
            hoaDonRepository.save(hoaDon);

            String message = "Lưu thành công!";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn chi tiết!";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> huyDonBanHangTaiQuay(Integer id) {
        Optional<HoaDon> otpHoaDon = hoaDonRepository.findById(id);
        if (otpHoaDon.isPresent()) {
            TrangThai trangThai = new TrangThai();
            trangThai.setIdTrangThai(8);
            HoaDon hoaDon = otpHoaDon.get();
            hoaDon.setTrangThai(trangThai);
            hoaDonRepository.save(hoaDon);

            // Cập nhật số lượng sản phẩm chi tiết và tổng tiền cho toàn bộ hóa đơn chi tiết
            List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepository.findByHoaDonIdAndDaXoa(id);
            for (HoaDonChiTiet hoaDonCT : hoaDonChiTiets) {
                hoaDonCT.setDaXoa(true);
                hoaDonChiTietRepository.save(hoaDonCT);

                ChiTietSanPham sanPhamChiTiet = hoaDonCT.getChiTietSanPham();
                Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
                Integer soLuongNhapVao = hoaDonCT.getSoLuong();
                Integer soLuongcapNhat = soLuongSPCTBanDau + soLuongNhapVao;

                sanPhamChiTiet.setSoLuong(soLuongcapNhat);
                sanPhamChiTietRepository.save(sanPhamChiTiet);
            }

            BigDecimal tongTien = hoaDon.getHoaDonChiTiets().stream().filter(hdct -> !hdct.getDaXoa()) // Lọc chỉ các hóa đơn chi tiết chưa bị xóa
                    .map(HoaDonChiTiet::getTongTien).reduce(BigDecimal.ZERO, BigDecimal::add);

            hoaDon.setTongTienDonHang(tongTien);
            hoaDon.setTongTienHoaDon(tongTien);
            hoaDon.setTienShip(BigDecimal.valueOf(1));
            hoaDonRepository.save(hoaDon);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                //Lưu lịch sử hóa đơn
                LichSuHoaDon ls = new LichSuHoaDon();
                ls.setNguoiThaoTac(nguoiDung.getTenNguoiDung());
                ls.setHoaDon(hoaDon);
                ls.setThaoTac("Đã hủy đơn" + hoaDon.getMaDon());
                lichSuHoaDonRepository.save(ls);

                //Lưu nhân viên bán hàng vào hóa đơn
                hoaDon.setNguoiDung(nguoiDung);
                hoaDonRepository.save(hoaDon);
            }

            String mss = "Hủy thành công!";
            return ResponseEntity.ok(mss);
        } else {
            String erro = "Không tìm thấy hóa đơn!";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void thanhToanHoaDonBanHangTaiQuay(Integer id, BigDecimal tien_giam, String khuyenMai, BigDecimal tongTienHoaDon) {
        Optional<HoaDon> optHoaDon = hoaDonRepository.findById(id);
        if (optHoaDon.isPresent()) {
            Optional<KhuyenMai> optionalKhuyenMai = khuyenMaiRepository.findKhuyenMaiByTenKhuyenMai(khuyenMai);
            if (optionalKhuyenMai.isPresent()) {
                KhuyenMai km = optionalKhuyenMai.get();
                HoaDon hoaDon = optHoaDon.get();
                Integer tienGiamToiDa = km.getGiaTriToiThieu();
                Integer tienGiamInt = Integer.valueOf(String.valueOf(tien_giam));
                if (khuyenMai != null) {
                    hoaDon.setKhuyenMai(km);
                    if (tienGiamInt >= tienGiamToiDa) {
                        hoaDon.setTienGiam(BigDecimal.valueOf(tienGiamToiDa));
                    } else {
                        hoaDon.setTienGiam(tien_giam);
                    }
                } else {
                    hoaDon.setKhuyenMai(null);
                }
            }

            TrangThai trangThai = new TrangThai();
            trangThai.setIdTrangThai(7);

            //LƯU HÓA ĐƠN
            HoaDon hoaDon = optHoaDon.get();
            hoaDon.setTrangThai(trangThai);
            hoaDon.setTongTienHoaDon(tongTienHoaDon);
            hoaDonRepository.save(hoaDon);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
          //  List<HoaDonChiTiet> hoaDonChiTiets1 = hoaDonChiTietRepository.findByHoaDonIdAndDaXoa(id);

//            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets1) {
//
//                ChiTietSanPham sanPhamChiTiet = hoaDonChiTiet.getChiTietSanPham();
//                Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
//                Integer soLuongTrongHoaDon = hoaDonChiTiet.getSoLuong();
//                Integer soLuongcapNhat = soLuongSPCTBanDau - soLuongTrongHoaDon;
//                sanPhamChiTiet.setSoLuong(soLuongcapNhat);
//                sanPhamChiTietRepository.save(sanPhamChiTiet);
//            }
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                //Lưu lịch sử hóa đơn
                LichSuHoaDon ls = new LichSuHoaDon();
                ls.setNguoiThaoTac(nguoiDung.getTenNguoiDung());
                ls.setHoaDon(hoaDon);
                ls.setThaoTac("Thanh toán hóa đơn " + hoaDon.getMaDon());
                lichSuHoaDonRepository.save(ls);

                //Lưu nhân viên bán hàng vào hóa đơn
                hoaDon.setNguoiDung(nguoiDung);
                hoaDonRepository.save(hoaDon);
            }
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> themMaGiamGiaBanHangTaiQuay(String couponCode) {
        Optional<KhuyenMai> optkhuyenMai = khuyenMaiRepository.findKhuyenMaiByTenKhuyenMai(couponCode);
        if (optkhuyenMai.isPresent()) {
            KhuyenMai khuyenMai = optkhuyenMai.get();
            Integer tienGiam = khuyenMai.getPhanTramGiam();
            Integer tienGiamToiDa = khuyenMai.getGiaTriToiThieu();
            String tenGiamGia = khuyenMai.getTenKhuyenMai();

            Map<String, String> response = new HashMap<>();
            response.put("tenGiamGia", tenGiamGia.toString());
            response.put("tienGiamToiDa", tienGiamToiDa.toString());
            response.put("tienGiam", tienGiam.toString());
            return ResponseEntity.ok(response);
        }
        String erro = "Lỗi";
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Map<String, Integer>> taoHoaDonBanHangTaiQuay(HoaDon hoaDon) {
        Integer maxId = hoaDonService.getMaxId();
        int id;
        String ma;

        if (maxId != null) {
            id = maxId + 1;
        } else {
            id = 1;
        }

        DecimalFormat df = new DecimalFormat("00");
        String formattedId = df.format(id);

        ma = "HD" + formattedId;

        TrangThai trangThai = trangThaiService.getTrangThaiById(6);
        hoaDon.setMaDon(ma);
        hoaDon.setNgayTao(new Date());
        hoaDon.setNguoiTao("quinc");
        hoaDon.setLoaiHoaDon(1);
        hoaDon.setTrangThai(trangThai);
        hoaDon.setTongTienHoaDon(BigDecimal.valueOf(0));
        hoaDon.setTongTienDonHang(BigDecimal.valueOf(0));
        hoaDon.setDaXoa(false);
        hoaDon.setTienGiam(BigDecimal.ZERO);
        hoaDonRepository.save(hoaDon);

        int idHoaDon = hoaDon.getIdHoaDon();
        Map<String, Integer> response = new HashMap<>();
        response.put("idHoaDonVuaTao", idHoaDon);

        return ResponseEntity.ok(response);
    }

    @Override
    public void themSanPhamVaoHoaDonBanHangTaiQuay(Integer kichThuocId, Integer mauSacId, Integer sanPhamId, int hoaDonID, Integer soLuongSanPham) {
        Optional<ChiTietSanPham> optSpct = sanPhamChiTietService.getSanPhamChiTietByMauSacSizeSanPhamId(sanPhamId, mauSacId, kichThuocId);
        if (optSpct.isPresent()) {
            Optional<HoaDon> optHD = hoaDonRepository.findById(hoaDonID);
            HoaDon hoaDon = optHD.get();
            BigDecimal giaSP = optSpct.get().getGia();
            BigDecimal thanhTien = giaSP.multiply(BigDecimal.valueOf(soLuongSanPham));

            Optional<HoaDonChiTiet> optHdct = hoaDonChiTietRepository.findBySanPhamChiTietAndHoaDon(optSpct.get().getIdCTSP(), hoaDon.getIdHoaDon());

            if (optHdct.isPresent()) {
                // Nếu hóa đơn chi tiết đã tồn tại, cập nhật số lượng và tổng tiền
                HoaDonChiTiet existingHdct = optHdct.get();
                Integer soLuongHienTai = existingHdct.getSoLuong();
                BigDecimal tongTienHienTai = existingHdct.getTongTien();

                existingHdct.setSoLuong(soLuongHienTai + soLuongSanPham);
                existingHdct.setTongTien(tongTienHienTai.add(thanhTien));
                hoaDonChiTietRepository.save(existingHdct);
            } else {
                // Nếu hóa đơn chi tiết chưa tồn tại, tạo mới hóa đơn chi tiết
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setChiTietSanPham(optSpct.get());
                hoaDonChiTiet.setHoaDon(hoaDon);
                hoaDonChiTiet.setDonGia(giaSP);
                hoaDonChiTiet.setSoLuong(soLuongSanPham);
                hoaDonChiTiet.setTongTien(thanhTien);
                hoaDonChiTiet.setDaXoa(false);
                hoaDonChiTietRepository.save(hoaDonChiTiet);

                hoaDon.getHoaDonChiTiets().add(hoaDonChiTiet);
                hoaDonRepository.save(hoaDon);
            }

            BigDecimal tongTienDonHang = hoaDon.getHoaDonChiTiets().stream()
                    .filter(hdct -> !hdct.getDaXoa())
                    .map(HoaDonChiTiet::getTongTien)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            hoaDon.setTongTienDonHang(tongTienDonHang);
            hoaDon.setTongTienHoaDon(tongTienDonHang);
            hoaDon.setTienShip(BigDecimal.ZERO);
            hoaDonRepository.save(hoaDon);

            // Cập nhật số lượng sản phẩm chi tiết
            ChiTietSanPham sanPhamChiTiet = optSpct.get();
            Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
            Integer soLuongcapNhat = soLuongSPCTBanDau - soLuongSanPham;

            sanPhamChiTiet.setSoLuong(soLuongcapNhat);
            sanPhamChiTietRepository.save(sanPhamChiTiet);
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> themKhachHangVaoHoaDonTaiQuay(Integer IdHoaDon, Integer IDKhachHang, String TenKhachHang, String SDTKhachHang) {
        Map<String, String> response = new HashMap<>();
        Optional<KhachHang> optionalKhachHang = khachHangRepository.findKhachHangBySDT(SDTKhachHang);
        if (optionalKhachHang.isPresent() && IDKhachHang == 0) {
            // Nếu tồn tại, báo lỗi và không thực hiện thêm vào hóa đơn
            response.put("error", "Số điện thoại này đã được sử dụng");
            return ResponseEntity.badRequest().body(response);
        }
        // Kiểm tra xem số điện thoại có bắt đầu bằng số 0 hay không
        if (!SDTKhachHang.startsWith("0")) {
            response.put("error", "Số điện thoại phải bắt đầu bằng số 0");
            return ResponseEntity.badRequest().body(response);
        }


        if (SDTKhachHang.length() != 10) {
            response.put("error", "Số điện thoại phải có đúng 10 số");
            return ResponseEntity.badRequest().body(response);
        }


        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(IdHoaDon);

        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();

            if (IDKhachHang != 0) {
                KhachHang khachHang = khachHangRepository.findKhachHangByID(IDKhachHang);
                hoaDon.setKhachHang(khachHang);
                hoaDon.setNguoiNhan(khachHang.getHoTen());
                hoaDon.setSdtNguoiNhan(khachHang.getSoDienThoai());
                hoaDonRepository.save(hoaDon);
            } else {
                hoaDon.setKhachHang(null);
                hoaDon.setNguoiNhan(TenKhachHang);
                hoaDon.setSdtNguoiNhan(SDTKhachHang);
                hoaDonRepository.save(hoaDon);

                KhachHang kh = new KhachHang();
                kh.setHoTen(TenKhachHang);
                kh.setSoDienThoai(SDTKhachHang);
                khachHangRepository.save(kh);
            }
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok().build();
    }
}
