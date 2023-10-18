package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.KieuDang;
import com.example.duantotnghiepgiaythethaonova.repository.KieuDangRepository;
import com.example.duantotnghiepgiaythethaonova.service.KieuDangService;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class KieuDangServiceImpl implements KieuDangService {


    @Autowired
    private KieuDangRepository kieuDangRepository;

    @Override
    public List<KieuDang> selectAllKieuDangExist() {
        return kieuDangRepository.selectAllKieuDangExist();
    }

    @Override
    public Optional<KieuDang> findById(Integer id) {
        return kieuDangRepository.findById(id);
    }

    @Override
    public <S extends KieuDang> S save(S entity) {
        entity.setDaXoa(false);
        return kieuDangRepository.save(entity);
    }

    @Override
    public Page<KieuDang> selectAllKieuDangExist(Pageable pageable) {
        return kieuDangRepository.selectAllKieuDangExist(pageable);
    }

    @Override
    public void delete(KieuDang entity) {
        entity.setDaXoa(true);
        kieuDangRepository.save(entity);
    }

    @Override
    public Page<KieuDang> getKieuDangExistByName(String tenKieuDang, Pageable pageable) {
        return kieuDangRepository.getKieuDangExistByName(tenKieuDang,pageable);
    }
}
