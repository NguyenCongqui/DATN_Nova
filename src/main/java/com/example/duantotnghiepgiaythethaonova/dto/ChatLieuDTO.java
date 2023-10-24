package com.example.duantotnghiepgiaythethaonova.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatLieuDTO extends BaseDTO<BaseDTO> {
	private Integer IdChatLieu;

	private int soSanPhamCungChatLieu;
	
	private String tenChatLieu;
	
	private String tenChatLieuSearch;
	
	private Boolean daXoa;
}
