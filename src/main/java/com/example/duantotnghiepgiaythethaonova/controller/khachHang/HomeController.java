package com.example.duantotnghiepgiaythethaonova.controller.khachHang;

import com.example.duantotnghiepgiaythethaonova.dto.composite.SanPhamTaiQuayDTO;
import com.example.duantotnghiepgiaythethaonova.dto.composite.ShowSanPhamdto;
import com.example.duantotnghiepgiaythethaonova.entity.HinhAnh;
import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import com.example.duantotnghiepgiaythethaonova.service.*;
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
    private CTSPService chiTietSanPhamService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private StorageService storageService;

    @ModelAttribute("listGia")
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
        resultSP.setLstShowSanPhamTaiQuayDTO(lstSSPTQ);
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
}
