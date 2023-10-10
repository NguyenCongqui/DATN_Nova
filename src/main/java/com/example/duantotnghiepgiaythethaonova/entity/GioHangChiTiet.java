package com.example.duantotnghiepgiaythethaonova.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GioHangCT")
@Builder
public class GioHangChiTiet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGioHangCT")
    private Integer IdGioHangCT;
    @Column(name = "DonGia")
    private Integer DonGia;
    @Column(name = "SoLuong")
    private Integer SoLuong;
    @Column(name = "TongTien")
    private BigDecimal ThanhTien;
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
    @Column(name = "TrangThai")
    private Integer TrangThai;
    @Column(name = "DaXoa")
    private Boolean DaXoa;

    @ManyToOne
    @JoinColumn(name = "IdGioHang")
    private GioHang gioHang;

    @ManyToOne
    @JoinColumn(name = "IdCTSP")
    private ChiTietSanPham chiTietSanPham;


}
