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
@Table(name = "LichSuHoaDon")
@Builder
public class LichSuHoaDon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLichSuHoaDon")
    private Integer IdLichSuHoaDon;
    @Column(name = "NguoiThaoTac")
    private String NguoiThaoTac;
    @Column(name = "ThaoTac")
    private String ThaoTac;
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

    @ManyToOne
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;


}
