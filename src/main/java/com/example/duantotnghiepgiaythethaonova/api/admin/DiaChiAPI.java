package com.example.duantotnghiepgiaythethaonova.api.admin;


import com.example.duantotnghiepgiaythethaonova.dto.DiaChiDTO;
import com.example.duantotnghiepgiaythethaonova.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "diaChiAPIOfAdmin")
public class DiaChiAPI {
    @Autowired
    private DiaChiService diaChiService;

//    @PostMapping("/admin/api/dia-chi")
//    public DiaChiDTO themMoiDiaChi(@RequestBody DiaChiDTO diaChiDTO) {
//
//        DiaChiDTO entityError = new DiaChiDTO();
//
//        if (diaChiDTO.getCity().equalsIgnoreCase("---Chọn thành phố---")
//                || diaChiDTO.getDistrict().equalsIgnoreCase("---Chọn thành huyện---")
//                || diaChiDTO.getWard().equalsIgnoreCase("---Chọn xã---")) {
//            return diaChiService.save(entityError);
//        }
//
//
//        return diaChiService.save(diaChiDTO);
//    }


    @DeleteMapping("/admin/api/dia-chi")
    public void xoaDiaChi(@RequestBody Integer[] ids) {
        diaChiService.delete(ids);
    }


}
