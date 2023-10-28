package com.example.duantotnghiepgiaythethaonova.convertor;

import com.example.duantotnghiepgiaythethaonova.dto.SanPhamChiTietDTO;
import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import org.springframework.stereotype.Component;

@Component
public class SanPhamChiTietConvertor {

	public SanPhamChiTietDTO toDTO(ChiTietSanPham entity) {

		SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
		dto.setDaXoa(entity.getDaXoa());
		dto.setId(entity.getIdCTSP());
		dto.setTenKichCo(entity.getKichCo().getTenKichCo());
		dto.setTenMauSac(entity.getMauSac().getTenMauSac());
		dto.setSoLuong(entity.getSoLuong());
		return dto ;
	}
}
