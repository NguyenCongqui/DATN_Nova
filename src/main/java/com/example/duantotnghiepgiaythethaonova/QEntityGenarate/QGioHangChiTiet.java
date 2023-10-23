package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.GioHangChiTiet;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGioHangChiTiet is a Querydsl query type for GioHangChiTiet
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGioHangChiTiet extends EntityPathBase<GioHangChiTiet> {

    private static final long serialVersionUID = 709552796L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGioHangChiTiet gioHangChiTiet = new QGioHangChiTiet("gioHangChiTiet");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QChiTietSanPham chiTietSanPham;

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final NumberPath<Integer> donGia = createNumber("donGia", Integer.class);

    public final QGioHang gioHang;

    public final NumberPath<Integer> idGioHangCT = createNumber("idGioHangCT", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final NumberPath<Integer> soLuong = createNumber("soLuong", Integer.class);

    public final NumberPath<java.math.BigDecimal> thanhTien = createNumber("thanhTien", java.math.BigDecimal.class);

    public final NumberPath<Integer> trangThai = createNumber("trangThai", Integer.class);

    public QGioHangChiTiet(String variable) {
        this(GioHangChiTiet.class, forVariable(variable), INITS);
    }

    public QGioHangChiTiet(Path<? extends GioHangChiTiet> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGioHangChiTiet(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGioHangChiTiet(PathMetadata metadata, PathInits inits) {
        this(GioHangChiTiet.class, metadata, inits);
    }

    public QGioHangChiTiet(Class<? extends GioHangChiTiet> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chiTietSanPham = inits.isInitialized("chiTietSanPham") ? new QChiTietSanPham(forProperty("chiTietSanPham"), inits.get("chiTietSanPham")) : null;
        this.gioHang = inits.isInitialized("gioHang") ? new QGioHang(forProperty("gioHang"), inits.get("gioHang")) : null;
    }

}

