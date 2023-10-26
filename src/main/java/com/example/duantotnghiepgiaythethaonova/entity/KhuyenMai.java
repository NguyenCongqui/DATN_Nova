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
@Table(name = "KhuyenMai")
@Builder
public class KhuyenMai extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKhuyenMai")
    private Integer idKhuyenMai;
    @Column(name = "TenKhuyenMai")
    private String tenKhuyenMai;
    @Column(name = "GiaTriToiThieu")
    private Integer giaTriToiThieu;
    @Column(name = "NgayBatDau")
    private Date ngayBatDau;
    @Column(name = "NgayKetThuc")
    private Date ngayKetThuc;
    @Column(name = "PhanTramGiam")
    private Integer phanTramGiam;
    @Column(name = "TrangThai")
    private Boolean trangThai;
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
    private Boolean xoa;

}
