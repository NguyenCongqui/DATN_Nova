package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.KichCo;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QKichCo is a Querydsl query type for KichCo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKichCo extends EntityPathBase<KichCo> {

    private static final Integer serialVersionUID = -2040708367;

    public static final QKichCo kichCo = new QKichCo("kichCo");

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

    public final ListPath<ChiTietSanPham, QSanPhamChiTiet> sanPhamChiTiets = this.<ChiTietSanPham, QSanPhamChiTiet>createList("sanPhamChiTiets", ChiTietSanPham.class, QSanPhamChiTiet.class, PathInits.DIRECT2);

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

