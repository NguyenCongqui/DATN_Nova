package com.example.duantotnghiepgiaythethaonova.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface InHoaDonService {
    //Bán hàng
    ResponseEntity<byte[]> generatePdf(Integer hoaDonId);

    //Đơn tại quầy
    ResponseEntity<byte[]> generatePdfDonTaiQuay(Integer hoaDonId);

    //Đơn đặt hàng
    ResponseEntity<byte[]> generatePdfDonDatHang(Integer hoaDonId);
}
