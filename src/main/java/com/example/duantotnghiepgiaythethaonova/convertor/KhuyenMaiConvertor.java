package com.example.duantotnghiepgiaythethaonova.convertor;

import com.example.duantotnghiepgiaythethaonova.dto.KhuyenMaiDTO;
import com.example.duantotnghiepgiaythethaonova.entity.KhuyenMai;
import org.springframework.stereotype.Component;

@Component
public class KhuyenMaiConvertor {
	public KhuyenMaiDTO toDTO(KhuyenMai entity) {
		KhuyenMaiDTO dto = new KhuyenMaiDTO();
		dto.setId(entity.getIdKhuyenMai());
		dto.setGiaTriToiThieu(entity.getGiaTriToiThieu());
		dto.setNgayBatDau(entity.getNgayBatDau());
		dto.setNgayKetThuc(entity.getNgayKetThuc());
		dto.setPhanTramGiam(entity.getPhanTramGiam());
		dto.setTenKhuyenMai(entity.getTenKhuyenMai());
		dto.setTrangThai(entity.getTrangThai());
		return dto ;
	}
}
