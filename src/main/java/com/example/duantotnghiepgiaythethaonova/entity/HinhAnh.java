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
@Table(name = "HinhAnh")
@Builder
public class HinhAnh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHinhAnh")
    private Integer IdHinhAnh;
    @Column(name = "TenAnh")
    private String TenAnh;
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
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "IdSanPham")
    private SanPham sanPham;
}
