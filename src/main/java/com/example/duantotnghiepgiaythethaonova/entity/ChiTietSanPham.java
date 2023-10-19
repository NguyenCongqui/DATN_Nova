package com.example.duantotnghiepgiaythethaonova.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SanPhamCT")
@Builder
public class ChiTietSanPham extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSanPhamCT")
    private Integer idCTSP;
    @Column(name = "MaSanPhamCT")
    private String maCTSP;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "CoHienThi")
    private Boolean coHienThi;
//    @Column(name = "NgayTao")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date NgayTao;
//    @Column(name = "NgayCapNhat")
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date NgayCapNhat;
//    @Column(name = "NguoiTao")
//    private String NguoiTao;
//    @Column(name = "NguoiCapNhat")
//    private String NguoiCapNhat;
    @Column(name = "DaXoa")
    private Boolean daXoa;

    @ManyToOne
    @JoinColumn(name = "IdKichCo")
    private KichCo kichCo;

    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "IdDayGiay")
    private DayGiay dayGiay;

    @ManyToOne
    @JoinColumn(name = "IdLotGiay")
    private LotGiay lotGiay;

    @ManyToOne
    @JoinColumn(name = "IdDeGiay")
    private DeGiay deGiay;

    @ManyToOne
    @JoinColumn(name = "IdSanPham")
    private SanPham sanPham;

    @OneToMany(mappedBy = "chiTietSanPham" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> hoaDonChiTiets;

    @OneToMany(mappedBy = "chiTietSanPham")
    private List<GioHangChiTiet> gioHangChiTiets = new ArrayList<GioHangChiTiet>();

}
