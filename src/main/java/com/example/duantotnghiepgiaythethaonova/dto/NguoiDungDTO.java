package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDungDTO extends BaseDTO<NguoiDungDTO>{

    private Integer idNguoiDung;

    private String tenDangNhap;

    private String maNhanVien;

    private String matKhau;

    private String email;

    private String tenNguoiDung;

    private String ho;

    private String ten;

    private String soDienThoai;

    private String duLieuTimKiem;

    private Integer trangThai;

    private Boolean daXoa;

    private String anhNhanVien;

    private List<NguoiDung_VaiTroDTO> listNguoiDungVaiTroDTO = new ArrayList<NguoiDung_VaiTroDTO>() ;

    private List<NguoiDungDTO> listNguoiDungDTO = new ArrayList<NguoiDungDTO>() ;

}
