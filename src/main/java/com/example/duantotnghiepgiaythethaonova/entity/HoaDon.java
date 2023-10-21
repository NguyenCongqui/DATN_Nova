package com.example.duantotnghiepgiaythethaonova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HoaDon")
@Builder
public class HoaDon extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHoaDon")
    private Integer idHoaDon;
    @Column(name = "MaHoaDon")
    private String maHoaDon;
    @Column(name = "DiaChiGiaoHang")
    private String diaChiGiaoHang;
    @Column(name = "EmailNguoiNhan")
    private String emailNguoiNhan;
    @Column(name = "GhiChu")
    private String ghiChu;
    @Column(name = "LoaiHoaDon")
    private Integer loaiHoaDon;
    @Column(name = "NguoiNhan")
    private String nguoiNhan;
    @Column(name = "SoDienThoaiNguoiNhan")
    private String soDienThoaiNguoiNhan;
    @Column(name = "ThoiGianGiaoHang")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianGiaoHang;
    @Column(name = "TienShip")
    private BigDecimal tienShip;
    @Column(name = "TienGiam")
    private BigDecimal tienGiam;
    @Column(name = "TongTienDonHang")
    private BigDecimal tongTienDonHang;
    @Column(name = "TongTienHoaDon")
    private BigDecimal tongTienHoaDon;
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
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;

    @OneToOne
    @JoinColumn(name = "IdKhuyenMai")
    private KhuyenMai khuyenMai;

    @ManyToOne
    @JoinColumn(name = "IdNguoiDung")
    private NguoiDung nguoiDung;

    @OneToOne
    @JoinColumn(name = "IdTrangThai")
    private TrangThai trangThai;

    @JsonIgnore
    @OneToMany(mappedBy = "hoaDon")
    private List<HoaDonChiTiet> hoaDonChiTiets;

//    public String LoaiHoaDon2() {
//        String LoaiHoaDon2;
//        if (LoaiHoaDon == 0) {
//            LoaiHoaDon2 = "Đơn đặt hàng";
//        } else {
//            LoaiHoaDon2 = "Đơn tại quầy";
//        }
//        return LoaiHoaDon2;
//    }


}
