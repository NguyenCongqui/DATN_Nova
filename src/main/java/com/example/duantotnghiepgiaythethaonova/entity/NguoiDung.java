package com.example.duantotnghiepgiaythethaonova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "NguoiDung")
@Builder
public class NguoiDung implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdNguoiDung")
    private Integer IdNguoiDung;
    @Column(name = "MaNguoiDung")
    private String MaNguoiDung;
    @Column(name = "TenNguoiDung")
    private String TenNguoiDung;
    @Column(name = "AnhNhanVien")
    private String AnhNhanVien;
    @Enumerated(EnumType.STRING)
    @Column(name = "AuthProvider")
    private AuthenticationProvider authProvider;
    @Column(name = "DiaChi")
    private String DiaChi;
    @Column(name = "Email")
    private String Email;
    @Column(name = "MatKhau")
    private String MatKhau;
    @Column(name = "SoDienThoai")
    private String SoDienThoai;
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
    @Column(name = "TrangThai")
    private Integer TrangThai;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.EAGER)
    private List<NguoiDung_VaiTro> listNguoiDungVaiTro;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<GiaoDich> giaoDichs = new ArrayList<GiaoDich>();

}
