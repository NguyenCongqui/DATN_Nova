package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.entity.DeGiay;
import com.example.duantotnghiepgiaythethaonova.repository.DeGiayRepository;
import com.example.duantotnghiepgiaythethaonova.service.DeGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeGiayServiceImpl implements DeGiayService {

    @Autowired
    private DeGiayRepository deGiayRepository;

    @Override
    public List<DeGiay> selectAllDeGiayExist() {
        return deGiayRepository.selectAllChatLieuExist();
    }

    @Override
    public Optional<DeGiay> findById(Integer id) {
        return deGiayRepository.findById(id);
    }

    @Override
    public <S extends DeGiay> S save(S entity) {
        entity.setDaXoa(false);
        return deGiayRepository.save(entity);
    }

    @Override
    public List<DeGiay> selectAllDeGiayBySanPhamId(Integer sanPhamId) {
        return deGiayRepository.selectAllKichCoBySanPhamId(sanPhamId);
    }

    @Override
    public Page<DeGiay> selectAllDeGiayExist(Pageable pageable) {
        return deGiayRepository.selectAllChatLieuExist(pageable);
    }

    @Override
    public Page<DeGiay> getDeGiayExistByName(String tenDeGiay, Pageable pageable) {
        return deGiayRepository.getChatLieuExistByName(tenDeGiay, pageable);
    }

    @Override
    public void delete(DeGiay entity) {
        entity.setDaXoa(true);
        deGiayRepository.save(entity);
    }
}
