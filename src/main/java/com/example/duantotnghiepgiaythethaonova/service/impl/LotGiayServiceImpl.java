package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import com.example.duantotnghiepgiaythethaonova.repository.LotGiayRepository;
import com.example.duantotnghiepgiaythethaonova.service.LotGiayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class LotGiayServiceImpl implements LotGiayService {

    private LotGiayRepository kichCoRepository;
    private static Logger logger = LoggerFactory.getLogger(LotGiayServiceImpl.class);

    @Override
    public List<LotGiay> selectAllKichCoExist() {
        return kichCoRepository.selectAllKichCoExist();
    }

    @Override
    public <S extends LotGiay> S save(S entity) {
        entity.setDaXoa(false);
        return kichCoRepository.save(entity);
    }

    @Override
    public Optional<LotGiay> findById(Integer id) {
        return kichCoRepository.findById(id);
    }

    @Override
    public List<LotGiay> selectAllKichCoBySanPhamId(Integer sanPhamId) {
        return kichCoRepository.selectAllKichCoBySanPhamId(sanPhamId);
    }
    @Override
    public Page<LotGiay> selectAllKichCoExist(Pageable pageable) {
        return kichCoRepository.selectAllKichCoExist(pageable);
    }
    @Override
    public Page<LotGiay> getKichCoExistByName(String tenKichCo, Pageable pageable) {
        return kichCoRepository.getKichCoExistByName(tenKichCo, pageable);
    }
    @Override
    public void delete(LotGiay entity) {
        entity.setDaXoa(true);
        kichCoRepository.save(entity);
    }
}
