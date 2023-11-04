package com.example.duantotnghiepgiaythethaonova.controller.admin.BanHang.BanHangTaiQuay;

import com.example.duantotnghiepgiaythethaonova.dto.ChatLieuDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KichCoDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KieuDangDTO;
import com.example.duantotnghiepgiaythethaonova.dto.MauSacDTO;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.KhachHang;
import com.example.duantotnghiepgiaythethaonova.entity.KhuyenMai;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import com.example.duantotnghiepgiaythethaonova.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class BanHangController {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    HoaDonRepoditory2 hoaDonRepository2;

    @Autowired
    HoaDonChiTietRepository2 hoaDonChiTietRepository2;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private ChatLieuService chatLieuService;

    @Autowired
    private KichCoService kichCoService;

//    @Autowired
//    private LoaiSanPhamService  loaiSanPhamService;

//    @Autowired
//    private PhongCachService phongCachService;

    @Autowired
    private KieuDangService kieuDangService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private HinhAnhService hinhAnhService;

    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Autowired
    BanHangService banHangService;

    @Autowired
    KhachHangRepository khachHangRepository;

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ModelAttribute("lstMauSac")
    public List<MauSacDTO> getLstMauSac() {
        return mauSacService.selectAllMauSacExist().stream().map(item -> {
            MauSacDTO dto = new MauSacDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstKieuDang")
    public List<KieuDangDTO> getLstKieuDang() {
        return kieuDangService.selectAllKieuDangExist().stream().map(item -> {
            KieuDangDTO dto = new KieuDangDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstChatLieu")
    public List<ChatLieuDTO> getLstChatLieu() {
        return chatLieuService.selectAllChatLieuExist().stream().map(item -> {
            ChatLieuDTO dto = new ChatLieuDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstKichCo")
    public List<KichCoDTO> getLstKichCo() {
        return kichCoService.selectAllKichCoExist().stream().map(item -> {
            KichCoDTO dto = new KichCoDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

//    @ModelAttribute("lstLoaiSanPham")
//    public List<LoaiSanPhamDTO> getLstLoaiHang() {
//        return loaiSanPhamService.selectAllLoaiHangExist().stream().map(item -> {
//            LoaiSanPhamDTO dto = new LoaiSanPhamDTO();
//            BeanUtils.copyProperties(item, dto);
//            return dto;
//        }).collect(Collectors.toList());
//    }

//    @ModelAttribute("lstPhongCach")
//    public List<PhongCachDTO> getLstPhongCach() {
//        return phongCachService.selectAllPhongCachExist().stream().map(item -> {
//            PhongCachDTO dto = new PhongCachDTO();
//            BeanUtils.copyProperties(item, dto);
//            return dto;
//        }).collect(Collectors.toList());
//    }

    @RequestMapping("banHang/{id}")
    public String banHangBanHangTaiQuay(@PathVariable("id") Integer id,
                                        @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
                                        @RequestParam(defaultValue = "1") int pageHDCT,
                                        @RequestParam(defaultValue = "5") int sizeHDCT,
                                        Model model) {
        // Giảm giá
        List<KhuyenMai> giamGia = khuyenMaiRepository.getAllKhuyenMai();
        model.addAttribute("giamGia", giamGia);

        List<KhachHang> khachHang = khachHangRepository.findAll();
        model.addAttribute("khachHang", khachHang);

        banHangService.banHangBanHangTaiQuay(id, dataSearch, pageHDCT, sizeHDCT, model);
        return "admin/banHang/banHangTaiQuay/banHang";
    }
}