package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import com.example.duantotnghiepgiaythethaonova.repository.DayGiayRepository;
import com.example.duantotnghiepgiaythethaonova.service.DayGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayGiayServiceImpl implements DayGiayService {

    @Autowired
    DayGiayRepository dayGiayRepository;

    @Override
    public List<DayGiay> selectAllKieuDangExist() {
        return dayGiayRepository.selectAllChatLieuExist();
    }

    @Override
    public Optional<DayGiay> findById(Integer id) {
        return dayGiayRepository.findById(id);
    }

    @Override
    public <S extends DayGiay> S save(S entity) {
        entity.setDaXoa(false);
        return dayGiayRepository.save(entity);
    }

    @Override
    public Page<DayGiay> selectAllKieuDangExist(Pageable pageable) {
        return dayGiayRepository.selectAllChatLieuExist(pageable);
    }

    @Override
    public void delete(DayGiay entity) {
        entity.setDaXoa(true);
        dayGiayRepository.save(entity);
    }

    @Override
    public Page<DayGiay> getKieuDangExistByName(String tenDayGiay, Pageable pageable) {
        return dayGiayRepository.getChatLieuExistByName(tenDayGiay, pageable);
    }
}
