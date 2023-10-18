package com.example.duantotnghiepgiaythethaonova.dto.composite;

import com.example.duantotnghiepgiaythethaonova.dto.HinhAnhDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnhMauSacDTO {
	//mau sac id
	private Integer mauSacAddImagesId;
	
	private List<MultipartFile> imgFiles;
	
	private List<HinhAnhDTO> hinhAnhDTOs;
	
	private List<String> imgLaAnhChinh;
	
	private List<String> imgCoHienThi;
	
	private List<Resource> imgFilesResource;
	
	private Integer laAnhChinh;
	
	private String tenMauSacAddImg;

	private Integer coHienThiHinhAnh;
}
