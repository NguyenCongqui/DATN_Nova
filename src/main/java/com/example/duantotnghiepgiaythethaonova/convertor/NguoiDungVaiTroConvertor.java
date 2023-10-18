package com.example.duantotnghiepgiaythethaonova.convertor;

import com.example.duantotnghiepgiaythethaonova.dto.NguoiDungDTO;
import com.example.duantotnghiepgiaythethaonova.dto.NguoiDung_VaiTroDTO;
import com.example.duantotnghiepgiaythethaonova.dto.VaiTroDTO;
import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
import org.springframework.stereotype.Component;

@Component
public class NguoiDungVaiTroConvertor {
	public NguoiDung_VaiTroDTO toDTO(NguoiDung_VaiTro entity) {
		NguoiDung_VaiTroDTO dto = null;
		NguoiDungDTO nguoiDungDTO = null ;
		VaiTroDTO vaiTroDTO = null ;
		if(entity != null) {
			dto = new NguoiDung_VaiTroDTO();
			if(entity.getNguoiDung().getIdNguoiDung() != null) {
				nguoiDungDTO = new NguoiDungDTO();
				nguoiDungDTO.setId(entity.getNguoiDung().getIdNguoiDung());
				nguoiDungDTO.setDaXoa(entity.getNguoiDung().getDaXoa());
				nguoiDungDTO.setEmail(entity.getNguoiDung().getEmail());
				nguoiDungDTO.setMatKhau(entity.getNguoiDung().getMaNguoiDung());
				nguoiDungDTO.setSoDienThoai(entity.getNguoiDung().getSoDienThoai());
				nguoiDungDTO.setTenNguoiDung(entity.getNguoiDung().getTenNguoiDung());
				nguoiDungDTO.setTrangThai(entity.getNguoiDung().getTrangThai());
				nguoiDungDTO.setMatKhau(entity.getNguoiDung().getMatKhau());
			}
			if(entity.getVaiTro().getIdVaiTro() != null) {
				vaiTroDTO = new VaiTroDTO();
				vaiTroDTO.setId(entity.getNguoiDung().getIdNguoiDung());
				vaiTroDTO.setTenVaiTro(entity.getVaiTro().getTenVaiTro());
				vaiTroDTO.setCode(entity.getVaiTro().getCode());
			}
			dto.setIdNguoiDung_VaiTro(entity.getIdNguoiDung_VaiTro());
			dto.setNguoiDungDTO(nguoiDungDTO);
			dto.setVaiTroDTO(vaiTroDTO);
		}
		return dto ;
	}
}
