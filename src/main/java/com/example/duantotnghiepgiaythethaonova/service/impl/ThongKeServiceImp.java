package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.dto.BestSellerDTO;
import com.example.duantotnghiepgiaythethaonova.dto.DoanhSoChart;
import com.example.duantotnghiepgiaythethaonova.dto.DoanhSoDTO;
import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.HoaDonChiTietRepository;
import com.example.duantotnghiepgiaythethaonova.repository.HoaDonRepository;
import com.example.duantotnghiepgiaythethaonova.repository.SanPhamChiTietRepository;
import com.example.duantotnghiepgiaythethaonova.response.ThongKe;
import com.example.duantotnghiepgiaythethaonova.service.HinhAnhService;
import com.example.duantotnghiepgiaythethaonova.service.HoaDonService;
import com.example.duantotnghiepgiaythethaonova.service.ThongKeService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThongKeServiceImp implements ThongKeService {

    private final HoaDonService hoaDonService;
    private final SanPhamChiTietRepository sanPhamChiTietRepository;
    private final HoaDonRepository hoaDonRepository;
    private final HinhAnhService hinhAnhService;
    private static final Integer TRANGTHAI_HOAN_THANH = 7;
    private static final Integer TRANGTHAI_DA_GIAO = 4;
    private static final Integer TRANGTHAI_DANG_GIAO = 3;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;


    @Override
    public List<DoanhSoChart> getDoanhSoChart(LocalDate startDate, LocalDate endDate) {
        ZoneId zoneId = ZoneId.of("UTC");
        Date start = Date.from(startDate.atStartOfDay(zoneId).toInstant());
        Date end = Date.from(endDate.atTime(LocalTime.MAX).toInstant(ZoneOffset.UTC));

        Map<LocalDate, BigDecimal> mapData = hoaDonRepository.getHoaDonInRangeDate(start, end)
                .stream()
                .filter(hd -> hd.getTrangThai() != null && checkTrangThaiDonHang(hd.getTrangThai().getIdTrangThai()))
                .collect(Collectors.groupingBy(
                        hd -> hd.getNgayTao().toInstant().atZone(zoneId).toLocalDate(),
                        Collectors.reducing(BigDecimal.ZERO, HoaDon::getTongTienHoaDon, BigDecimal::add)
                ));

        List<DoanhSoChart> list = getRangeDate(startDate, endDate);
        for (DoanhSoChart doanhSoChart : list) {
            LocalDate date = doanhSoChart.getDate();
            BigDecimal value = mapData.getOrDefault(date, BigDecimal.ZERO);
            doanhSoChart.setValue(value.doubleValue());
        }

        return list;
    }

    private List<DoanhSoChart> getRangeDate(LocalDate startDate, LocalDate endDate) {
        List<DoanhSoChart> dateList = new ArrayList<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            dateList.add(new DoanhSoChart(currentDate));
            currentDate = currentDate.plusDays(1);
        }

        return dateList;
    }


    @Override
    public DoanhSoDTO getDoanhSoData() {

        return getDoanhSoDTOByDate(LocalDate.now(), LocalDate.now().withDayOfMonth(1));
    }

    @Override
    public DoanhSoDTO getDoanhSoDataDate(LocalDate startDate, LocalDate endDate) {
        return getDoanhSoDTOByDate(startDate, endDate);
    }

    @NotNull
    private DoanhSoDTO getDoanhSoDTOByDate(LocalDate startOfMonth, LocalDate current) {
        Date start = Date.from(startOfMonth.atStartOfDay().toInstant(ZoneOffset.UTC));
        Date end = Date.from(LocalDateTime.of(current, LocalTime.MAX).toInstant(ZoneOffset.UTC));

        List<HoaDon> hoadon = getHoaDonInRange(start, end);

        Integer donHangThang = (int) hoadon.size();
        Integer donHangNgay = Math.toIntExact(hoadon.stream().filter(hoaDon -> compareDate(hoaDon.getNgayTao(), current))
                .count());
        Double doanhSoThang = hoadon.stream()
                .map(HoaDon::getTongTienHoaDon)
                .reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
        Double doanhSoNgay = hoadon.stream()
                .filter(hoaDon -> compareDate(hoaDon.getNgayTao(), current))
                .map(HoaDon::getTongTienHoaDon)
                .reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
        Integer hangBanDuoc = Math.toIntExact(hoadon.stream().flatMap(hd -> hd.getHoaDonChiTiets().stream()).mapToInt(HoaDonChiTiet::getSoLuong).sum());
        return new DoanhSoDTO(donHangThang, doanhSoThang, donHangNgay, doanhSoNgay, hangBanDuoc);
    }

    private boolean compareDate(Date date1, LocalDate date2) {
        LocalDate ld1 = date1.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
        return ld1.isEqual(date2);
    }

    @NotNull
    private List<HoaDon> getHoaDonInRange(Date startOfMonth, Date current) {
        return hoaDonRepository.getHoaDonInRangeDate(startOfMonth, current)
                .stream()
                .filter(hd -> hd.getTrangThai() != null && checkTrangThaiDonHang(hd.getTrangThai().getIdTrangThai()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BestSellerDTO> getMatHangBanChay() {
//        List<Integer> listHoaDonId = hoaDonService.getAll().stream()
//                .filter(hoaDon -> hoaDon.getTrangThai() != null && checkTrangThaiDonHang(hoaDon.getTrangThai().getIdTrangThai()))
//                .map(HoaDon::getIdHoaDon)
//                .collect(Collectors.toList());
//
//        Pageable top5 = PageRequest.of(0, 5);
//        List<BestSellerDTO> result = sanPhamChiTietRepository.layIdChiTietSanPhamBanChay(listHoaDonId, top5);
//        List<Integer> sanPhamId = result.stream().map(BestSellerDTO::getIdSanPham).collect(Collectors.toList());
//        List<ChiTietSanPham> sanPhams = sanPhamChiTietRepository.layChiTietSanPhamBanChay(sanPhamId);
//        List<ThongKe> hoaDonChiTiets = hoaDonChiTietRepository.thongKe();
//        for (BestSellerDTO dto : result) {
//            BigDecimal tongDoanhSo = BigDecimal.ZERO;
//            for (ThongKe hdct  : hoaDonChiTiets) {
//                for(ChiTietSanPham sp : sanPhams){
//                    if (Objects.equals(sp.getIdCTSP(), dto.getIdSanPham())) {
//                        //get doanh so
//                        int soLuong = hdct.getSoLuong();
//                        tongDoanhSo = hdct.getTongDoanhThu();
////                        tongDoanhSo = tongDoanhSo.add(BigDecimal.valueOf(soLuong).multiply(donGia));
//
//                        SanPham sanPham = sp.getSanPham();
//                        dto.setTenSanPham(sanPham.getTenSanPham());
//                        dto.setGiaBan(sp.getGia().doubleValue());
//                        dto.setChatLieu(sanPham.getChatLieu().getTenChatLieu());
//                        dto.setThuongHieu(sanPham.getThuongHieu().getTenThuongHieu());
//                        dto.setKieuDang(sanPham.getKieuDang().getTenKieuDang());
//                        dto.setMauSac(sp.getMauSac().getTenMauSac());
//                        dto.setKichCo(sp.getKichCo().getTenKichCo());
//                        dto.setSoLuong(soLuong);
//                        List<HinhAnh> lstAnhChinh = hinhAnhService.getHinhAnhChinhBySanPhamId(sanPham.getIdSanPham());
//                        String anhChinhs = lstAnhChinh.stream().filter(HinhAnh::getLaAnhChinh).findFirst().map(HinhAnh::getTenAnh).orElse("");
//                        dto.setAnhChinhs(anhChinhs);
//                        dto.setTongDoanhthu(tongDoanhSo);
//                    }
//                }
//
//            }
//        }
//
//        return result;
    return null;
    }


    @Override
    public List<ThongKe> getSPBanChay() {
        return hoaDonChiTietRepository.thongKe();
    }

    private boolean checkTrangThaiDonHang(Integer idTrangThai) {
        return Objects.equals(idTrangThai, TRANGTHAI_HOAN_THANH) || Objects.equals(idTrangThai, TRANGTHAI_DA_GIAO) || Objects.equals(idTrangThai, TRANGTHAI_DANG_GIAO);
    }
}
