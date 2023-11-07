package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamTaiQuayDTO;
import com.example.duantotnghiepgiaythethaonova.dto.composite.ShowSanPhamdto;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import com.example.duantotnghiepgiaythethaonova.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    CTSPRepository sanPhamChiTietRepository;

    @Autowired
    CTSPService sanPhamChiTietService;

    @Autowired
    GiaoDichRepository giaoDichRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    KichCoService kichCoService;

    @Autowired
    HinhAnhService hinhAnhService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    MauSacService mauSacService;

    public HoaDonServiceImpl(HoaDonRepository hoaDonRepository) {
        this.hoaDonRepository = hoaDonRepository;
    }

    @Override
    public List<HoaDon> getAll() {
        return (List<HoaDon>) hoaDonRepository.findAll();
    }

    @Override
    public Integer getMaxId() {
        return hoaDonRepository.getMaxId();
    }

    @Override
    @Transactional
    public void capNhatTrangThaiHuyDon(String maDonHang) {
        HoaDon hoaDon = hoaDonRepository.findByMaDonHang(maDonHang);
        if (hoaDon != null) {
            hoaDonRepository.capNhatTrangThaiThanhHuyDon(hoaDon.getIdHoaDon());
        }
    }

    @Override
    public ResponseEntity<String> xoaHoaDonCTChinhSuaHoaDon(Integer hoaDonCTId) {
        Optional<HoaDonChiTiet> optHoaDonChiTiet = hoaDonChiTietRepository.findById(hoaDonCTId);
        if (optHoaDonChiTiet.isPresent()) {
            HoaDonChiTiet hoaDonChiTiet = optHoaDonChiTiet.get();
            int hoaDonId = hoaDonChiTiet.getHoaDon().getIdHoaDon();
            hoaDonChiTiet.setDaXoa(true);
            hoaDonChiTietRepository.save(hoaDonChiTiet);

            Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
            if (optionalHoaDon.isPresent()) {
                HoaDon hoaDon = optionalHoaDon.get();
                BigDecimal tongTienDonHang = hoaDon.getHoaDonChiTiets().stream()
                        .filter(hdct -> !hdct.getDaXoa())
                        .map(HoaDonChiTiet::getTongTien)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                BigDecimal tienShip = hoaDon.getTienShip();
                hoaDon.setTongTienDonHang(tongTienDonHang);
                hoaDon.setTongTienHoaDon(tongTienDonHang.add(tienShip));

                // Lưu lại hóa đơn sau khi cập nhật tổng tiền
                hoaDonRepository.save(hoaDon);

                String email = SecurityContextHolder.getContext().getAuthentication().getName();
                Optional<NguoiDung> optNguoiDung = nguoiDungRepository.findByEmail2(email);
                if (optNguoiDung.isPresent()) {
                    NguoiDung nguoiDung = optNguoiDung.get();
                    // Lưu lịch sử hóa đơn
                    LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
                    lichSuHoaDon.setNguoiThaoTac(nguoiDung.getTenNguoiDung());
                    lichSuHoaDon.setHoaDon(hoaDon);
                    lichSuHoaDon.setThaoTac("Xóa sản phẩm " + hoaDonChiTiet.getChiTietSanPham().getSanPham().getTenSanPham());
                    lichSuHoaDonRepository.save(lichSuHoaDon);
                }
            }
        }

        return new ResponseEntity<>("Đã xóa hóa đơn chi tiết thành công và cập nhật tổng tiền của hóa đơn", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> chinhSuaSoLuongSanPhamChinhSuaHoaDon(Integer id, int quantity) {
        Optional<HoaDonChiTiet> optionalHoaDonCT = hoaDonChiTietRepository.findById(id);
        if (optionalHoaDonCT.isPresent()) {
            HoaDonChiTiet hoaDonCT = optionalHoaDonCT.get();
            int soLuongTruocCapNhat = hoaDonCT.getSoLuong();
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
            hoaDon.setTongTienHoaDon(tongTienHoaDon);
            hoaDonRepository.save(hoaDon);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                //Lưu lịch sử hóa đơn
                LichSuHoaDon ls = new LichSuHoaDon();
                ls.setNguoiThaoTac(nguoiDung.getTenNguoiDung());
                ls.setHoaDon(hoaDon);
                ls.setThaoTac("Cập nhật số lượng của sản phẩm " + hoaDonCT.getChiTietSanPham().getSanPham().getTenSanPham() + " từ " + soLuongTruocCapNhat + " thành " + quantity);
                lichSuHoaDonRepository.save(ls);
            }

            String message = "Lưu thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn chi tiết";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void ThemSanPhamVaoHoaDonChoXacNhanChinhSuaHoaDon(Integer kichThuocId, Integer mauSacId, Integer sanPhamId, int hoaDonID, Integer soLuongSanPham) {
        Optional<ChiTietSanPham> optSpct = sanPhamChiTietService.getSanPhamChiTietByMauSacSizeSanPhamId(sanPhamId, mauSacId, kichThuocId);
        if (optSpct.isPresent()) {
            Optional<HoaDon> optHD = hoaDonRepository.findById(hoaDonID);
            HoaDon hoaDon = optHD.get();
            BigDecimal giaSP = optSpct.get().getSanPham().getGia();
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

            BigDecimal tienShip = hoaDon.getTienShip();
            hoaDon.setTongTienDonHang(tongTienDonHang);
            hoaDon.setTongTienHoaDon(tongTienDonHang.add(tienShip));
            hoaDonRepository.save(hoaDon);

            // Cập nhật số lượng sản phẩm chi tiết
            ChiTietSanPham sanPhamChiTiet = optSpct.get();
            Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
            Integer soLuongcapNhat = soLuongSPCTBanDau - soLuongSanPham;

            sanPhamChiTiet.setSoLuong(soLuongcapNhat);
            sanPhamChiTietRepository.save(sanPhamChiTiet);

            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                //Lưu lịch sử hóa đơn
                LichSuHoaDon ls = new LichSuHoaDon();
                ls.setNguoiThaoTac(nguoiDung.getTenNguoiDung());
                ls.setHoaDon(hoaDon);
                ls.setThaoTac("Thêm mới sản phẩm " + optSpct.get().getSanPham().getTenSanPham() + " vào hóa đơn");
                lichSuHoaDonRepository.save(ls);
            }
        }
    }

    @Override
    public void XoaHoaDonCXNChinhSuaHoaDon(Integer hoaDonID) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonID);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            hoaDon.setDaXoa(true);
            hoaDonRepository.save(hoaDon);
        }
    }

    @Override
    public void ChinhSuaHoaDonView(Integer hoaDonId, SPAndSPCTSearchDto dataSearch, Optional<Integer> page, Optional<Integer> size, Model model, Optional<String> messageSuccess, Optional<String> messageDanger) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<SanPham> resultPage = null;

        //Lấy hóa đơn id lưu vào DTO
        SanPhamTaiQuayDTO resultSP = new SanPhamTaiQuayDTO();
        resultSP.setHoaDonId(hoaDonId);

        List<ShowSanPhamdto> lstSSPTQ = new ArrayList<>();

        Optional<SPAndSPCTSearchDto> optDataSearch = Optional.of(dataSearch);
        if (optDataSearch.isPresent()) {
            resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
            for (SanPham sp : resultPage.getContent()) {
                ShowSanPhamdto ssptq = new ShowSanPhamdto();
                List<Integer> mauSacIds = sanPhamChiTietService.getLstMauSacBySanPhamId(sp.getIdSanPham());
                List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacIds(sp.getIdSanPham(), mauSacIds);
                List<String> lstHinhAnhStr = new ArrayList<>();
                for (HinhAnh ha : lstHinhAnh) {
                    lstHinhAnhStr.add(ha.getTenAnh());
                }
                ssptq.setAnhChinhs(lstHinhAnhStr);
                ssptq.setSanPhamId(sp.getIdSanPham());
                ssptq.setGia(sp.getGia());
                ssptq.setTenSanPham(sp.getTenSanPham());
                List<KichCo> lstKichCo = kichCoService.selectAllKichCoBySanPhamId(sp.getIdSanPham());
                List<MauSac> lstMauSac = mauSacService.getAllMauSacExistBySPId(sp.getIdSanPham());
                ssptq.setLstKichCo(lstKichCo);
                ssptq.setLstMauSac(lstMauSac);
                lstSSPTQ.add(ssptq);
            }
            resultSP.setLstShowSanPhamTaiQua(lstSSPTQ);
            model.addAttribute("dataSearch", dataSearch);
            model.addAttribute("resultSP", resultSP);
        }

        int totalPages = resultPage.getTotalPages();
        if (totalPages > 0) {
            int start = Math.max(1, currentPage - 2);
            int end = Math.min(currentPage + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) {
                    start = end - 5;
                } else if (start == 1) {
                    end = start + 5;
                }
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        if (messageSuccess.isPresent()) {
            model.addAttribute("messageSuccess", messageSuccess.get());
        }
        if (messageSuccess.isPresent()) {
            model.addAttribute("messageDanger", messageDanger.get());
        }
        model.addAttribute("sanPhamPage", resultPage);
        model.addAttribute("idHoaDon", hoaDonId);
    }

    @Override
    public ResponseEntity<String> updateGiaoHangChoGiaoHang(Integer hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setIdTrangThai(3);
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
            String message = "Xác nhận thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> updateGiaoHangAllChoGiaoHang() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(2);
        if (!hoaDonList.isEmpty()) {
            // Duyệt qua từng hóa đơn và cập nhật trạng thái
            for (HoaDon hoaDon : hoaDonList) {
                TrangThai tt = new TrangThai();
                tt.setIdTrangThai(3);
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

    @Override
    public ResponseEntity<String> updateXacNhanChoXacNhan(Integer hoaDonId) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(hoaDonId);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon = optionalHoaDon.get();
            TrangThai tt = new TrangThai();
            tt.setIdTrangThai(2);
            hoaDon.setTrangThai(tt);
            hoaDon.setNgayCapNhat(new Date());
            hoaDon.setNguoiCapNhat("linh");
            hoaDonRepository.save(hoaDon);
            String message = "Xác nhận thành công";
            GiaoDich gd = new GiaoDich();
            gd.setHoaDon(hoaDon);
            gd.setNgayCapNhat(new Date());
            gd.setNgayTao(new Date());
            gd.setNguoiCapNhat("linh");
            gd.setNguoiTao("linh");
            gd.setTrangThai(tt);
            giaoDichRepository.save(gd);

            String emailNguoiNhan = hoaDon.getEmailNguoiNhan();
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<NguoiDung> OptNguoiDung = nguoiDungRepository.findByEmail2(email);
            if (OptNguoiDung.isPresent()) {
                NguoiDung nguoiDung = OptNguoiDung.get();
                gd.setNguoiDung(nguoiDung);
                giaoDichRepository.save(gd);
            }
            try {
                emailService.sendMailChoGiaoHang(emailNguoiNhan, hoaDon);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Không tìm thấy hóa đơn";
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> updateHuyDonChoXacNhan(Integer hoaDonId) {
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
                emailService.sendMailHuyDonHang(emailNguoiNhan, hoaDon);
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
    public ResponseEntity<String> updateXacNhanAllChoXacNhan() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(1);
        if (!hoaDonList.isEmpty()) {
            for (HoaDon hoaDon : hoaDonList) {
                TrangThai tt = new TrangThai();
                tt.setIdTrangThai(2);
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
                    emailService.sendMailChoGiaoHang(emailNguoiNhan, hoaDon);
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

    @Override
    public ResponseEntity<String> updateHuyAllChoXacNhan() {
        List<HoaDon> hoaDonList = hoaDonRepository.findByTrangThaiHoaDonListTrangThai(1);
        if (!hoaDonList.isEmpty()) {
            for (HoaDon hoaDon : hoaDonList) {
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
                    emailService.sendMailHuyDonHang(emailNguoiNhan, hoaDon);
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

    @Override
    public ResponseEntity<String> updateGiaoHangThanhCongDangGiaoHang(Integer hoaDonId) {
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
                emailService.sendMailDaGiaoHang(emailNguoiNhan, hoaDon);
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
    public ResponseEntity<String> updateThanhCongAllDangGiaoHang() {
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
                    emailService.sendMailDaGiaoHang(emailNguoiNhan, hoaDon);
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
