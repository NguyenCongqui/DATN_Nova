package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.KhuyenMaiDTO;
import org.springframework.data.domain.Page;

public interface KhuyenMaiService {
    Page<KhuyenMaiDTO> getListKhuyenMai(int page,
                                        int size,
                                        String keyword,
                                        String status,
                                        Integer startStr,
                                        Integer endStr,
                                        String dateFrom,
                                        String dateTo);
    KhuyenMaiDTO createVoucher(KhuyenMaiDTO khuyenMaiDTO);
    KhuyenMaiDTO editVoucher(Integer id, KhuyenMaiDTO khuyenMaiDTO);
    KhuyenMaiDTO getVoucher(Integer id);
    boolean deleteVoucher(Integer id);
    boolean toggleDisableVoucher(Integer id);
    KhuyenMaiDTO timKhuyenMaiTheoTenKhuyenMai(String maGiamGia);
    boolean checkExistVoucher(KhuyenMaiDTO khuyenMaiDTO);
}
