package com.example.duantotnghiepgiaythethaonova.controller.admin.HoaDon.chinhSuaHoaDon;

import com.example.duantotnghiepgiaythethaonova.dto.*;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class chinhSuaHoaDonController {
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


    @Autowired
    private DayGiayService dayGiayService;

    @Autowired
    private DeGiayService deGiayService;

    @Autowired
    private LotGiayService lotGiayService;


    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KieuDangService kieuDangService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private HinhAnhService hinhAnhService;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    HoaDonService hoaDonService;


    @GetMapping("chinhSuaHoaDon/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
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

    @ModelAttribute("lstDayGiay")
    public List<DayGiayDTO> getLstDayGiay() {
        return dayGiayService.selectAllKichCoExist().stream().map(item -> {
            DayGiayDTO dto = new DayGiayDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstDeGiay")
    public List<DeGiayDTO> getLstDeGiay() {
        return deGiayService.selectAllKichCoExist().stream().map(item -> {
            DeGiayDTO dto = new DeGiayDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstLotGiay")
    public List<LotGiayDTO> getLstLotGiay() {
        return lotGiayService.selectAllKichCoExist().stream().map(item -> {
            LotGiayDTO dto = new LotGiayDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstThuongHieu")
    public List<ThuongHieuDTO> getLstLoaiHang() {
        return thuongHieuService.selectAllLoaiHangExist().stream().map(item -> {
            ThuongHieuDTO dto = new ThuongHieuDTO();
            BeanUtils.copyProperties(item, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping("hoaDon/chinhSuaHoaDon/{id}")
    public String ChinhSuaHoaDon(@PathVariable("id") Integer hoaDonId,
                                 @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size,
                                 Model model,
                                 @RequestParam("messageSuccess") Optional<String> messageSuccess,
                                 @RequestParam("messageDanger") Optional<String> messageDanger) {

        hoaDonService.ChinhSuaHoaDonView(hoaDonId, dataSearch, page, size, model, messageSuccess, messageDanger);
        return "admin/hoadon/ChinhSuaHoaDon/danhSachSanPham";
    }
}
