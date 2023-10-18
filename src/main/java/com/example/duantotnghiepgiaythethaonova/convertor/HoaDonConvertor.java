package com.example.duantotnghiepgiaythethaonova.convertor;

import com.example.duantotnghiepgiaythethaonova.dto.HoaDonDTO;
import com.example.duantotnghiepgiaythethaonova.entity.HoaDon;
import org.springframework.stereotype.Component;

@Component
public class HoaDonConvertor {
	public HoaDonDTO toDTO(HoaDon entity) {
		HoaDonDTO dto = new HoaDonDTO();
		dto.setId(entity.getIdHoaDon());
		dto.setMaDonHang(entity.getMaHoaDon());
		dto.setNguoiNhan(entity.getNguoiNhan());
		dto.setSdtNguoiNhan(entity.getSoDienThoaiNguoiNhan());
		if(entity.getKhuyenMai() != null) {
			dto.setTenKhuyenMai(entity.getKhuyenMai().getTenKhuyenMai());
		}
		dto.setTienShip(entity.getTienShip());
		dto.setTongTienDonHang(entity.getTongTienDonHang());
		dto.setTongTienHoaDon(entity.getTongTienHoaDon());
		dto.setGhiChu(entity.getGhiChu());
		dto.setTrangThai(entity.getTrangThai().getName());
		
		return dto ;
	}
}
