package com.example.duantotnghiepgiaythethaonova.controller.admin.BanHang.BanHangTaiQuay;

import com.example.duantotnghiepgiaythethaonova.dto.ChatLieuDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KichCoDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KieuDangDTO;
import com.example.duantotnghiepgiaythethaonova.dto.MauSacDTO;
import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamTaiQuayDTO;
import com.example.duantotnghiepgiaythethaonova.dto.composite.ShowSanPhamdto;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.HinhAnh;
import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import com.example.duantotnghiepgiaythethaonova.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class SanPhamController {
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
//    private LoaiSanPhamService loaiSanPhamService;

//    @Autowired
//    private PhongCachService phongCachService;

    @Autowired
    private KieuDangService kieuDangService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private HinhAnhService hinhAnhService;


    @GetMapping("danh-sach/images/{filename:.+}")
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

//    @ModelAttribute("lstLoaiSanPham")
//    public List<LoaiSanPhamDTO> getLstLoaiHang() {
//        return loaiSanPhamService.selectAllLoaiHangExist().stream().map(item -> {
//            LoaiSanPhamDTO dto = new LoaiSanPhamDTO();
//            BeanUtils.copyProperties(item, dto);
//            return dto;
//        }).collect(Collectors.toList());
//    }
//
//    @ModelAttribute("lstPhongCach")
//    public List<PhongCachDTO> getLstPhongCach() {
//        return phongCachService.selectAllPhongCachExist().stream().map(item -> {
//            PhongCachDTO dto = new PhongCachDTO();
//            BeanUtils.copyProperties(item, dto);
//            return dto;
//        }).collect(Collectors.toList());
//    }

    @GetMapping("banHang/danhSachSanPham/{id}")
    public String danhSachSanPham(@PathVariable("id") Integer hoaDonId,
                                  @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size,
                                  Model model,
                                  @RequestParam("messageSuccess") Optional<String> messageSuccess,
                                  @RequestParam("messageDanger") Optional<String> messageDanger) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<SanPham> resultPage = null;
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
//                for (int i=lstHinhAnh.size()-1;0<=i;i--) {
//    				lstHinhAnhStr.add(lstHinhAnh.get(i).getTenAnh());
//    			}
                for (HinhAnh ha : lstHinhAnh) {
                    lstHinhAnhStr.add(ha.getTenAnh());
                }
                if (mauSacIds.size() > lstHinhAnh.size()) {
                    int count = mauSacIds.size() - lstHinhAnh.size();
                    for (int i = 0; i < count; i++) {
                        lstHinhAnhStr.add("default.png");
                    }
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
        return "admin/banHang/banHangTaiQuay/sanPham";
    }
}