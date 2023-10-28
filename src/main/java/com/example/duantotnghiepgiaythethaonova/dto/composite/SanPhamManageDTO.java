package com.example.duantotnghiepgiaythethaonova.dto.composite;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamManageDTO {
	private Integer sanPhamId;
	
	@Min(value = 0, message = "Kiểu dáng sản phẩm không được để trống")
	private Integer kieuDangId;
	
	@Min(value = 0, message = "Chất liệu sản phẩm không được để trống")
	private Integer chatLieuId;
	
	@Min(value = 0, message = "Thương hiệu không được để trống")
	private Integer thuongHieuId;

	private Boolean daXoa;
	
	@NotEmpty(message = "Tên sản phẩm không được để trống")
	private String tenSanPham;
	
	@NotEmpty(message = "Mô tả sản phẩm không được để trống")
	private String moTa;
	
	@NotNull(message = "Số lượng không được để trống")
	@Min(value = 0, message = "Số lượng không được nhỏ hơn 0")
	private Integer soLuong;
	
	@DecimalMin(value = "1000", message = "Giá không được nhỏ hơn 1.000")
	@NotNull(message = "Giá không được để trống")
	private BigDecimal gia;
	
	@NotEmpty(message = "Danh sách kích cỡ sản phẩm không được để trống")
	private List<@Valid Integer> kichCoIds;
	
	@NotEmpty(message = "Danh sách màu sắc sản phẩm không được để trống")
	private List<@Valid Integer> mauSacIds;
	
	private HashMap<Integer, String> lstMauSacAddImg;
	 
	private Boolean isEdit = false;
	
	private Boolean isCreatedImg = false;
	
	private Boolean isCreatedValueImg = false;
	
	private Boolean isGenaratedData = false;
	
	private Boolean isAddProductImageSuccess = false;
	
	private Boolean isAddProductSuccess = false;
	
	private Boolean isHaveImg = false;
	
	private List<HinhAnhMauSacDTO> lstHinhAnhMauSacDTO;
}
