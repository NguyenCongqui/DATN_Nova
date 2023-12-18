package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.DiaChiDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KhachHangDTO;
import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiaChiService {

    DiaChiDTO save(DiaChiDTO diaChiDTO);

    void delete(Integer[] ids);

    int countByMaKhachHang(Integer id);

    List<DiaChiDTO> findAllDiaChiByMaKhachHang(Integer id , Pageable pageable);

    DiaChiDTO findById(Integer id);

    void update(DiaChiDTO diaChiDTO);

    void save(KhachHangDTO khachHangDTO);

//     DiaChi addAddress(DiaChiDTO diaChiDTO);
     List<DiaChi> getAddressByCustomer(Integer Id);



}
