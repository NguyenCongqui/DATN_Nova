package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.convertor.DiaChiConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.KhachHangConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.NguoiDungConvertor;
import com.example.duantotnghiepgiaythethaonova.dto.KhachHangDTO;
import com.example.duantotnghiepgiaythethaonova.dto.TaiKhoanDTO;
import com.example.duantotnghiepgiaythethaonova.entity.AuthenticationProvider;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import com.example.duantotnghiepgiaythethaonova.service.KhachHangService;
import com.example.duantotnghiepgiaythethaonova.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private KhachHangConvertor khachHangConvertor;

    @Autowired
    private DiaChiConvertor diaChiConvertor;

    @Autowired
    private NguoiDungConvertor nguoiDungConvertor;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private VaiTroRepository vaiTroRepository;

    @Autowired
    private NguoiDung_VaiTroRepository nguoiDungVaiTroRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void capNhatTrangThaiThanhDangHoatDongTheoMa(Integer[] ids) {

    }

    @Override
    public void capNhatTrangThaiThanhKhongHoatDongTheoMa(Integer[] ids) {

    }

    @Override
    public KhachHangDTO findById(Integer id) {
        return null;
    }

    @Override
    public KhachHangDTO save(KhachHangDTO khachHangDTO) {
        return null;
    }

    @Override
    public List<KhachHangDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<KhachHangDTO> findAll() {
        return null;
    }

    @Override
    public List<KhachHangDTO> findAllByInputCoPhanTrang(String soDienThoai, Pageable pageable) {
        return null;
    }

    @Override
    public List<KhachHangDTO> findAllByInputVaTrangThaiCoPhanTrang(String soDienThoai, Integer trangThai, Pageable pageable) {
        return null;
    }

    @Override
    public int countAll() {
        return 0;
    }

    @Override
    public int countByTrangThai(Integer trangThai) {
        return 0;
    }

    @Override
    public int countByInput(String input) {
        return 0;
    }

    @Override
    public int countByInputVaTrangThai(String input, Integer trangThai) {
        return 0;
    }

    @Override
    public void capNhatTrangThaiTheoId(Integer id) {

    }

    @Override
    public void updateUserStatus(Integer id, int trangThai) {

    }

    @Override
    public KhachHangDTO findByEmail(String auth) {
        return null;
    }

    @Override
    public KhachHangDTO findByEmailAndTrangThai(String email, int i) {
        return null;
    }

    @Override
    public KhachHangDTO register(KhachHangDTO khachHangDTO) {
        return null;
    }

    @Override
    public String sendCode(String email) {
        return null;
    }

    @Override
    public void updatePassword(String email, KhachHangDTO khachHangDTO) {

    }

    @Override
    public void capNhatMatKhau(TaiKhoanDTO taiKhoanDTO) {

    }

    @Override
    public void taoMoiKhachHang(String email, String fullname, AuthenticationProvider provider) {

    }

    @Override
    public void capNhatKhachHang(String email, String fullname, AuthenticationProvider provider) {

    }
}
