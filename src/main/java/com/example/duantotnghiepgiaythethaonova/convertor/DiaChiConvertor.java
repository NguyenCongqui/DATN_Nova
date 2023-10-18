package com.example.duantotnghiepgiaythethaonova.convertor;

import com.example.duantotnghiepgiaythethaonova.dto.DiaChiDTO;
import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import org.springframework.stereotype.Component;

@Component
public class DiaChiConvertor {
	
		public DiaChi toEntity(DiaChiDTO dto) {
			DiaChi entity = new DiaChi();
			if(dto.getId() != null) {
				entity.setIdDiaChi(dto.getId());
			}
			entity.setDiaChi(dto.getDiaChi());
			entity.setHoTen(dto.getHoTen());
			entity.setSoDienThoai(dto.getSoDienThoai());
			entity.setLaDiaChiMacDinh(dto.isLaDiaChiMacDinh());
			return entity ;
		}
		
		public DiaChiDTO toDTO(DiaChi entity) {
			DiaChiDTO dto = new DiaChiDTO();
			dto.setId(entity.getIdDiaChi());
			dto.setDiaChi(entity.getDiaChi());
			dto.setHoTen(entity.getHoTen());
			dto.setSoDienThoai(entity.getSoDienThoai());
			dto.setLaDiaChiMacDinh(entity.isLaDiaChiMacDinh());
			return dto ;
		}
	
}
