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
@Table(name = "KieuDang")
@Builder
@EntityListeners(AuditingEntityListener.class)

public class KieuDang extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKieuDang")
    private Integer idKieuDang;
    @Column(name = "TenKieuDang")
    private String tenKieuDang;
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

    @OneToMany(mappedBy = "kieuDang", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPham> sanPhams;
}
