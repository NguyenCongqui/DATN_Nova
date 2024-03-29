package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.DiaChi;
//import com.example.duantotnghiepgiaythethaonova.entity.QKhachHang;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiaChi is a Querydsl query type for DiaChi
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDiaChi extends EntityPathBase<DiaChi> {

    private static final long serialVersionUID = -1808039333L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiaChi diaChi1 = new QDiaChi("diaChi1");

//    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath diaChi = createString("diaChi");

    public final StringPath hoTen = createString("hoTen");

    public final NumberPath<Integer> idDiaChi = createNumber("idDiaChi", Integer.class);

    public final QKhachHang khachHang;

    public final BooleanPath laDiaChiMacDinh = createBoolean("laDiaChiMacDinh");

    //inherited
//    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;
//
//    //inherited
//    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;
//
//    //inherited
//    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;
//
//    //inherited
//    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath soDienThoai = createString("soDienThoai");

    public QDiaChi(String variable) {
        this(DiaChi.class, forVariable(variable), INITS);
    }

    public QDiaChi(Path<? extends DiaChi> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiaChi(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiaChi(PathMetadata metadata, PathInits inits) {
        this(DiaChi.class, metadata, inits);
    }

    public QDiaChi(Class<? extends DiaChi> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.khachHang = inits.isInitialized("khachHang") ? new QKhachHang(forProperty("khachHang"), inits.get("khachHang")) : null;
    }

}

