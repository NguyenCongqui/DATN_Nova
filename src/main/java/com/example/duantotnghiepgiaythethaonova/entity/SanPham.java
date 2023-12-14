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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SanPham")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class SanPham extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSanPham")
    private Integer idSanPham;
    @Column(name = "TenSanPham")
    private String tenSanPham;
    @Column(name = "MaSanPham")
    private String maSanPham;

//    @Column(name = "Gia")
//    private BigDecimal gia;
    @Column(name = "MoTa")
    private String moTa;
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
    @JoinColumn(name = "IdKieuDang")
    private KieuDang kieuDang;

    @ManyToOne
    @JoinColumn(name = "IdChatLieu")
    private ChatLieu chatLieu;

    @ManyToOne
    @JoinColumn(name = "IdThuongHieu")
    private ThuongHieu thuongHieu;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HinhAnh> hinhAnhs;

    @OneToMany(mappedBy = "sanPham", orphanRemoval = true)
    private List<ChiTietSanPham> chiTietSanPhams = new ArrayList<ChiTietSanPham>();

}
