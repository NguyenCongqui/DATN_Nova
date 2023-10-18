package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.entity.KieuDang;
import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import com.example.duantotnghiepgiaythethaonova.repository.LotGiayRepository;
import com.example.duantotnghiepgiaythethaonova.service.LotGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotGiayServiceImpl implements LotGiayService {

    @Autowired
    LotGiayRepository lotGiayRepository;


    @Override
    public List<LotGiay> selectAllKieuDangExist() {
        return lotGiayRepository.selectAllChatLieuExist();
    }

    @Override
    public Optional<LotGiay> findById(Integer id) {
        return lotGiayRepository.findById(id);
    }

    @Override
    public <S extends LotGiay> S save(S entity) {
        entity.setDaXoa(false);
        return lotGiayRepository.save(entity);
    }

    @Override
    public Page<LotGiay> selectAllKieuDangExist(Pageable pageable) {
        return lotGiayRepository.selectAllChatLieuExist(pageable);
    }

    @Override
    public void delete(LotGiay entity) {
        entity.setDaXoa(true);
        lotGiayRepository.save(entity);

    }

    @Override
    public Page<LotGiay> getKieuDangExistByName(String tenLotGiay, Pageable pageable) {
        return lotGiayRepository.getChatLieuExistByName(tenLotGiay, pageable);
    }
}
