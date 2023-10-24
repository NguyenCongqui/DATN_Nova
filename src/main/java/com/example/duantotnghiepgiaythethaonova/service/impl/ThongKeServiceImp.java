package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO;
import com.example.duantotnghiepgiaythethaonova.dto.DoanhSoChart;
import com.example.duantotnghiepgiaythethaonova.dto.DoanhSoDTO;
import com.example.duantotnghiepgiaythethaonova.repository.CTSPRepository;
import com.example.duantotnghiepgiaythethaonova.repository.HoaDonRepository;
import com.example.duantotnghiepgiaythethaonova.service.HinhAnhService;
import com.example.duantotnghiepgiaythethaonova.service.HoaDonService;
import com.example.duantotnghiepgiaythethaonova.service.ThongKeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ThongKeServiceImp implements ThongKeService {

//    private final HoaDonService hoaDonService;
//    private final CTSPRepository sanPhamChiTietRepository;
//    private final HoaDonRepository hoaDonRepository;
//    private final HinhAnhService hinhAnhService;
    private static final Long TRANGTHAI_HOAN_THANH = 7L;
    private static final Long TRANGTHAI_DA_GIAO = 4L;
    private static final Long TRANGTHAI_DANG_GIAO = 3L;

    @Override
    public List<DoanhSoChart> getDoanhSoChart(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public DoanhSoDTO getDoanhSoData() {
        return null;
    }

    @Override
    public DoanhSoDTO getDoanhSoDataDate(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<BestSellerDTO> getMatHangBanChay() {
        return null;
    }

    private boolean checkTrangThaiDonHang(Integer idTrangThai){
        return Objects.equals(idTrangThai, TRANGTHAI_HOAN_THANH) || Objects.equals(idTrangThai, TRANGTHAI_DA_GIAO) || Objects.equals(idTrangThai, TRANGTHAI_DANG_GIAO);
    }
}
