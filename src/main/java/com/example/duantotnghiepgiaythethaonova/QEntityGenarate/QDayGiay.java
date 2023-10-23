package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.ChiTietSanPham;
import com.example.duantotnghiepgiaythethaonova.entity.DayGiay;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDayGiay is a Querydsl query type for DayGiay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDayGiay extends EntityPathBase<DayGiay> {

    private static final long serialVersionUID = -421393181L;

    public static final QDayGiay dayGiay = new QDayGiay("dayGiay");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<ChiTietSanPham, QChiTietSanPham> chiTietSanPhams = this.<ChiTietSanPham, QChiTietSanPham>createList("chiTietSanPhams", ChiTietSanPham.class, QChiTietSanPham.class, PathInits.DIRECT2);

    public final BooleanPath daXoa = createBoolean("daXoa");

    public final NumberPath<Integer> idDayGiay = createNumber("idDayGiay", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath tenDayGiay = createString("tenDayGiay");

    public QDayGiay(String variable) {
        super(DayGiay.class, forVariable(variable));
    }

    public QDayGiay(Path<? extends DayGiay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDayGiay(PathMetadata metadata) {
        super(DayGiay.class, metadata);
    }

}

