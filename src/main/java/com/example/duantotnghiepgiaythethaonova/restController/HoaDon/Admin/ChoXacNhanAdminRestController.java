package com.example.duantotnghiepgiaythethaonova.restController.HoaDon.Admin;


import com.example.duantotnghiepgiaythethaonova.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChoXacNhanAdminRestController {
    @Autowired
    HoaDonService hoaDonService;

    @RequestMapping("/updateXacNhan/{id}")
    public ResponseEntity<String> updateXacNhan(@PathVariable("id") Integer hoaDonId) {
        return hoaDonService.updateXacNhanChoXacNhan(hoaDonId);
    }

    @RequestMapping("/updateHuyDon/{id}")
    public ResponseEntity<String> updateHuyDon(@PathVariable("id") Integer hoaDonId) {
        return hoaDonService.updateHuyDonChoXacNhan(hoaDonId);
    }

    //XÁC NHẬN TẤT CẢ
    @RequestMapping("/updateXacNhanAll")
    public ResponseEntity<String> updateXacNhanAll() {
        return hoaDonService.updateXacNhanAllChoXacNhan();
    }

    //HỦY TẤT CẢ
    @RequestMapping("/updateHuyAll")
    public ResponseEntity<String> updateHuyAll() {
        return hoaDonService.updateHuyAllChoXacNhan();
    }
}
