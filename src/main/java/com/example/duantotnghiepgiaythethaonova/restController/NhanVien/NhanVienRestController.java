package com.example.duantotnghiepgiaythethaonova.restController.NhanVien;

import com.example.duantotnghiepgiaythethaonova.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@RestController
public class NhanVienRestController {

    @Autowired
    NhanVienService nhanVienService;


    @PostMapping("/ThemMoiNhanVien")
    public @ResponseBody Map<String, Object> themMoiNhanVien(@RequestParam("email") String email,
                                                             @RequestParam("diaChi") String diaChi,
                                                             @RequestParam("soDienThoai") String soDienThoai,
                                                             @RequestParam("ho") String ho,
                                                             @RequestParam("ten") String ten,
                                                             @RequestParam("anhNhanVien") String anhNhanVien,
                                                             @RequestParam("ChucVu") Integer ChucVu) {
        Map<String, Object> response = new HashMap<>();
        try {
            nhanVienService.themMoiNhanVien(email, diaChi, soDienThoai, ho, ten, anhNhanVien, ChucVu, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    @PostMapping("/LuuThongTinChinhSua")
    public Map<String, Object> ChinhSuaNhanVien(@RequestParam("idNhanVien") Integer idNhanVien,
                                                @RequestParam("email") String email,
                                                @RequestParam("diaChi") String diaChi,
                                                @RequestParam("soDienThoai") String soDienThoai,
                                                @RequestParam("hoTen") String hoTen,
                                                @RequestParam("anhNhanVien") String anhNhanVien,
                                                @RequestParam("ChucVu") Integer ChucVu) {

        Map<String, Object> response = new HashMap<>();
        try {
            nhanVienService.ChinhSuaNhanVien(idNhanVien, email, diaChi, soDienThoai, hoTen, anhNhanVien, ChucVu, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    @RequestMapping("/xoaNhanVien/{id}")
    public ResponseEntity<String> XoaNhanVien(@PathVariable("id") Integer id){
        return nhanVienService.XoaNhanVien(id);
    }







}
