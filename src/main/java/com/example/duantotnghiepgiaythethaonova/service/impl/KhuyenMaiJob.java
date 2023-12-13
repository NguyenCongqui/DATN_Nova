package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.KhuyenMai;
import com.example.duantotnghiepgiaythethaonova.repository.KhuyenMaiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KhuyenMaiJob {
    private final KhuyenMaiRepository khuyenMaiRepository;

    //    @Scheduled(cron = "0 0/1 * * * *", zone = "Asia/Ho_Chi_Minh") 1 minute 1 time
//    @Scheduled(cron = "0/2 * * * * *", zone = "Asia/Ho_Chi_Minh")  // Thực hiện công việc cần được lên lịch mỗi 2 giây theo múi giờ Hồ Chí Minh
    @Scheduled(cron = "0 0 0 * * *") //Cứ đến 12h quét 1 lần
    @Transactional
    public void updateStatusVoucher() {
        List<KhuyenMai> list = khuyenMaiRepository.findAll();
        Date currentDate = new Date();

        List<Integer> disable = list.stream()
                .filter(item -> item.getNgayBatDau() == null || item.getNgayKetThuc() == null ||
                        currentDate.before(item.getNgayBatDau()) || currentDate.after(item.getNgayKetThuc()))
                .filter(item -> currentDate.before(item.getNgayBatDau()) || currentDate.after(item.getNgayKetThuc()))
                .filter(KhuyenMai::getTrangThai)
                .map(KhuyenMai::getIdKhuyenMai)
                .collect(Collectors.toList());
        System.out.println("Đã check giảm giá hết hạn");
        khuyenMaiRepository.updateStatusByDate(disable, false);

        List<Integer> enable = list.stream()
                .filter(item -> item.getNgayBatDau() != null && item.getNgayKetThuc() != null &&
                        currentDate.before(item.getNgayKetThuc()) && currentDate.after(item.getNgayBatDau()))
                .filter(item -> currentDate.before(item.getNgayKetThuc()) && currentDate.after(item.getNgayBatDau()))
                .filter(item -> !item.getTrangThai())
                .map(KhuyenMai::getIdKhuyenMai)
                .collect(Collectors.toList());
        System.out.println("Đã check giảm giá còn hạn");
        khuyenMaiRepository.updateStatusByDate(enable, true);
    }
}
