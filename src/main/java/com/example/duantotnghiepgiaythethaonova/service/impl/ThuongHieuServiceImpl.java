package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.entity.ThuongHieu;
import com.example.duantotnghiepgiaythethaonova.repository.ChatLieuRepository;
import com.example.duantotnghiepgiaythethaonova.repository.ThuongHieuRepository;
import com.example.duantotnghiepgiaythethaonova.service.ThuongHieuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ThuongHieuServiceImpl implements ThuongHieuService {

    private ThuongHieuRepository thuongHieuRepository;
    private static Logger logger = LoggerFactory.getLogger(ThuongHieuServiceImpl.class);


    @Override
    public Page<ThuongHieu> selectAllThuongHieuExist(Pageable pageable) {
        logger.info("Select all thuong hieu exist");
        return thuongHieuRepository.selectAllThuongHieuExist(pageable);
    }

    @Override
    public Optional<ThuongHieu> findById(Integer id) {
        return thuongHieuRepository.findById(id);
    }

    @Override
    public <S extends ThuongHieu> S save(S entity) {
        entity.setDaXoa(false);
        return thuongHieuRepository.save(entity);
    }

    @Override
    public List<ThuongHieu> selectAllThuongHieuExist() {
        logger.info("Select all thuong hieu exist");
        return thuongHieuRepository.selectAllThuongHieuExist();
    }

    @Override
    public Page<ThuongHieu> getThuongHieuExistByName(String tenThuongHieu, Pageable pageable) {
        return thuongHieuRepository.getThuongHieuExistByName(tenThuongHieu, pageable);
    }

    @Override
    public void delete(ThuongHieu entity) {
        entity.setDaXoa(true);
        thuongHieuRepository.save(entity);
    }
}
