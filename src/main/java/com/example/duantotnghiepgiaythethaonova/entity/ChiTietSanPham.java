package com.example.duantotnghiepgiaythethaonova.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SanPhamCT")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class ChiTietSanPham extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSanPhamCT")
    private Integer idCTSP;
    @Column(name = "MaSanPhamCT")
    private String maCTSP;
    @Column(name = "Gia")
    private BigDecimal gia;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "CoHienThi")
    private Boolean coHienThi;

    @Column(name = "DaXoa")
    private Boolean daXoa;

    @ManyToOne
    @JoinColumn(name = "IdKichCo")
    private KichCo kichCo;

    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "IdDayGiay")
    private DayGiay dayGiay;

    @ManyToOne
    @JoinColumn(name = "IdLotGiay")
    private LotGiay lotGiay;

    @ManyToOne
    @JoinColumn(name = "IdDeGiay")
    private DeGiay deGiay;

    @ManyToOne
    @JoinColumn(name = "IdSanPham")
    private SanPham sanPham;

    @OneToMany(mappedBy = "chiTietSanPham" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> hoaDonChiTiets;

    @OneToMany(mappedBy = "chiTietSanPham")
    private List<GioHangChiTiet> gioHangChiTiets = new ArrayList<GioHangChiTiet>();

    @Override
    public String toString() {
        return "SanPhamChiTiet{" +
                "maSanPhamChiTiet='" + maCTSP + '\'' +
                ", sanPham=" + sanPham +
                ", soLuong=" + soLuong +
                ", hoaDonChiTiets=" + hoaDonChiTiets.size() +
                ", daXoa=" + daXoa +
                '}';
    }

}
