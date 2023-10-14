package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface KichCoService {
    List<KichCo> selectAllKichCoExist();

    Optional<KichCo> findById(int id);

    <S extends KichCo> S save(S entity);

    List<KichCo> selectAllKichCoBySanPhamId(int IdSanPham);

    Page<KichCo> selectAllKichCoExist(Pageable pageable);

    Page<KichCo> getKichCoExistByName(String tenKichCo, Pageable pageable);

    void delete(KichCo entity);
}
