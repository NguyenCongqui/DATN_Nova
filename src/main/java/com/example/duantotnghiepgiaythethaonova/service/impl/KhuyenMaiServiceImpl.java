package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.convertor.KhuyenMaiConvertor;
import com.example.duantotnghiepgiaythethaonova.dto.KhuyenMaiDTO;
import com.example.duantotnghiepgiaythethaonova.entity.KhuyenMai;
import com.example.duantotnghiepgiaythethaonova.repository.KhuyenMaiRepository;
import com.example.duantotnghiepgiaythethaonova.service.KhuyenMaiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j

public class KhuyenMaiServiceImpl implements KhuyenMaiService {
    @Autowired
    private KhuyenMaiConvertor khuyenMaiConvertor;

    private final KhuyenMaiRepository khuyenMaiRepository;


    @Override
    public Page<KhuyenMaiDTO> getListKhuyenMai(int page,
                                               int size,
                                               String keyword,
                                               String status,
                                               Integer start,
                                               Integer end,
                                               String dateFromStr,
                                               String dateToStr) {
        Sort sort = Sort.by("id").descending();
        PageRequest pageRequest = PageRequest.of(page-1, size, sort);
        Date startDate = convertStringToDate(dateFromStr);
        Date endDate = convertStringToDate(dateToStr);
        Page<KhuyenMai> entities = khuyenMaiRepository
                .findVoucher(keyword, status, start, end, startDate, endDate,pageRequest);
        return entities.map(this::toDto);
    }

    private Date convertStringToDate(String input){
        if (input == null || input.isEmpty()){
            return null;
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try{
                return format.parse(input);
            } catch (ParseException e){
                return null;
            }
        }
    }


    private KhuyenMaiDTO toDto(KhuyenMai e) {
        if (e == null) return null;
        return KhuyenMaiDTO.builder()
                .id(e.getIdKhuyenMai())
                .ngayTao(e.getNgayTao())
                .nguoiTao(e.getNguoiTao())
                .nguoiCapNhat(e.getNguoiCapNhat())
                .ngayCapNhat(e.getNgayCapNhat())
                .tenKhuyenMai(e.getTenKhuyenMai())
                .giaTriToiThieu(e.getGiaTriToiThieu())
                .phanTramGiam(e.getPhanTramGiam())
                .ngayBatDau(e.getNgayBatDau())
                .ngayKetThuc(e.getNgayKetThuc())
                .trangThai(e.getTrangThai())
                .build();
    }

    private KhuyenMai toEntity(KhuyenMaiDTO e) {
        if (e == null) return null;
        return KhuyenMai.builder()
                .tenKhuyenMai(e.getTenKhuyenMai())
                .giaTriToiThieu(e.getGiaTriToiThieu())
                .phanTramGiam(e.getPhanTramGiam())
                .ngayBatDau(e.getNgayBatDau())
                .ngayKetThuc(e.getNgayKetThuc())
                .xoa(false)
                .trangThai(true)
                .build();
    }

    private void mapDto(KhuyenMai entity, KhuyenMaiDTO dto){
        if (dto == null) return;
        entity.setTenKhuyenMai(dto.getTenKhuyenMai());
        entity.setGiaTriToiThieu(dto.getGiaTriToiThieu());
        entity.setPhanTramGiam(dto.getPhanTramGiam());
        entity.setNgayBatDau(dto.getNgayBatDau());
        entity.setNgayKetThuc(dto.getNgayKetThuc());
        entity.setTrangThai(dto.isTrangThai());
    }

    @Override
    public KhuyenMaiDTO createVoucher(KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai entity = toEntity(khuyenMaiDTO);
        return toDto(khuyenMaiRepository.save(entity));
    }

    @Override
    public KhuyenMaiDTO editVoucher(Integer id, KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai khuyenMai = khuyenMaiRepository.findById(id).orElseThrow(() -> new RuntimeException("NOTFOUND"));
        mapDto(khuyenMai, khuyenMaiDTO);
        khuyenMaiRepository.save(khuyenMai);
        return toDto(khuyenMai);
    }


    @Override
    public KhuyenMaiDTO getVoucher(Long id) {
        return null;
    }

    @Override
    public boolean deleteVoucher(Long id) {
        return false;
    }

    @Override
    public boolean toggleDisableVoucher(Long id) {
        return false;
    }

    @Override
    public KhuyenMaiDTO timKhuyenMaiTheoTenKhuyenMai(String maGiamGia) {
        return null;
    }

    @Override
    public boolean checkExistVoucher(KhuyenMaiDTO khuyenMaiDTO) {
        return false;
    }
}
