package com.example.duantotnghiepgiaythethaonova.entity;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DiaChi")
@Builder
@Data
public class DiaChi extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDiaChi")
    private Integer idDiaChi;
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    @Column(name = "LaDiaChiMacDinh")
    private boolean laDiaChiMacDinh = false;
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

    @ManyToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;


    @Override
    public String toString() {
        return "DiaChi{" +
                "diaChi='" + diaChi + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", khachHang=" + khachHang.getIdKhachHang() +
                ", laDiaChiMacDinh=" + laDiaChiMacDinh +
                '}';
    }

}
