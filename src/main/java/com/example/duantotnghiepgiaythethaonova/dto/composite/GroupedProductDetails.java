package com.example.duantotnghiepgiaythethaonova.dto.composite;

import com.example.duantotnghiepgiaythethaonova.entity.HoaDonChiTiet;

import java.util.List;

public class GroupedProductDetails {
    private Integer productId;
    private List<HoaDonChiTiet> productDetails;

    public GroupedProductDetails(Integer productId, List<HoaDonChiTiet> productDetails) {
        this.productId = productId;
        this.productDetails = productDetails;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public List<HoaDonChiTiet> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<HoaDonChiTiet> productDetails) {
        this.productDetails = productDetails;
    }
}
