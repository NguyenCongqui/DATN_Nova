package com.example.duantotnghiepgiaythethaonova.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DiaChi")
@Builder
public class DiaChi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDiaChi")
    private Integer IdDiaChi;
    @Column(name = "HoTen")
    private String HoTen;
    @Column(name = "DiaChi")
    private String DiaChi;
    @Column(name = "SoDienThoai")
    private String SoDienThoai;
    @Column(name = "LaDiaChiMacDinh")
    private boolean LaDiaChiMacDinh = false;
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

    @ManyToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;




}
