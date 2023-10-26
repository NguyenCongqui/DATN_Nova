package com.example.duantotnghiepgiaythethaonova.service;


import com.example.duantotnghiepgiaythethaonova.entity.KieuDang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface KieuDangService {

	List<KieuDang> selectAllKieuDangExist();

	Optional<KieuDang> findById(Integer id);

	<S extends KieuDang> S save(S entity);

	Page<KieuDang> selectAllKieuDangExist(Pageable page);

	void delete(KieuDang entity);

	Page<KieuDang> getKieuDangExistByName(String tenKieuDang, Pageable page);


}
