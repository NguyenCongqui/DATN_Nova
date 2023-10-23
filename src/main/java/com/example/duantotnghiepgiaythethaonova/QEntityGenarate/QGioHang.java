package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.GioHang;
import com.example.duantotnghiepgiaythethaonova.entity.GioHangChiTiet;
//import com.example.duantotnghiepgiaythethaonova.entity.QGioHangChiTiet;
//import com.example.duantotnghiepgiaythethaonova.entity.QKhachHang;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGioHang is a Querydsl query type for GioHang
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGioHang extends EntityPathBase<GioHang> {

    private static final long serialVersionUID = -1834028948L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGioHang gioHang = new QGioHang("gioHang");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<GioHangChiTiet, QGioHangChiTiet> gioHangChiTiets = this.<GioHangChiTiet, QGioHangChiTiet>createList("gioHangChiTiets", GioHangChiTiet.class, QGioHangChiTiet.class, PathInits.DIRECT2);

    public final NumberPath<Integer> idGioHang = createNumber("idGioHang", Integer.class);

    public final QKhachHang khachHang;

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final NumberPath<Integer> tongTien = createNumber("tongTien", Integer.class);

    public final NumberPath<Integer> trangThai = createNumber("trangThai", Integer.class);

    public QGioHang(String variable) {
        this(GioHang.class, forVariable(variable), INITS);
    }

    public QGioHang(Path<? extends GioHang> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGioHang(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGioHang(PathMetadata metadata, PathInits inits) {
        this(GioHang.class, metadata, inits);
    }

    public QGioHang(Class<? extends GioHang> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.khachHang = inits.isInitialized("khachHang") ? new QKhachHang(forProperty("khachHang"), inits.get("khachHang")) : null;
    }

}

