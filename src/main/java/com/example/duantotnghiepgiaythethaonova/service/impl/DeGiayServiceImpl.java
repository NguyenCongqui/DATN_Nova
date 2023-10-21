package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.entity.DeGiay;
import com.example.duantotnghiepgiaythethaonova.repository.DeGiayRepository;
import com.example.duantotnghiepgiaythethaonova.service.DeGiayService;
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
public class DeGiayServiceImpl implements DeGiayService {
    private DeGiayRepository kichCoRepository;
    private static Logger logger = LoggerFactory.getLogger(DeGiayServiceImpl.class);

    @Override
    public List<DeGiay> selectAllKichCoExist() {
        return kichCoRepository.selectAllKichCoExist();
    }

    @Override
    public <S extends DeGiay> S save(S entity) {
        entity.setDaXoa(false);
        return kichCoRepository.save(entity);
    }

    @Override
    public Optional<DeGiay> findById(Integer id) {
        return kichCoRepository.findById(id);
    }

    @Override
    public List<DeGiay> selectAllKichCoBySanPhamId(Integer sanPhamId) {
        return kichCoRepository.selectAllKichCoBySanPhamId(sanPhamId);
    }

    @Override
    public Page<DeGiay> selectAllKichCoExist(Pageable pageable) {
        return kichCoRepository.selectAllKichCoExist(pageable);
    }

    @Override
    public Page<DeGiay> getKichCoExistByName(String tenKichCo, Pageable pageable) {
        return kichCoRepository.getKichCoExistByName(tenKichCo, pageable);
    }

    @Override
    public void delete(DeGiay entity) {
        entity.setDaXoa(true);
        kichCoRepository.save(entity);
    }
}