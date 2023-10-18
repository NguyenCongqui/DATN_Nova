package com.example.duantotnghiepgiaythethaonova.convertor;

import com.example.duantotnghiepgiaythethaonova.dto.HoaDonChiTietDTO;
import com.example.duantotnghiepgiaythethaonova.entity.HoaDonChiTiet;
import org.springframework.stereotype.Component;

@Component
public class HoaDonChiTietConvertor {
	public HoaDonChiTietDTO toDTO(HoaDonChiTiet entity) {
		HoaDonChiTietDTO dto = new HoaDonChiTietDTO();
		dto.setId(entity.getIdHoaDonCT());
		dto.setDonGia(entity.getDonGia());
		dto.setSoLuong(entity.getSoLuong());
		dto.setTongTien(entity.getTongTien());
		
		return dto ;
	}
}
