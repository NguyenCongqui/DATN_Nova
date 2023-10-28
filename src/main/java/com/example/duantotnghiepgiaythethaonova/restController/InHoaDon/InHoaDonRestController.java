package com.example.duantotnghiepgiaythethaonova.restController.InHoaDon;


import com.example.duantotnghiepgiaythethaonova.service.InHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
public class InHoaDonRestController {
    @Autowired
    InHoaDonService inHoaDonService;

    @RequestMapping("/in-hoa-don/{hoaDonId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Integer hoaDonId) {
        return inHoaDonService.generatePdf(hoaDonId);
    }

    @RequestMapping("/in-hoa-don-don-dat-hang/{hoaDonId}")
    public ResponseEntity<byte[]> generatePdfDonDatHang(@PathVariable Integer hoaDonId) {
        return inHoaDonService.generatePdfDonDatHang(hoaDonId);
    }

    @RequestMapping("/in-hoa-don-don-tai-quay/{hoaDonId}")
    public ResponseEntity<byte[]> generatePdfDonTaiQuay(@PathVariable Integer hoaDonId) {
        return inHoaDonService.generatePdfDonTaiQuay(hoaDonId);
    }
}
