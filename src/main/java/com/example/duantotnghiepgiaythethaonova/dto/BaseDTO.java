package com.example.duantotnghiepgiaythethaonova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseDTO<T extends BaseDTO> {
    private Integer id;

    private Date NgayTao;

    private String NguoiTao;

    private Date NgayCapNhat;

    private String NguoiCapNhat;
}
