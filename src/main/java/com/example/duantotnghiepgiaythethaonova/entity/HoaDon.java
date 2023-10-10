package com.example.duantotnghiepgiaythethaonova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HoaDon")
@Builder
public class HoaDon implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHoaDon")
    private Integer IdHoaDon;
    @Column(name = "MaHoaDon")
    private String MaHoaDon;
    @Column(name = "DiaChiGiaoHang")
    private String DiaChiGiaoHang;
    @Column(name = "EmailNguoiNhan")
    private String EmailNguoiNhan;
    @Column(name = "GhiChu")
    private String GhiChu;
    @Column(name = "LoaiHoaDon")
    private Integer LoaiHoaDon;
    @Column(name = "NguoiNhan")
    private String NguoiNhan;
    @Column(name = "SoDienThoaiNguoiNhan")
    private String SoDienThoaiNguoiNhan;
    @Column(name = "ThoiGianGiaoHang")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ThoiGianGiaoHang;
    @Column(name = "TienShip")
    private BigDecimal TienShip;
    @Column(name = "TienGiam")
    private BigDecimal TienGiam;
    @Column(name = "TongTienDonHang")
    private BigDecimal TongTienDonHang;
    @Column(name = "TongTienHoaDon")
    private BigDecimal TongTienHoaDon;
    @Column(name = "NgayTao")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date NgayTao;
    @Column(name = "NgayCapNhat")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date NgayCapNhat;
    @Column(name = "NguoiTao")
    private String NguoiTao;
    @Column(name = "NguoiCapNhat")
    private String NguoiCapNhat;
    @Column(name = "DaXoa")
    private Boolean DaXoa;

    @ManyToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "IdKhuyenMai")
    private KhuyenMai khuyenMai;

    @ManyToOne
    @JoinColumn(name = "IdNguoiDung")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "IdTrangThai")
    private TrangThai trangThai;

    @JsonIgnore
    @OneToMany(mappedBy = "hoaDon")
    private List<HoaDonChiTiet> hoaDonChiTiets;

}
