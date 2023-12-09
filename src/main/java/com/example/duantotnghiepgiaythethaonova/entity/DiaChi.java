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
public class DiaChi implements Serializable {

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

    @Column(name = "IdThanhPho")
    private Integer idThanhPho;

    @Column(name = "idQuanHuyen")
    private Integer idQuanHuyen;

    @Column(name = "idXaPhuong")
    private Integer idXaPhuong;

    @Column(name = "TenThanhPho")
    private String tenThanhPho;

    @Column(name = "TenQuanHuyen")
    private String tenQuanHuyen;

    @Column(name = "TenXaPhuong")
    private String tenXaPhuong;

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
