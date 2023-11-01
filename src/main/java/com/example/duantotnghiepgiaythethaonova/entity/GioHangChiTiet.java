package com.example.duantotnghiepgiaythethaonova.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GioHangCT")
@Builder
@EqualsAndHashCode(callSuper=false)
public class GioHangChiTiet extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGioHangCT")
    private Integer idGioHangCT;
    @Column(name = "DonGia")
    private Integer donGia;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "TongTien")
    private BigDecimal thanhTien;
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
    @Column(name = "TrangThai")
    private Integer trangThai;
    @Column(name = "DaXoa")
    private Boolean daXoa;

    @ManyToOne
    @JoinColumn(name = "IdGioHang")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "IdSanPhamCT")
    private ChiTietSanPham chiTietSanPham;

    @Override
    public String toString() {
        return "GioHangChiTiet{" +
                "sanPhamChiTiet=" + chiTietSanPham.getIdCTSP() +
                ", gioHang=" + gioHang.getIdGioHang() +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", thanhTien=" + thanhTien +
                ", trangThai=" + trangThai +
                ", daXoa=" + daXoa +
                '}';
    }

}
