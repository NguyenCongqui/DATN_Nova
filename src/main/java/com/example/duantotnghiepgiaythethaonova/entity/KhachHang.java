package com.example.duantotnghiepgiaythethaonova.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KhachHang")
@Builder
public class KhachHang extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKhachHang")
    private Integer IdKhachHang;
    @Column(name = "HoTen")
    private String HoTen;
    @Enumerated(EnumType.STRING)
    @Column(name = "AuthProvider")
    private AuthenticationProvider authProvider;
    @Column(name = "SoLanMua")
    private Integer SoLanMua;
    @Column(name = "Email")
    private String Email;
    @Column(name = "MatKhau")
    private String MatKhau;
    @Column(name = "SoDienThoai")
    private String SoDienThoai;
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
    private Integer TrangThai;

    @OneToMany(mappedBy = "khachHang")
    private List<DiaChi> listDiaChi = new ArrayList<DiaChi>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDiaChi")
    private DiaChi diaChi;
}
