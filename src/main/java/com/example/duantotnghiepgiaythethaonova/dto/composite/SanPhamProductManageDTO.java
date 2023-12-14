package com.example.duantotnghiepgiaythethaonova.dto.composite;

import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamProductManageDTO {
	private SanPham sanPham;

	private ChiTietSanPham chiTietSanPham;
	
	private int tongSoLuong;

	private BigDecimal giaBan;
	
	private List<String> anhChinhs;
}
