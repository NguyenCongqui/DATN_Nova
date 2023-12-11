package com.example.duantotnghiepgiaythethaonova.dto;

import com.example.duantotnghiepgiaythethaonova.entity.HoaDon;
import com.example.duantotnghiepgiaythethaonova.entity.LichSuHoaDon;
import com.example.duantotnghiepgiaythethaonova.entity.TrangThai;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TimeLineDTO {
    private int id;

    private String thaoTac;

    private Date ngayTao;

    private String tenTrangThai;

    private int trangThaiID;

    private List<String> hinhAnh;

    private int id_HoaDon;

    private String nguoiTao;

    private String tenNguoiTao;

    private HoaDon hoaDon;

    private TrangThai trangThai;

    private List<LichSuHoaDon> lichSuSuaDoi;

    private List<LichSuHoaDon> timeLineChoXacNhan;

    private List<LichSuHoaDon> timeLineChoGiaoHang;



}
