package com.example.duantotnghiepgiaythethaonova.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KhachHang")
@EqualsAndHashCode(callSuper=false)
public class KhachHang extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKhachHang")
    private Integer idKhachHang;
    @Column(name = "HoTen")
    private String hoTen;
    @Enumerated(EnumType.STRING)
    @Column(name = "AuthProvider")
    private AuthenticationProvider authProvider;
    @Column(name = "SoLanMua")
    private Integer soLanMua;
    @Column(name = "Email")
    private String email;
    @Column(name = "MatKhau")
    private String matKhau;
    @Column(name = "SoDienThoai")
    private String soDienThoai;
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
    private Integer trangThai;

    @OneToMany(mappedBy = "khachHang")
    private List<DiaChi> listDiaChi = new ArrayList<DiaChi>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDiaChi")
    private DiaChi diaChi;

    @Override
    public String toString() {
        return "KhachHang{" +
                "email='" + email + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", soLanMua=" + soLanMua +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", trangThai=" + trangThai +
                ", listDiaChi=" + listDiaChi +
                ", diaChi=" + diaChi.getIdDiaChi() +
                '}';
    }
}
