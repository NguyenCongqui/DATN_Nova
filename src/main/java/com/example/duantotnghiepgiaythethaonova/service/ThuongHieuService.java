package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.entity.ChatLieu;
import com.example.duantotnghiepgiaythethaonova.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ThuongHieuService {

    Page<ThuongHieu> selectAllThuongHieuExist(Pageable pageable);
    //phan trang

    Optional<ThuongHieu> findById(Integer id);
    //tim theo id

    <S extends  ThuongHieu> S save(S entity);
    //them

    List<ThuongHieu> selectAllThuongHieuExist();
    //goi cac chat lieu ton tai

    Page<ThuongHieu> getThuongHieuExistByName(String tenThuongHieu, Pageable pageable);
    //tim chat lieu theo ten , phan trang

    void delete(ThuongHieu entity);
    //xoa


}
