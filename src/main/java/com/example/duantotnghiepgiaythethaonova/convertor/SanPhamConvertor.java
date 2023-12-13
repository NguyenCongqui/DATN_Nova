package com.example.duantotnghiepgiaythethaonova.convertor;

import com.example.duantotnghiepgiaythethaonova.dto.SanPhamDTO;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import org.springframework.stereotype.Component;

@Component
public class SanPhamConvertor {
	
	public SanPhamDTO toDTO(SanPham entity) {
		SanPhamDTO dto = new SanPhamDTO();
		dto.setIdSanPhamDTO(entity.getIdSanPham());
//		dto.setGia(entity.getGia());
		dto.setMoTa(entity.getMoTa());
		dto.setTenSanPham(entity.getTenSanPham());
		dto.setTenChatLieu(entity.getChatLieu().getTenChatLieu());
		dto.setTenKieuDang(entity.getKieuDang().getTenKieuDang());
		return dto ;
	}
	
}
