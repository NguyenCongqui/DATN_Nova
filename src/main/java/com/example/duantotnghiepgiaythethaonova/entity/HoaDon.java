package com.example.duantotnghiepgiaythethaonova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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
@EntityListeners(AuditingEntityListener.class)
public class HoaDon extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -6627502088104297623L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHoaDon")
    private Integer idHoaDon;
    @Column(name = "MaDon")
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

    public String loaiHoaDon2() {
        String loaiHoaDon2;
        if (loaiHoaDon == 0) {
            loaiHoaDon2 = "Đơn đặt hàng";
        } else {
            loaiHoaDon2 = "Đơn tại quầy";
        }
        return loaiHoaDon2;
    }


    @Override
    public String toString() {
        return "HoaDon{nguoiNhan='" + nguoiNhan + '\'' +
                ", sdtNguoiNhan='" + soDienThoaiNguoiNhan + '\'' +
                ", diaChiGiaoHang='" + diaChiGiaoHang + '\'' +
                ", thoiGianGiaoHang='" + thoiGianGiaoHang + '\'' +
                ", ghiChu='" + ghiChu + '\'' +
                ", tongTienHoaDon=" + tongTienHoaDon +
                ", tienShip=" + tienShip +
                ", tongTienDonHang=" + tongTienDonHang +
                ", loaiDonHang='" + loaiHoaDon + '\'' +
                ", hoaDonChiTiets=" + hoaDonChiTiets.size() +
                '}';
    }

}
