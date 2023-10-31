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
@Table(name = "HoaDonCT")
@Builder
public class HoaDonChiTiet extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHoaDonCT")
    private Integer idHoaDonCT;
    @Column(name = "DonGia")
    private BigDecimal donGia;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "TongTien")
    private BigDecimal tongTien;
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
    @Column(name = "DaXoa")
    private Boolean daXoa;

    @ManyToOne
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "IdCTSP")
    private ChiTietSanPham chiTietSanPham;


    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "hoaDon=" + hoaDon.getIdHoaDon() +
                ", sanPhamChiTiet=" + chiTietSanPham.getIdCTSP() +
                ", donGia=" + donGia +
                ", soLuong=" + soLuong +
                ", tongTien=" + tongTien +
                ", daXoa='" + daXoa + '\'' +
                '}';
    }


}
