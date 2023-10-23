package com.example.duantotnghiepgiaythethaonova.service;

import com.example.duantotnghiepgiaythethaonova.dto.KhachHangDTO;
import com.example.duantotnghiepgiaythethaonova.dto.TaiKhoanDTO;
import com.example.duantotnghiepgiaythethaonova.entity.AuthenticationProvider;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KhachHangService {


    void capNhatTrangThaiThanhDangHoatDongTheoMa(Integer[] ids);

    void capNhatTrangThaiThanhKhongHoatDongTheoMa(Integer[] ids);

    KhachHangDTO findById(Integer id);

    KhachHangDTO save(KhachHangDTO khachHangDTO);

    List<KhachHangDTO> findAll(Pageable pageable);

    List<KhachHangDTO> findAll();

    List<KhachHangDTO> findAllByInputCoPhanTrang(String soDienThoai, Pageable pageable);

    List<KhachHangDTO> findAllByInputVaTrangThaiCoPhanTrang(String soDienThoai, Integer trangThai, Pageable pageable);

    int countAll();

    int countByTrangThai(Integer trangThai);

    int countByInput(String input);

    int countByInputVaTrangThai(String input, Integer trangThai);


    void capNhatTrangThaiTheoId(Integer id);


    void updateUserStatus(Integer id, int trangThai);


    KhachHangDTO findByEmail(String auth);


    KhachHangDTO findByEmailAndTrangThai(String email, int i);


    KhachHangDTO register(KhachHangDTO khachHangDTO);


    String sendCode(String email);


    void updatePassword(String email, KhachHangDTO khachHangDTO);


    void capNhatMatKhau(TaiKhoanDTO taiKhoanDTO);

    public void taoMoiKhachHang(String email, String fullname, AuthenticationProvider provider);

    public void capNhatKhachHang(String email, String fullname, AuthenticationProvider provider);
}
