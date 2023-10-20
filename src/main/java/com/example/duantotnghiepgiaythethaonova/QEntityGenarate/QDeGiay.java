package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import com.example.duantotnghiepgiaythethaonova.entity.DeGiay;
import com.example.duantotnghiepgiaythethaonova.entity.SanPham;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QDeGiay is a Querydsl query type for PhongCach
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeGiay extends EntityPathBase<DeGiay> {

    private static final long serialVersionUID = 592882353L;

    public static final QDeGiay deGiay = new QDeGiay("phongCach");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final BooleanPath daXoa = createBoolean("daXoa");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final ListPath<SanPham, QSanPham> sanPhams = this.<SanPham, QSanPham>createList("sanPhams", SanPham.class, QSanPham.class, PathInits.DIRECT2);

    public final StringPath tenPhongCach = createString("tenPhongCach");

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

