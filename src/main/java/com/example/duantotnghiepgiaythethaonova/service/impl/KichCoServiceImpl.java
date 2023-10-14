package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.example.duantotnghiepgiaythethaonova.repository.KichCoRepository;
import com.example.duantotnghiepgiaythethaonova.service.KichCoService;
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
public class KichCoServiceImpl implements KichCoService {
    private KichCoRepository kichCoRepository;
    private static Logger logger = LoggerFactory.getLogger(KichCoServiceImpl.class);

    @Override
    public List<KichCo> selectAllKichCoExist() {
        return kichCoRepository.selectAllKichCoExist();
    }

    @Override
    public Optional<KichCo> findById(int id) {
        return kichCoRepository.findById(id);
    }

    @Override
    public <S extends KichCo> S save(S entity) {
        entity.setDaXoa(false);
        return kichCoRepository.save(entity);    }

    @Override
    public List<KichCo> selectAllKichCoBySanPhamId(int IdSanPham) {
        return kichCoRepository.selectAllKichCoBySanPhamId(IdSanPham);
    }

    @Override
    public Page<KichCo> selectAllKichCoExist(Pageable pageable) {
        return kichCoRepository.selectAllKichCoExist(pageable);
    }

    @Override
    public Page<KichCo> getKichCoExistByName(String tenKichCo, Pageable pageable) {
        return kichCoRepository.getKichCoExistByName(tenKichCo, pageable);
    }

    @Override
    public void delete(KichCo entity) {
        entity.setDaXoa(true);
        kichCoRepository.save(entity);
    }
}
