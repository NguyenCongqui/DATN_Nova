package com.example.duantotnghiepgiaythethaonova.service.impl;


import com.example.duantotnghiepgiaythethaonova.entity.TrangThai;
import com.example.duantotnghiepgiaythethaonova.repository.TrangThaiRepository;
import com.example.duantotnghiepgiaythethaonova.service.TrangThaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrangThaiServiceImpl implements TrangThaiService {
    private final TrangThaiRepository trangThaiRepository;

    @Autowired
    public TrangThaiServiceImpl(TrangThaiRepository trangThaiRepository) {
        this.trangThaiRepository = trangThaiRepository;
    }

    @Override
    public TrangThai getTrangThaiById(Integer id) {
        return trangThaiRepository.findById(id).orElse(null);
    }
}