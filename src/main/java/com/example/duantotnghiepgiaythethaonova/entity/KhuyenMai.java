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
@Table(name = "KhuyenMai")
@Builder
public class KhuyenMai extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKhuyenMai")
    private Integer IdKhuyenMai;
    @Column(name = "TenKhuyenMai")
    private String TenKhuyenMai;
    @Column(name = "GiaTriToiThieu")
    private Integer GiaTriToiThieu;
    @Column(name = "NgayBatDau")
    private Date NgayBatDau;
    @Column(name = "NgayKetThuc")
    private Date NgayKetThuc;
    @Column(name = "PhamTramGiam")
    private Integer PhanTramGiam;
    @Column(name = "TrangThai")
    private Boolean TrangThai;
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
    @Column(name = "Xoa")
    private Boolean Xoa;

}
