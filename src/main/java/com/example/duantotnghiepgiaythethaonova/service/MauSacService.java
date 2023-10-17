package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MauSacService {
    List<MauSac> selectAllMauSacExist();

    Optional<MauSac> findById(Integer id);

    <S extends MauSac> S save(S entity);

    List<MauSac> getAllMauSacExistBySPId(Integer spId);

    Page<MauSac> selectAllMauSacExist(Pageable page);

    Page<MauSac> getMauSacExistByName(String tenMauSac, Pageable page);

    void delete(MauSac entity);
}
