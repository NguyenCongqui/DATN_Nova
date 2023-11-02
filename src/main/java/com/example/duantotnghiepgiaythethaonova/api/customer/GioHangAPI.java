package com.example.duantotnghiepgiaythethaonova.api.customer;

import com.example.duantotnghiepgiaythethaonova.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "gioHangAPI")
public class GioHangAPI {
	
	@Autowired
	private GioHangService gioHangService ;
	
	@DeleteMapping("/khachhang/api/gio-hang/tinh-tien")
	public int tinhTienGioHang(@RequestBody  int[] idGioHangChiTiet) {
			return gioHangService.tinhTienGioHangTheoMaGioHangChiTiet(idGioHangChiTiet);
	}
}
