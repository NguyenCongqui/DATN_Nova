package com.example.duantotnghiepgiaythethaonova.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO extends BaseDTO<BaseDTO> {

	private Integer sanPhamId;
	
	private Integer kichCoId;

	private Integer mauSacId;
	
	@NotNull(message = "Số lượng không được để trống")
	@Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
	private Integer soLuong;
	
	private Boolean coHienThi;
	
	private Boolean daXoa;
	
	private String tenKichCo ;
	
	private String tenMauSac ;
	
	private SanPhamDTO sanPhamDTO ;

}
