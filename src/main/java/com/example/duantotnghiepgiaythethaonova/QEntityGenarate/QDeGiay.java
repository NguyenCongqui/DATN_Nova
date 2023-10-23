package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.DeGiay;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeGiay is a Querydsl query type for DeGiay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeGiay extends EntityPathBase<DeGiay> {

    private static final long serialVersionUID = -1812471666L;

    public static final QDeGiay deGiay = new QDeGiay("deGiay");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<ChiTietSanPham, QChiTietSanPham> chiTietSanPhams = this.<ChiTietSanPham, QChiTietSanPham>createList("chiTietSanPhams", ChiTietSanPham.class, QChiTietSanPham.class, PathInits.DIRECT2);

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final NumberPath<Integer> idDeGiay = createNumber("idDeGiay", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath tenDeGiay = createString("tenDeGiay");

    public QDeGiay(String variable) {
        super(DeGiay.class, forVariable(variable));
    }

    public QDeGiay(Path<? extends DeGiay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeGiay(PathMetadata metadata) {
        super(DeGiay.class, metadata);
    }

}

