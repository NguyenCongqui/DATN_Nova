package com.example.duantotnghiepgiaythethaonova.dto.composite;

import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDetailsDTO {
	private Integer sanPhamId;
	
	private String tenSanPham;
	
	private BigDecimal gia;
	
	private List<String> anhChinhs;
	
	private List<String> anhChinhs1;
	
	private List<String> anhChinhs2;
	
	private List<KichCo> lstKichCo;
	
	private List<MauSac> lstMauSac;
	
	private int soLuongConLai;

	private Integer hoaDonId;
	
	private Integer kichCoId;
	
	private Integer mauSacId;
	
	private String moTa;
	
	private Integer soLuong;
}
