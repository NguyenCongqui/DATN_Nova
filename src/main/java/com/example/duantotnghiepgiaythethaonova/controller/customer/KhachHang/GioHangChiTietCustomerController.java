package com.example.duantotnghiepgiaythethaonova.controller.customer.KhachHang;

import com.example.duantotnghiepgiaythethaonova.dto.GioHangChiTietDTO;
import com.example.duantotnghiepgiaythethaonova.dto.GioHangDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KhachHangDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KhuyenMaiDTO;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.GioHangChiTiet;
import com.example.duantotnghiepgiaythethaonova.repository.GioHangChiTietRepository;
import com.example.duantotnghiepgiaythethaonova.repository.GioHangRepository;
import com.example.duantotnghiepgiaythethaonova.repository.HoaDonRepository;
import com.example.duantotnghiepgiaythethaonova.repository.SanPhamChiTietRepository;
import com.example.duantotnghiepgiaythethaonova.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class GioHangChiTietCustomerController {

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    GioHangRepository gioHangRepository;

    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @GetMapping("/khachhang/gio-hang-chi-tiet")
    public String layGioHangChiTiet(Model model, RedirectAttributes redirectAttributes) {
        GioHangDTO gioHangDTO = null;
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        KhachHangDTO khachHangDT0 = khachHangService.findByEmail(auth);
        if (khachHangDT0 != null) {
            gioHangDTO = gioHangService.findByKhachHangId(khachHangDT0.getId());
            System.out.println(gioHangService.findByKhachHangId(khachHangDT0.getId()));
            if (gioHangDTO != null) {
                if (gioHangDTO.getSoTienGiamGia() == null) {
                    gioHangDTO.setSoTienGiamGia(0);
                    System.out.println(gioHangDTO.getId());
                    if (gioHangChiTietService.getTongTienByKhachHangID(gioHangDTO.getId()) != null) {
                        gioHangDTO.setTongTien(gioHangChiTietService.getTongTienByKhachHangID(gioHangDTO.getId()));
                    } else {
                        gioHangDTO.setTongTien(gioHangDTO.getTongTien());
                    }
                    gioHangDTO.setThanhTien(gioHangDTO.getTongTien() - gioHangDTO.getSoTienGiamGia());
                }
            } else {
                gioHangDTO = new GioHangDTO();
                gioHangDTO.setListGioHangChiTiets(null);
            }
            model.addAttribute("gioHangDTO", gioHangDTO);
            return "customer/khach-hang/gio-hang-chi-tiet";
        } else {
            redirectAttributes.addFlashAttribute("message", "Phiên đăng nhập đã hết hạn !");
            return "redirect:/security/login/form";
        }
    }

    @GetMapping("/khachhang/gio-hang-chi-tiet/xoa-gio-hang")
    public String xoaGioHang(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        if (id != null) {
            Optional<GioHangChiTiet> optionalGioHangChiTiet = gioHangChiTietRepository.findById(id);
            if (optionalGioHangChiTiet.isPresent()) {
                GioHangChiTiet gioHangChiTiet = optionalGioHangChiTiet.get();
                gioHangChiTiet.setDaXoa(true);
                gioHangChiTietRepository.save(gioHangChiTiet);

                ChiTietSanPham sanPhamChiTiet = gioHangChiTiet.getChiTietSanPham();
                Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
                Integer soLuongDangCo = gioHangChiTiet.getSoLuong();
                Integer soLuongcapNhat = soLuongSPCTBanDau + soLuongDangCo;

                sanPhamChiTiet.setSoLuong(soLuongcapNhat);
                sanPhamChiTietRepository.save(sanPhamChiTiet);
            }
            gioHangChiTietService.capNhatGioHangThanhDaXoaById(id);
            layGioHangChiTiet(model, redirectAttributes);
        }
        return "redirect:/khachhang/gio-hang-chi-tiet";
    }


    //Cập nhật giỏ hàng
    @PostMapping("/khachhang/gio-hang-chi-tiet")
    public String capNhatGioHang(@RequestParam("ids") Integer[] ids,
                                 @RequestParam("soLuongs") Integer[] soLuongs,
                                 @RequestParam("donGias") Integer[] donGias,
                                 Model model, RedirectAttributes redirectAttributes) {
        layGioHangChiTiet(model, redirectAttributes);
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        KhachHangDTO khachHangDT0 = khachHangService.findByEmail(auth);
        List<Integer> listSoLuong = toListInterger(soLuongs);
        if (ids != null && soLuongs != null) {
            for (int i = 0; i < ids.length; i++) {
                Integer id = (Integer) Array.get(ids, i);
                Integer soLuong = listSoLuong.get((int) i);
                if (soLuong > 0) {
                    GioHangChiTietDTO gioHangChiTietDTO = gioHangChiTietService.findById(id);
                    if (soLuong > gioHangChiTietDTO.getSanPhamChiTietDTO().getSoLuong() + gioHangChiTietDTO.getSoLuong()) {
                        model.addAttribute("message", "Số lượng  sản phẩm " + gioHangChiTietDTO.getSanPhamChiTietDTO().getSanPhamDTO().getTenSanPham() +
                                " ,kích cỡ " + gioHangChiTietDTO.getSanPhamChiTietDTO().getTenKichCo() + " ,màu " + gioHangChiTietDTO.getSanPhamChiTietDTO().getTenMauSac() +
                                " không đủ !");
                        return "/customer/khach-hang/gio-hang-chi-tiet";
                    }
                }
            }
            for (Integer soLuong : soLuongs) {
                if (soLuong == null) {
                    model.addAttribute("message", "Số lượng sản phẩm trống !");
                    return "/customer/khach-hang/gio-hang-chi-tiet";
                } else if (soLuong <= 0) {
                    model.addAttribute("message", "Số lượng sản phẩm phải lớn hơn 0");
                    return "/customer/khach-hang/gio-hang-chi-tiet";
                }
            }
            gioHangChiTietService.capNhatSoLuongGioHangChiTiet(ids, soLuongs, donGias);
            //Mốt là thay bằng spring security
            gioHangService.capNhatTongTien(khachHangDT0.getId());
            return "redirect:/khachhang/gio-hang-chi-tiet";
        } else {
            model.addAttribute("message", "Giỏ hàng không có sản phẩm");
            return "/customer/khach-hang/gio-hang-chi-tiet";
        }
    }

    public List<Integer> toListInterger(Integer[] integers) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < integers.length; i++) {
            Integer integer = integers[i];
            list.add(integer);
        }
        return list;
    }

    @PostMapping("/khachhang/gio-hang-chi-tiet/ap-dung-ma-giam-gia")
    public String apMaGiamGia(@ModelAttribute("gioHangDTO") GioHangDTO result, Model model) {
        //Thay bằng mã khách hàng
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        KhachHangDTO khachHangDT0 = khachHangService.findByEmail(auth);
        GioHangDTO gioHangDTO = gioHangService.findByKhachHangId(khachHangDT0.getId());
        gioHangDTO.setMaGiamGia(result.getMaGiamGia());
        gioHangDTO.setTongTien(gioHangChiTietService.getTongTienByKhachHangID(gioHangDTO.getId()));
        if (!gioHangDTO.getMaGiamGia().equals("")) {
            KhuyenMaiDTO khuyenMaiDTO = khuyenMaiService.timKhuyenMaiTheoTenKhuyenMai(result.getMaGiamGia());
            if (khuyenMaiDTO != null) {
                if (khuyenMaiDTO.isTrangThai() == false) {
                    model.addAttribute("message_khuyenMai", "Khuyến Mại đã hết hạn sử dụng !");
                } else {
                    if (gioHangDTO.getTongTien() > khuyenMaiDTO.getGiaTriToiThieu()) {
                        gioHangDTO.setSoTienGiamGia((khuyenMaiDTO.getPhanTramGiam() * gioHangDTO.getTongTien()) / 100);
                        gioHangDTO.setThanhTien(gioHangDTO.getTongTien() - gioHangDTO.getSoTienGiamGia());
                        model.addAttribute("message_khuyenMai_success", "Áp dụng mã khuyến mãi thành công !");
                    } else {
                        model.addAttribute("message_khuyenMai", "Giá trị tối thiểu của đơn phải lớn hơn " + khuyenMaiDTO.getGiaTriToiThieu() + " !");
                    }
                }
            } else {
                model.addAttribute("message_khuyenMai", "Mã khuyến mại không chính xác !");
            }
        } else {
            model.addAttribute("message_khuyenMai", "Bạn chưa nhập mã !");
        }
        model.addAttribute("gioHangDTO", gioHangDTO);
        return "/customer/khach-hang/gio-hang-chi-tiet";
    }
}
