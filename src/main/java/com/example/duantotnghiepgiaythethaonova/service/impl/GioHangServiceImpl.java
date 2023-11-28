package com.example.duantotnghiepgiaythethaonova.service.impl;

import com.example.duantotnghiepgiaythethaonova.convertor.GioHangChiTietConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.GioHangConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.SanPhamChiTietConvertor;
import com.example.duantotnghiepgiaythethaonova.convertor.SanPhamConvertor;
import com.example.duantotnghiepgiaythethaonova.dto.GioHangChiTietDTO;
import com.example.duantotnghiepgiaythethaonova.dto.GioHangDTO;
import com.example.duantotnghiepgiaythethaonova.dto.SanPhamChiTietDTO;
import com.example.duantotnghiepgiaythethaonova.entity.*;
import com.example.duantotnghiepgiaythethaonova.repository.*;
import com.example.duantotnghiepgiaythethaonova.service.GioHangService;
import com.example.duantotnghiepgiaythethaonova.service.SanPhamChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GioHangServiceImpl implements GioHangService{

    @Autowired
    private GioHangRepository gioHangRepo;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepo;

    @Autowired
    private GioHangConvertor gioHangConvertor;

    @Autowired
    private GioHangChiTietConvertor gioHangChiTietConvertor;

    @Autowired
    private SanPhamChiTietConvertor sanPhamChiTietConvertor;

    @Autowired
    private SanPhamConvertor sanPhamConvertor;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;


    @Override
    public GioHangDTO findByKhachHangId(Integer id) {
        GioHangDTO gioHangDTO = null;
        if (id != null) {
            gioHangDTO = new GioHangDTO();
            List<GioHangChiTietDTO> listGioHangChiTietDTO = new ArrayList<GioHangChiTietDTO>();
            GioHang gioHangEntity = gioHangRepo.findGioHangByKhachHangId(id);
            if (gioHangEntity != null) {
                for (GioHangChiTiet gioHangChiTiet : gioHangEntity.getGioHangChiTiets()) {
                    if (gioHangChiTiet.getDaXoa() == false) {
                        GioHangChiTietDTO gioHangChiTietDTO = gioHangChiTietConvertor.toDTO(gioHangChiTiet);
                        SanPhamChiTietDTO sanPhamChiTietDTO = sanPhamChiTietConvertor.toDTO(gioHangChiTiet.getChiTietSanPham());
                        sanPhamChiTietDTO.setSanPhamDTO(sanPhamConvertor.toDTO(gioHangChiTiet.getChiTietSanPham().getSanPham()));
                        HinhAnh hinhAnh = hinhAnhRepository
                                .findByHinhAnhByMauSacIdVaLaAnhChinh(gioHangChiTiet.getChiTietSanPham().getMauSac().getIdMauSac()
                                        , gioHangChiTiet.getChiTietSanPham().getSanPham().getIdSanPham()
                                );
                        sanPhamChiTietDTO.getSanPhamDTO()
                                .setTenHinhAnh(hinhAnh.getTenAnh());
                        gioHangChiTietDTO.setSanPhamChiTietDTO(sanPhamChiTietDTO);
                        listGioHangChiTietDTO.add(gioHangChiTietDTO);
                    }
                }
                gioHangDTO = gioHangConvertor.toDTO(gioHangEntity);
                gioHangDTO.setListGioHangChiTiets(listGioHangChiTietDTO);

                return gioHangDTO;
            }
        }
        return null;
    }

    @Override
    public void capNhatTongTien(Integer id) {
        GioHang gioHang = gioHangRepo.findGioHangByKhachHangId(id);
        if (gioHang != null) {
            gioHang.setTongTien(gioHangChiTietRepo.tongTien(gioHang.getIdGioHang()));
            gioHangRepo.save(gioHang);
        }
        return;
    }

    @Override
    public int tinhTienGioHangTheoMaGioHangChiTiet(int[] idGioHangChiTiet) {
        int thanhTien = 0;
        for (int i = 0; i < idGioHangChiTiet.length; i++) {
            Integer id = (Integer) Array.get(idGioHangChiTiet, i);
            GioHangChiTiet gioHangChiTiet = gioHangChiTietRepo.findById(id).get();
            thanhTien += Double.parseDouble(gioHangChiTiet.getThanhTien().toString());
        }
        return thanhTien;
    }


    @Override
    public ResponseEntity<String> addToCart(Integer sanPhamId, Integer mauSacId, Integer kichCoId, Integer soLuong) {
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        String message = "";
        if (auth == null) {
            message = "Lỗi";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
        KhachHang khachHang = khachHangRepository.findByEmail(auth);
        GioHang gioHang = gioHangRepository.findGioHangsByKhachHangId(khachHang.getIdKhachHang());

        Optional<ChiTietSanPham> opt = sanPhamChiTietService.getChiTietSanPhamByMauSacSizeSanPhamId(sanPhamId, mauSacId, kichCoId);
        Integer soLuongBanDau = sanPhamChiTietRepository.laySoLuongChiTietSanPham2(kichCoId, mauSacId, sanPhamId);

        if (opt.isPresent() && gioHang != null) {
            ChiTietSanPham sanPhamChiTiet = opt.get();
            BigDecimal donGia = sanPhamChiTiet.getSanPham().getGia();
            BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(soLuong));
            Integer thanhTienInt = thanhTien.intValue();

            Optional<GioHangChiTiet> existingCartItem = gioHangChiTietRepository.findBySanPhamChiTietAndGioHang(sanPhamChiTiet.getIdCTSP(), gioHang.getIdGioHang());
            GioHangChiTiet gioHangChiTiet;
            if (existingCartItem.isPresent()) {
                gioHangChiTiet = existingCartItem.get();
                Integer soLuongCu = gioHangChiTiet.getSoLuong();
                gioHangChiTiet.setSoLuong(soLuongCu + soLuong);
                gioHangChiTiet.setThanhTien(gioHangChiTiet.getThanhTien().add(thanhTien));
            } else {
                gioHangChiTiet = new GioHangChiTiet();
                gioHangChiTiet.setChiTietSanPham(sanPhamChiTiet);
                gioHangChiTiet.setSoLuong(soLuong);
                gioHangChiTiet.setGioHang(gioHang);
                gioHangChiTiet.setDonGia(donGia);
                gioHangChiTiet.setThanhTien(thanhTien);
                gioHangChiTiet.setDaXoa(false);
                gioHangChiTiet.setTrangThai(0);
            }
            gioHangChiTietRepository.save(gioHangChiTiet);

            gioHang.setTongTien(gioHang.getTongTien() + thanhTienInt);
            gioHangRepository.save(gioHang);
            gioHangRepository.save(gioHang);

            Integer soLuongThemVao = soLuong;
//            Integer soLuongCapNhat = soLuongBanDau - soLuongThemVao;
//            sanPhamChiTiet.setSoLuong(soLuongCapNhat);
            sanPhamChiTietRepository.save(sanPhamChiTiet);

            message = "Thêm mới thành công";
            return ResponseEntity.ok(message);
        } else {
            String errorMessage = "Lỗi khi thêm vào giỏ hàng";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @Override
    public void xoaSachGioHang(Integer id) {
        Optional<GioHang> optGioHang = gioHangRepository.findById(id);
        if (optGioHang.isPresent()) {
            GioHang gioHang = optGioHang.get();
            List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietRepository.findbyGiohangIdAndDaXoa(id);

            for (GioHangChiTiet gioHangChiTiet : gioHangChiTiets) {
                gioHangChiTiet.setDaXoa(true);
                gioHangChiTietRepository.save(gioHangChiTiet);

                ChiTietSanPham sanPhamChiTiet = gioHangChiTiet.getChiTietSanPham();
//                Integer soLuongSPCTBanDau = sanPhamChiTiet.getSoLuong();
//                Integer soLuongNhapVao = gioHangChiTiet.getSoLuong();
//                Integer soLuongcapNhat = soLuongSPCTBanDau + soLuongNhapVao;
//                sanPhamChiTiet.setSoLuong(soLuongcapNhat);
                sanPhamChiTietRepository.save(sanPhamChiTiet);
            }
        }
    }
    }


