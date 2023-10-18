package com.example.duantotnghiepgiaythethaonova.convertor;


import com.example.duantotnghiepgiaythethaonova.dto.GioHangChiTietDTO;
import com.example.duantotnghiepgiaythethaonova.entity.GioHangChiTiet;
import org.springframework.stereotype.Component;

@Component
public class GioHangChiTietConvertor {
	public GioHangChiTietDTO toDTO(GioHangChiTiet entity) {
		GioHangChiTietDTO dto = new GioHangChiTietDTO();
		dto.setId(entity.getIdGioHangCT());
		dto.setDaXoa(entity.getDaXoa());
		dto.setDonGia(entity.getDonGia());
		dto.setSoLuong(entity.getSoLuong());
		dto.setThanhTien(entity.getThanhTien());
		dto.setTrangThai(entity.getTrangThai());
		dto.setGioHangId(entity.getGioHang().getIdGioHang());
		dto.setSanPhamChiTietId(entity.getChiTietSanPham().getIdCTSP());
		return dto ;
	}
	
	public GioHangChiTiet toEntity(GioHangChiTietDTO dto) {
		GioHangChiTiet entity = new GioHangChiTiet();
		if(dto.getId() != null) {
			entity.setIdGioHangCT(dto.getId());
		}
		entity.setDaXoa(dto.getDaXoa());
		entity.setDonGia(dto.getDonGia());
		entity.setSoLuong(dto.getSoLuong());
		entity.setThanhTien(dto.getThanhTien());
		entity.setTrangThai(dto.getTrangThai());
		return entity ;
	}
}
