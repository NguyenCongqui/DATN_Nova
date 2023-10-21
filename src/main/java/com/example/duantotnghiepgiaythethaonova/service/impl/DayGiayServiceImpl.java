package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import com.example.duantotnghiepgiaythethaonova.repository.DayGiayRepository;
import com.example.duantotnghiepgiaythethaonova.service.DayGiayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DayGiayServiceImpl implements DayGiayService {

    private DayGiayRepository kichCoRepository;
    private static Logger logger = LoggerFactory.getLogger(DayGiayServiceImpl.class);

    @Override
    public List<DayGiay> selectAllKichCoExist() {
        return kichCoRepository.selectAllKichCoExist();
    }

    @Override
    public <S extends DayGiay> S save(S entity) {
        entity.setDaXoa(false);
        return kichCoRepository.save(entity);
    }

    @Override
    public Optional<DayGiay> findById(Integer id) {
        return kichCoRepository.findById(id);
    }

    @Override
    public List<DayGiay> selectAllKichCoBySanPhamId(Integer sanPhamId) {
        return kichCoRepository.selectAllKichCoBySanPhamId(sanPhamId);
    }
    @Override
    public Page<DayGiay> selectAllKichCoExist(Pageable pageable) {
        return kichCoRepository.selectAllKichCoExist(pageable);
    }
    @Override
    public Page<DayGiay> getKichCoExistByName(String tenKichCo, Pageable pageable) {
        return kichCoRepository.getKichCoExistByName(tenKichCo, pageable);
    }
    @Override
    public void delete(DayGiay entity) {
        entity.setDaXoa(true);
        kichCoRepository.save(entity);
    }
}
