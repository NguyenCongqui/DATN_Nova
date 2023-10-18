package com.example.duantotnghiepgiaythethaonova.service;



import com.example.duantotnghiepgiaythethaonova.dto.GioHangChiTietDTO;

import java.util.List;

public interface GioHangChiTietService  {

	List<GioHangChiTietDTO> findAllByGioHangId(Integer id);

	void capNhatSoLuongGioHangChiTiet(Integer[] ids, Integer[] soLuongs,Integer[] donGias);

	void capNhatGioHangThanhDaXoaById(Integer id);

	void capNhatTatCaGioHangThanhDaXoaById();

	Integer getTongTienByKhachHangID(Integer id);

	GioHangChiTietDTO findById(Integer id);

}
