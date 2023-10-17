package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import com.example.duantotnghiepgiaythethaonova.repository.MauSacRepository;
import com.example.duantotnghiepgiaythethaonova.service.MauSacService;
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
public class MauSacServiceImpl implements MauSacService {

    private MauSacRepository mauSacRepository;
    private static Logger logger = LoggerFactory.getLogger(MauSacServiceImpl.class);


    @Override
    public List<MauSac> selectAllMauSacExist() {
        return mauSacRepository.selectAllMauSacExist();
    }

    @Override
    public <S extends MauSac> S save(S entity) {
        entity.setDaXoa(false);
        return mauSacRepository.save(entity);
    }

    @Override
    public Optional<MauSac> findById(Integer id) {
        return mauSacRepository.findById(id);
    }

    @Override
    public List<MauSac> getAllMauSacExistBySPId(Integer spId) {
        return mauSacRepository.getAllMauSacExistBySPId(spId);
    }
    @Override
    public Page<MauSac> selectAllMauSacExist(Pageable page) {
        return mauSacRepository.selectAllMauSacExist(page);
    }
    @Override
    public Page<MauSac> getMauSacExistByName(String tenMauSac, Pageable page) {
        return mauSacRepository.getMauSacExistByName(tenMauSac, page);
    }
    @Override
    public void delete(MauSac entity) {
        entity.setDaXoa(true);
        mauSacRepository.save(entity);
    }
}
