package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.convertor.GioHangChiTietConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.SanPhamChiTietConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.SanPhamConvertor;
import com.example.duantotnghiepgiaythethaonova.dto.GioHangChiTietDTO;
import com.example.duantotnghiepgiaythethaonova.entity.GioHangChiTiet;
import com.example.duantotnghiepgiaythethaonova.repository.GioHangChiTietRepository;
import com.example.duantotnghiepgiaythethaonova.service.GioHangChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

	@Autowired
	private GioHangChiTietRepository gioHangChiTietRepo ;

	@Autowired
	private GioHangChiTietConvertor gioHangChiTietConvertor ;

	@Autowired
	private SanPhamChiTietConvertor sanPhamChiTietConvertor ;
	@Autowired
	private SanPhamConvertor sanPhamConvertor ;

	@Override
	public List<GioHangChiTietDTO> findAllByGioHangId(Integer id) {
		List<GioHangChiTietDTO> listGioHangChiTietDTO = new ArrayList<GioHangChiTietDTO>();
		List<GioHangChiTiet> listGioHangChiTiet = new ArrayList<GioHangChiTiet>();
		GioHangChiTietDTO gioHangChiTietDTO = null ;
		try {
			listGioHangChiTiet = gioHangChiTietRepo.findAllByGioHangId(id);
			for (GioHangChiTiet gioHangChiTiet : listGioHangChiTiet) {
				gioHangChiTietDTO = new GioHangChiTietDTO();
				gioHangChiTietDTO = gioHangChiTietConvertor.toDTO(gioHangChiTiet);
				listGioHangChiTietDTO.add(gioHangChiTietDTO);
			}
			return listGioHangChiTietDTO;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listGioHangChiTietDTO;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void capNhatSoLuongGioHangChiTiet(Integer[] ids, Integer[] soLuongs, BigDecimal[] donGias) {
		List<Integer> listSoLuong = toListInterger(soLuongs);
		List<BigDecimal> listDonGia = toListBigDecimal(donGias);
		for (int i = 0; i < ids.length; i++) {
			Integer id =  (Integer) Array.get(ids, i);
			Integer soLuong = listSoLuong.get((int) i);
			BigDecimal donGia = listDonGia.get((int) i);
			 if(soLuong > 0) {
				 GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.getOne(id);
				 if(soLuong > gioHangChiTiet.getSoLuong()) {
					 gioHangChiTiet.getChiTietSanPham().setSoLuong(gioHangChiTiet.getChiTietSanPham().getSoLuong()- (soLuong - gioHangChiTiet.getSoLuong()));
				 }else {
					 gioHangChiTiet.getChiTietSanPham().setSoLuong(gioHangChiTiet.getChiTietSanPham().getSoLuong() + (gioHangChiTiet.getSoLuong()) - soLuong  );
				 }

				 gioHangChiTiet.setSoLuong(soLuong);
				 gioHangChiTiet.setDonGia(donGia);
				 gioHangChiTiet.setThanhTien(BigDecimal.valueOf(Math.abs(gioHangChiTiet.getSoLuong()*gioHangChiTiet.getDonGia().doubleValue())));
				 gioHangChiTietRepo.save(gioHangChiTiet);
			 }
		}
	}
	public List<Integer> toListInterger(Integer[] integers) {
		List<Integer> list = new ArrayList<>() ;
		for (int i = 0; i < integers.length; i++) {
			Integer integer = integers[i];
			list.add(integer);
		}
		return list ;
	}

	public List<BigDecimal> toListBigDecimal(BigDecimal[] bigDecimals) {
		List<BigDecimal> list = new ArrayList<>() ;
		for (int i = 0; i < bigDecimals.length; i++) {
			BigDecimal bigDecimal = bigDecimals[i];
			list.add(bigDecimal);
		}
		return list ;
	}

	@Override
	@Transactional
	public void capNhatGioHangThanhDaXoaById(Integer id) {
		GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(id).get();
		if(gioHangChiTiet != null) {
			gioHangChiTiet.setDaXoa(true);
			gioHangChiTietRepo.save(gioHangChiTiet);
		}
	}

	@Override
	@Transactional
	public void capNhatTatCaGioHangThanhDaXoaById() {
		List<GioHangChiTiet> list = gioHangChiTietRepo.findAll();
		if (!list.isEmpty()) {
			for (GioHangChiTiet gioHangChiTiet : list) {
				if(gioHangChiTiet.getDaXoa() == false) {
					gioHangChiTiet.setDaXoa(true);
					gioHangChiTietRepo.save(gioHangChiTiet);
				}
			}
		}
	}

	@Override
	public Integer getTongTienByKhachHangID(Integer GioHangId) {
		List<GioHangChiTiet> ghct = gioHangChiTietRepo.findAllByGioHangId(GioHangId);
		if(!ghct.isEmpty()) {
				if(gioHangChiTietRepo.tongTien(GioHangId) != null ) {
					return gioHangChiTietRepo.tongTien(GioHangId);
				}
		}else {
			return 0 ;
		}
		return null ;
	}

	@Override
	public GioHangChiTietDTO findById(Integer id) {
		GioHangChiTietDTO gioHangChiTietDTO = null ;
		GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(id).get();
		if(gioHangChiTiet != null) {
			gioHangChiTietDTO = new GioHangChiTietDTO();
			gioHangChiTietDTO = gioHangChiTietConvertor.toDTO(gioHangChiTiet);
			if(gioHangChiTiet.getChiTietSanPham() != null) {
				gioHangChiTietDTO.setSanPhamChiTietDTO(sanPhamChiTietConvertor.toDTO(gioHangChiTiet.getChiTietSanPham()));
				gioHangChiTietDTO.getSanPhamChiTietDTO().setSanPhamDTO(sanPhamConvertor.toDTO(gioHangChiTiet.getChiTietSanPham().getSanPham()));
			}
			return gioHangChiTietDTO;
		}
		return gioHangChiTietDTO;
	}

}
