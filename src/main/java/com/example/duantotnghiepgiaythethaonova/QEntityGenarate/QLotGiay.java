package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.LotGiay;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLotGiay is a Querydsl query type for LotGiay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLotGiay extends EntityPathBase<LotGiay> {

    private static final long serialVersionUID = -1515107816L;

    public static final QLotGiay lotGiay = new QLotGiay("lotGiay");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<ChiTietSanPham, QChiTietSanPham> chiTietSanPhams = this.<ChiTietSanPham, QChiTietSanPham>createList("chiTietSanPhams", ChiTietSanPham.class, QChiTietSanPham.class, PathInits.DIRECT2);

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final NumberPath<Integer> idLotGiay = createNumber("idLotGiay", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath tenLotGiay = createString("tenLotGiay");

    public QLotGiay(String variable) {
        super(LotGiay.class, forVariable(variable));
    }

    public QLotGiay(Path<? extends LotGiay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLotGiay(PathMetadata metadata) {
        super(LotGiay.class, metadata);
    }

}

