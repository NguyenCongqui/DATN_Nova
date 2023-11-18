package com.example.duantotnghiepgiaythethaonova.service;



import com.example.duantotnghiepgiaythethaonova.dto.GioHangChiTietDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface GioHangChiTietService  {

	List<GioHangChiTietDTO> findAllByGioHangId(Integer id);

	void capNhatSoLuongGioHangChiTiet(Integer[] ids, Integer[] soLuongs, BigDecimal[] donGias);

	void capNhatGioHangThanhDaXoaById(Integer id);

	void capNhatTatCaGioHangThanhDaXoaById();

	Integer getTongTienByKhachHangID(Integer id);

	GioHangChiTietDTO findById(Integer id);

}
