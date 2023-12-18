package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.convertor.DiaChiConvertor;
import com.example.duantotnghiepgiaythethaonova.dto.DiaChiDTO;
import com.example.duantotnghiepgiaythethaonova.dto.KhachHangDTO;
import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
import com.example.duantotnghiepgiaythethaonova.entity.KhachHang;
import com.example.duantotnghiepgiaythethaonova.repository.DiaChiRepository;
import com.example.duantotnghiepgiaythethaonova.repository.KhachHangRepository;
import com.example.duantotnghiepgiaythethaonova.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    private DiaChiRepository diaChiRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private DiaChiConvertor diaChoConvertor;

    @Override
    @Transactional
    public DiaChiDTO save(DiaChiDTO result) {
        DiaChiDTO diaChiDTO = new DiaChiDTO();
        DiaChi diaChiEntity = new DiaChi();
        try {
            if(result.getKhachHangId() != null) {
                if(result.getDiaChi() != null) {
                    diaChiEntity.setDiaChi(result.getDiaChi());
                }else {
                    diaChiEntity.setDiaChi(result.getCity()+"-"+result.getDistrict()+"-"+result.getWard()+"-"+result.getSoNha());
                }

                diaChiEntity.setKhachHang(khachHangRepository.findById(result.getKhachHangId()).get());
                diaChiEntity.setHoTen(result.getHoTen());
                diaChiEntity.setSoDienThoai(result.getSoDienThoai());
                diaChiEntity = diaChiRepository.save(diaChiEntity);
                if(diaChiEntity.getIdDiaChi() != null) {
                    diaChiDTO.setId(diaChiEntity.getIdDiaChi());
                    diaChiDTO.setDiaChi(diaChiEntity.getDiaChi());
                    diaChiDTO.setCity(result.getCity());
                    diaChiDTO.setDistrict(result.getDistrict());
                    diaChiDTO.setWard(result.getWard());
                    diaChiDTO.setSoNha(result.getSoNha());
                    diaChiDTO.setHoTen(result.getHoTen());
                    diaChiDTO.setSoDienThoai(result.getSoDienThoai());
                    diaChiDTO.setKhachHangId(result.getKhachHangId());
                    diaChiDTO.setLaDiaChiMacDinh(false);
                    return diaChiDTO;
                }
            }
        } catch (UnexpectedRollbackException e) {
            return null;
        }
        return null;
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            diaChiRepository.deleteById(id);
        }
    }

    @Override
    public int countByMaKhachHang(Integer id) {
        return diaChiRepository.countByMaKhachHang(id);
    }

    @Override
    public List<DiaChiDTO> findAllDiaChiByMaKhachHang(Integer id, Pageable pageale) {
        List<DiaChiDTO> listDiaChiDTO = new ArrayList<DiaChiDTO>();
        List<DiaChi> listDiaChiEntity = new ArrayList<>();
        DiaChiDTO diaChiDTO = null;
        listDiaChiEntity = diaChiRepository.findAllByMaKhachHang(id, pageale).getContent();
        for (DiaChi diaChi : listDiaChiEntity) {
            diaChiDTO = new DiaChiDTO();
            diaChiDTO = diaChoConvertor.toDTO(diaChi);
            listDiaChiDTO.add(diaChiDTO);
        }
        return listDiaChiDTO;
    }


    @SuppressWarnings("deprecation")
    @Override
    public DiaChiDTO findById(Integer id) {
        DiaChiDTO dto = null;
        DiaChi entity = diaChiRepository.getOne(id);
        if (entity.getIdDiaChi() != null) {
            dto = new DiaChiDTO();
            dto.setId(entity.getIdDiaChi());
            dto.setDiaChi(entity.getDiaChi());
            dto.setHoTen(entity.getHoTen());
            dto.setSoDienThoai(entity.getSoDienThoai());
        }
        return dto;
    }

    @SuppressWarnings("deprecation")
    @Override
    @Transactional
    public void update(DiaChiDTO diaChiDTO) {
        if (diaChiDTO.getId() != null) {

            DiaChi entity = new DiaChi();

            entity.setIdDiaChi(diaChiDTO.getId());
            if (diaChiDTO.getDiaChi() == null) {
              //  entity.setDiaChi(diaChiDTO.getCity() + "-" + diaChiDTO.getDistrict() + "-" + diaChiDTO.getWard() + "-" + diaChiDTO.getSoNha());
            } else {
                entity.setDiaChi(diaChiDTO.getDiaChi());
            }
            entity.setSoDienThoai(diaChiDTO.getSoDienThoai());
            entity.setHoTen(diaChiDTO.getHoTen());
            entity.setKhachHang(khachHangRepository.getOne(diaChiDTO.getKhachHangId()));
            diaChiRepository.save(entity);
        }
    }

    @Override
    public void save(KhachHangDTO khachHangDTO) {
        DiaChi diaChi = new DiaChi();
        diaChi.setDiaChi(khachHangDTO.getCity() + "-" + khachHangDTO.getDistrict() + "-"
                + khachHangDTO.getWard() + "-" + khachHangDTO.getSoNha());
        diaChi.setHoTen(khachHangDTO.getHoTen());
        diaChi.setSoDienThoai(khachHangDTO.getSoDienThoai());
        diaChi.setKhachHang(khachHangRepository.findById(khachHangDTO.getId()).get());
        diaChiRepository.save(diaChi);
    }

//    public DiaChi addAddress(DiaChiDTO diaChiDTO){
//        DiaChi diaChi = new DiaChi();
//        diaChi.setDiaChi(diaChiDTO.getDiaChi());
//        diaChi.setHoTen(diaChiDTO.getHoTen());
//        diaChi.setSoDienThoai(diaChiDTO.getSoDienThoai());
//        diaChi.setTenThanhPho(diaChiDTO.getTenThanhPho());
//        diaChi.setTenQuanHuyen(diaChiDTO.getTenQuanHuyen());
//        diaChi.setTenXaPhuong(diaChiDTO.getTeXaPhuong());
//        diaChi.setIdThanhPho(diaChiDTO.getIdThanhPho());
//        diaChi.setIdQuanHuyen(diaChiDTO.getIdHuyen());
//        diaChi.setIdXaPhuong(diaChiDTO.getXa());
////        address.setKhachHang(KhachHang.builder().idKhachHang(diaChiDTO.getKhachHangId()).build());
////        address.setNgayTao(new Date());
//        diaChi.setLaDiaChiMacDinh(false);
//      //  diaChi.setKhachHang(KhachHang.builder().idKhachHang(diaChiDTO.getKhachHangId()).build());
//        return  diaChiRepository.save(diaChi);
//    }

    public List<DiaChi> getAddressByCustomer(Integer Id){
        return diaChiRepository.getAddressByCustomer(Id);
    }
}