package com.example.duantotnghiepgiaythethaonova.controller.customer;

import com.example.duantotnghiepgiaythethaonova.dto.*;
import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamTaiQuayDTO;
import com.example.duantotnghiepgiaythethaonova.dto.composite.ShopDetailsDTO;
import com.example.duantotnghiepgiaythethaonova.dto.composite.ShowSanPhamdto;
import com.example.duantotnghiepgiaythethaonova.dto.search.SPAndSPCTSearchDto;
import com.example.duantotnghiepgiaythethaonova.entity.*;
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
@RequestMapping("khachhang")
public class HomeController {
    @Autowired
    private HinhAnhService hinhAnhService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private ChatLieuService chatLieuService;

    @Autowired
    private KieuDangService kieuDangService;

    @Autowired
    private DeGiayService deGiayService;

    @Autowired
    private LotGiayService lotGiayService;

    @Autowired
    private DayGiayService dayGiayService;

    @Autowired
    private CTSPService chiTietSanPhamService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private StorageService storageService;

    @ModelAttribute("lstGia")
    public List<String> getListGia() {
        List<String> gia = new ArrayList<>();
        gia.add("0VNĐ - 100.000VNĐ");
        gia.add("100.000VNĐ - 200.000VNĐ");
        gia.add("200.000VNĐ - 300.000VNĐ");
        gia.add("300.000VNĐ - 500.000VNĐ");
        gia.add("500.000VNĐ - 1.000.000VNĐ");
        gia.add("1.000.000VNĐ+");
        return gia;
    }

    @ModelAttribute("lstThuongHieu")
    public List<ThuongHieuDTO> getLstThuongHieu() {
        return thuongHieuService.selectAllLoaiHangExist().stream().map(item -> {
            int soSanPhamCungThuongHieu = sanPhamService.selectCountSanPhamByThuongHieuId(item.getIdThuongHieu());
            ThuongHieuDTO dto = new ThuongHieuDTO();
            BeanUtils.copyProperties(item, dto);
            dto.setSoSanPhamCungThuongHieu(soSanPhamCungThuongHieu);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstChatLieu")
    public List<ChatLieuDTO> getLstChatLieu() {
        return chatLieuService.selectAllChatLieuExist().stream().map(item -> {
            int soSanPhamCungCL = sanPhamService.selectCountSanPhamByChatLieuId(item.getIdChatLieu());
            ChatLieuDTO dto = new ChatLieuDTO();
            BeanUtils.copyProperties(item, dto);
            dto.setSoSanPhamCungChatLieu(soSanPhamCungCL);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstKieuDang")
    public List<KieuDangDTO> getlstKieuDang() {
        return kieuDangService.selectAllKieuDangExist().stream().map(item -> {
            int soSanPhamCungKieuDang = sanPhamService.selectCountSanPhamByKieuDangId(item.getIdKieuDang());
            KieuDangDTO dto = new KieuDangDTO();
            BeanUtils.copyProperties(item, dto);
            dto.setSoSanPhamCungKieuDang(soSanPhamCungKieuDang);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstDeGiay")
    public List<DeGiayDTO> getlstDeGiay() {
        return deGiayService.selectAllKichCoExist().stream().map(item -> {
            int soSanPhamCungDeGiay = chiTietSanPhamService.selectCountSanPhamChiTietByDeGiayId(item.getIdDeGiay());
            DeGiayDTO dto = new DeGiayDTO();
            BeanUtils.copyProperties(item, dto);
            dto.setSoSanPhamChiTietCungDeGiay(soSanPhamCungDeGiay);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstLotGiay")
    public List<LotGiayDTO> getlstLotGiay() {
        return lotGiayService.selectAllKichCoExist().stream().map(item -> {
            int soSanPhamCungLotDay = chiTietSanPhamService.selectCountSanPhamChiTietByDeGiayId(item.getIdLotGiay());
            LotGiayDTO dto = new LotGiayDTO();
            BeanUtils.copyProperties(item, dto);
            dto.setSoSanPhamChiTietCungLotGiay(soSanPhamCungLotDay);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstDayGiay")
    public List<DayGiayDTO> getlstDayGiay() {
        return dayGiayService.selectAllKichCoExist().stream().map(item -> {
            int soSanPhamCungDayGiay = chiTietSanPhamService.selectCountSanPhamChiTietByDeGiayId(item.getIdDayGiay());
            DayGiayDTO dto = new DayGiayDTO();
            BeanUtils.copyProperties(item, dto);
            dto.setSoSanPhamChiTietCungDayGiay(soSanPhamCungDayGiay);
            return dto;
        }).collect(Collectors.toList());
    }

    @ModelAttribute("lstMauSac")
    public List<MauSacDTO> getLstMauSac() {
        return mauSacService.selectAllMauSacExist().stream().map(item -> {
            MauSacDTO dto = new MauSacDTO();
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



    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("home")
    public String home(Model model) {
        int currentPage = 1;
        int pageSize = 8;
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        SanPhamTaiQuayDTO resultSP = new SanPhamTaiQuayDTO();
        List<ShowSanPhamdto> lstSSPTQ = new ArrayList<>();
        Page<SanPham> resultPage = sanPhamService.showSanPhamExistHomePage(pageable);
//        System.out.println(sanPhamService.showSanPhamExistHomePage(pageable));
        for (SanPham sp : resultPage.getContent()) {
            ShowSanPhamdto ssptq = new ShowSanPhamdto();
            List<Integer> mauSacIds = chiTietSanPhamService.getLstMauSacBySanPhamId(sp.getIdSanPham());
            List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacIds(sp.getIdSanPham(), mauSacIds);
            List<String> lstHinhAnhStr = new ArrayList<>();

            for (HinhAnh ha : lstHinhAnh) {
                lstHinhAnhStr.add(ha.getTenAnh());
            }
            if (lstHinhAnhStr.size() < mauSacIds.size()) {
                for (int i = 0; i < mauSacIds.size() - lstHinhAnhStr.size() - 1; i++) {
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
        model.addAttribute("resultSP", resultSP);

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
        model.addAttribute("sanPhamPage", resultPage);
        return "customer/view/home";
    }
    @GetMapping("shop")
    public String shop(Model model, @RequestParam("page") Optional<Integer> page,
                       @ModelAttribute(name = "dataSearch") SPAndSPCTSearchDto dataSearch) {
        int currentPage = page.orElse(1);
        int pageSize = 12;
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        SanPhamTaiQuayDTO resultSP = new SanPhamTaiQuayDTO();
        List<ShowSanPhamdto> lstSSPTQ = new ArrayList<>();
        Page<SanPham> resultPage = sanPhamService.searchProductExist(dataSearch, pageable);
        for (SanPham sp : resultPage.getContent()) {
            ShowSanPhamdto ssptq = new ShowSanPhamdto();
            List<Integer> mauSacIds = chiTietSanPhamService.getLstMauSacBySanPhamId(sp.getIdSanPham());
            List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhChinhBySanPhamIdAndMauSacIds(sp.getIdSanPham(), mauSacIds);
            List<String> lstHinhAnhStr = new ArrayList<>();
//			for (int i = lstHinhAnh.size() - 1; 0 <= i; i--) {
//				lstHinhAnhStr.add(lstHinhAnh.get(i).getTenAnh());
//			}
            for (HinhAnh ha : lstHinhAnh) {
                lstHinhAnhStr.add(ha.getTenAnh());
            }
            if (lstHinhAnhStr.size() < mauSacIds.size()) {
                for (int i = 0; i < mauSacIds.size() - lstHinhAnhStr.size(); i++) {
                    lstHinhAnhStr.add("default.png");
                }
            }
//            ssptq.setLoaiSanPhamId(sp.getLoaiSanPham().getId());
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
        model.addAttribute("resultSP", resultSP);

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
        model.addAttribute("sanPhamPage", resultPage);
        model.addAttribute("dataSearch", dataSearch);
        return "customer/view/shop";
    }

    @GetMapping("shop-details/{id}")
    public String shopDetails(Model model, @PathVariable("id") Integer sanPhamId) {
        Optional<SanPham> optSP = sanPhamService.findById(sanPhamId);
        if (optSP.isPresent()) {
            ShopDetailsDTO dto = new ShopDetailsDTO();
            List<Integer> mauSacIds = chiTietSanPhamService.getLstMauSacBySanPhamId(sanPhamId);
            List<HinhAnh> lstHinhAnh = hinhAnhService.getHinhAnhBySanPhamIdAndMauSacIds(sanPhamId, mauSacIds);
            List<String> lstHinhAnhStr = new ArrayList<>();

            for (HinhAnh ha : lstHinhAnh) {
                lstHinhAnhStr.add(ha.getTenAnh());
            }
            if (lstHinhAnhStr.size() < mauSacIds.size()) {
                for (int i = 0; i < mauSacIds.size() - lstHinhAnhStr.size() - 1; i++) {
                    lstHinhAnhStr.add("default.png");
                }
            }
            if(lstHinhAnhStr.size()%2==0)
            {
                dto.setAnhChinhs1(lstHinhAnhStr.subList(0, lstHinhAnhStr.size()/2));
                dto.setAnhChinhs2(lstHinhAnhStr.subList(lstHinhAnhStr.size()/2, lstHinhAnhStr.size()));
            }
            else
            {
                dto.setAnhChinhs1(lstHinhAnhStr.subList(0, lstHinhAnhStr.size()/2+1));
                dto.setAnhChinhs2(lstHinhAnhStr.subList(lstHinhAnhStr.size()/2+1, lstHinhAnhStr.size()));
            }
            dto.setAnhChinhs(lstHinhAnhStr);
            dto.setSanPhamId(sanPhamId);
            dto.setGia(optSP.get().getGia());
            dto.setTenSanPham(optSP.get().getTenSanPham());
            dto.setMoTa(optSP.get().getMoTa());
            dto.setSoLuong(1);
            List<KichCo> lstKichCo = kichCoService.selectAllKichCoBySanPhamId(sanPhamId);
            List<MauSac> lstMauSac = mauSacService.getAllMauSacExistBySPId(sanPhamId);
            dto.setLstKichCo(lstKichCo);
            dto.setLstMauSac(lstMauSac);
            model.addAttribute("shopDetails", dto);
        }

        return "customer/view/shop-details";
    }
}
