package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.entity.KieuDang;
import com.example.duantotnghiepgiaythethaonova.repository.KieuDangRepository;
import com.example.duantotnghiepgiaythethaonova.service.KieuDangService;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class KieuDangServiceImpl implements KieuDangService {
	private KieuDangRepository kieuDangRepository;
	private static Logger logger = LoggerFactory.getLogger(KieuDangServiceImpl.class);
	
	@Override
	public List<KieuDang> selectAllKieuDangExist() {
		logger.info("Select all kieu dang exist");
		return kieuDangRepository.selectAllKieuDangExist();
	}
	@Override
	public <S extends KieuDang> S save(S entity) {
		entity.setDaXoa(false);
		return kieuDangRepository.save(entity);
	}
	
	@Override
	public Optional<KieuDang> findById(Integer id) {
		return kieuDangRepository.findById(id);
	}
	
	@Override
	public Page<KieuDang> selectAllKieuDangExist(Pageable page) {
		return kieuDangRepository.selectAllKieuDangExist(page);
	}
	
	@Override
	public void delete(KieuDang entity) {
		entity.setDaXoa(true);
		kieuDangRepository.save(entity);
	}
	
	@Override
	public Page<KieuDang> getKieuDangExistByName(String tenKieuDang, Pageable page) {
		return kieuDangRepository.getKieuDangExistByName(tenKieuDang, page);
	}
	
	
}
