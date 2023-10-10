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
@Table(name = "DanhMuc")
@Builder
public class DanhMuc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDanhMuc")
    private Integer IdDanhMuc;
    @Column(name = "TenDanhMuc")
    private String TenDanhMuc;
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
}
