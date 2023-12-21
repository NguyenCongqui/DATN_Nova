package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO;
import com.example.duantotnghiepgiaythethaonova.dto.DoanhSoChart;
import com.example.duantotnghiepgiaythethaonova.dto.DoanhSoDTO;
import com.example.duantotnghiepgiaythethaonova.response.ThongKe;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ThongKeService {
    List<DoanhSoChart> getDoanhSoChart(LocalDate startDate, LocalDate endDate);
    DoanhSoDTO getDoanhSoData();
    DoanhSoDTO getDoanhSoDataDate(LocalDate startDate, LocalDate endDate);
    List<BestSellerDTO> getMatHangBanChay();
    List<ThongKe> getSPBanChay();


}
