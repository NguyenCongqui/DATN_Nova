package com.example.duantotnghiepgiaythethaonova.convertor;

import com.example.duantotnghiepgiaythethaonova.dto.GioHangDTO;
import com.example.duantotnghiepgiaythethaonova.entity.GioHang;
import org.springframework.stereotype.Component;

@Component
public class GioHangConvertor {
	
	public GioHangDTO toDTO(GioHang entity) {
		GioHangDTO dto = new GioHangDTO();
		dto.setId(entity.getIdGioHang());
		dto.setTongTien(entity.getTongTien());
		dto.setTrangThai(entity.getTrangThai());
		dto.setKhachHangId(entity.getIdGioHang());
		return dto ;
	}
	
	public GioHang toEntity(GioHangDTO dto) {
		GioHang entity = new GioHang();
		if(dto.getId() != null) {
			entity.setIdGioHang(entity.getIdGioHang());
		}
		entity.setTongTien(entity.getTongTien());
		entity.setTrangThai(entity.getTrangThai());
		return entity ;
	}
	
}
