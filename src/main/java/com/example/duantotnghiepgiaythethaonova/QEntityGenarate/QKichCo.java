package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKichCo is a Querydsl query type for KichCo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKichCo extends EntityPathBase<KichCo> {

    private static final long serialVersionUID = -1607541278L;

    public static final QKichCo kichCo = new QKichCo("kichCo");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<ChiTietSanPham, QChiTietSanPham> chiTietSanPhams = this.<ChiTietSanPham, QChiTietSanPham>createList("chiTietSanPhams", ChiTietSanPham.class, QChiTietSanPham.class, PathInits.DIRECT2);

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final NumberPath<Integer> idKichCo = createNumber("idKichCo", Integer.class);

    public final StringPath maKichCo = createString("maKichCo");

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath tenKichCo = createString("tenKichCo");

    public QKichCo(String variable) {
        super(KichCo.class, forVariable(variable));
    }

    public QKichCo(Path<? extends KichCo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKichCo(PathMetadata metadata) {
        super(KichCo.class, metadata);
    }

}

