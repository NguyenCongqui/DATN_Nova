package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.GiaoDich;
//import com.example.duantotnghiepgiaythethaonova.entity.QHoaDon;
//import com.example.duantotnghiepgiaythethaonova.entity.QNguoiDung;
//import com.example.duantotnghiepgiaythethaonova.entity.QTrangThai;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGiaoDich is a Querydsl query type for GiaoDich
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGiaoDich extends EntityPathBase<GiaoDich> {

    private static final long serialVersionUID = -1385982099L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGiaoDich giaoDich = new QGiaoDich("giaoDich");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QHoaDon hoaDon;

    public final NumberPath<Integer> idGiaoDich = createNumber("idGiaoDich", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    public final QNguoiDung nguoiDung;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final QTrangThai trangThai;

    public QGiaoDich(String variable) {
        this(GiaoDich.class, forVariable(variable), INITS);
    }

    public QGiaoDich(Path<? extends GiaoDich> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGiaoDich(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGiaoDich(PathMetadata metadata, PathInits inits) {
        this(GiaoDich.class, metadata, inits);
    }

    public QGiaoDich(Class<? extends GiaoDich> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hoaDon = inits.isInitialized("hoaDon") ? new QHoaDon(forProperty("hoaDon"), inits.get("hoaDon")) : null;
        this.nguoiDung = inits.isInitialized("nguoiDung") ? new QNguoiDung(forProperty("nguoiDung")) : null;
        this.trangThai = inits.isInitialized("trangThai") ? new QTrangThai(forProperty("trangThai")) : null;
    }

}

