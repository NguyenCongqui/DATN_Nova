package com.example.duantotnghiepgiaythethaonova.controller.admin.thongKe;

import com.example.duantotnghiepgiaythethaonova.dto.DoanhSoChart;
import com.example.duantotnghiepgiaythethaonova.service.ThongKeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ThongKeController {

    private final ThongKeService thongKeService;
    @GetMapping("/admin/thong-ke")
    public String index(Model model){
        model.addAttribute("bestSeller", thongKeService.getMatHangBanChay());
        model.addAttribute("thongkespbanchay",thongKeService.getSPBanChay());
        return "admin/thongke/index";
    }
    @GetMapping("/api/doanh-so/chart")
    public @ResponseBody ResponseEntity<?> fetchChartData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        List<DoanhSoChart> data = thongKeService.getDoanhSoChart(startDate, endDate);
        return ResponseEntity.ok().body(data);
    }
    @GetMapping("/api/doanh-so/data")
    public @ResponseBody ResponseEntity<?> fetchStatistictData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return ResponseEntity.ok().body(thongKeService.getDoanhSoDataDate(startDate, endDate));
    }
}
