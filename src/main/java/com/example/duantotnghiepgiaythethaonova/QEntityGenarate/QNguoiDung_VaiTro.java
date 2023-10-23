package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.NguoiDung_VaiTro;
//import com.example.duantotnghiepgiaythethaonova.entity.QVaiTro;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNguoiDung_VaiTro is a Querydsl query type for NguoiDung_VaiTro
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNguoiDung_VaiTro extends EntityPathBase<NguoiDung_VaiTro> {

    private static final long serialVersionUID = 2035233573L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNguoiDung_VaiTro nguoiDung_VaiTro = new QNguoiDung_VaiTro("nguoiDung_VaiTro");

    public final NumberPath<Integer> idNguoiDung_VaiTro = createNumber("idNguoiDung_VaiTro", Integer.class);

    public final QNguoiDung nguoiDung;

    public final QVaiTro vaiTro;

    public QNguoiDung_VaiTro(String variable) {
        this(NguoiDung_VaiTro.class, forVariable(variable), INITS);
    }

    public QNguoiDung_VaiTro(Path<? extends NguoiDung_VaiTro> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNguoiDung_VaiTro(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNguoiDung_VaiTro(PathMetadata metadata, PathInits inits) {
        this(NguoiDung_VaiTro.class, metadata, inits);
    }

    public QNguoiDung_VaiTro(Class<? extends NguoiDung_VaiTro> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.nguoiDung = inits.isInitialized("nguoiDung") ? new QNguoiDung(forProperty("nguoiDung")) : null;
        this.vaiTro = inits.isInitialized("vaiTro") ? new QVaiTro(forProperty("vaiTro")) : null;
    }

}

