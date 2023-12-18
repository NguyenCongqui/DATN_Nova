package com.example.duantotnghiepgiaythethaonova.api.customer;

import com.example.duantotnghiepgiaythethaonova.dto.DiaChiDTO;
import com.example.duantotnghiepgiaythethaonova.dto.TaiKhoanDTO;
import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import com.example.duantotnghiepgiaythethaonova.entity.KhachHang;
import com.example.duantotnghiepgiaythethaonova.repository.DiaChiRepository;
import com.example.duantotnghiepgiaythethaonova.repository.KhachHangRepository;
import com.example.duantotnghiepgiaythethaonova.service.DiaChiService;
import com.example.duantotnghiepgiaythethaonova.service.KhachHangService;
import com.example.duantotnghiepgiaythethaonova.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController(value = "taiKhoanAPI")
public class TaiKhoanAPI {
	
	@Autowired
	private KhachHangService khachHangService ;
	
	@Autowired
	private NguoiDungService nguoiDungService ;
	
	@Autowired
	private DiaChiService diaChiService ;
	
	@PutMapping("/khach-hang/api/tai-khoan/doi-mat-khau")
	public ResponseEntity<String> doiMatKhauTaiKhoan(@RequestBody TaiKhoanDTO taiKhoanDTO) {
		if(taiKhoanDTO.getPassword().equals(taiKhoanDTO.getConfirm_password())){
			khachHangService.capNhatMatKhau(taiKhoanDTO);
			nguoiDungService.capNhatMatKhau(taiKhoanDTO);
			return ResponseEntity.ok("Đổi mật khẩu thành công !");
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mật khẩu không khớp !");
		}
	}
	@PostMapping("/khach-hang/api/dia-chi/khach-hang-dang-nhap")
	public DiaChiDTO themMoiDiaChiKhachHangDangNhap(@RequestBody DiaChiDTO diaChiDTO) {
		return diaChiService.save(diaChiDTO);
	}
	@DeleteMapping("/khach-hang/api/dia-chi")
	public void xoaDiaChi(@RequestBody Integer[] ids) {
		diaChiService.delete(ids);
	}
	
	@Autowired
	private KhachHangRepository khachHangRepository ;
	
	@Autowired
	private DiaChiRepository diaChiRepository ;
	
	@PostMapping("/khach-hang/api/update-dia-chi-mac-dinh")
    public @ResponseBody Map<String, Object> updateDiaChiMacDinh(@RequestParam("DiaChiID") Integer DiaChiID,
                                                                 @RequestParam("KhachHangID") Integer KhachHangID) {
        Map<String, Object> response = new HashMap<>();
        Optional<DiaChi> optionalDiaChi = diaChiRepository.findById(DiaChiID);
        Optional<KhachHang> optionalKhachHang = khachHangRepository.findById(KhachHangID);
        if (optionalDiaChi.isPresent() && optionalKhachHang.isPresent()) {
            DiaChi diaChi = optionalDiaChi.get();
            KhachHang khachHang = optionalKhachHang.get();

            for (DiaChi existingDiaChi : khachHang.getListDiaChi()) {
                if (existingDiaChi.getIdDiaChi() == diaChi.getIdDiaChi()) {
                    existingDiaChi.setLaDiaChiMacDinh(true);
                } else {
                    existingDiaChi.setLaDiaChiMacDinh(false);
                }
                diaChiRepository.save(existingDiaChi);
            }

            khachHang.setDiaChi(diaChi);
            khachHangRepository.save(khachHang);

        } else {
            response.put("success", false);
            response.put("error", "Lỗi");
        }

        response.put("success", true);


        return response;
    }
	
}
