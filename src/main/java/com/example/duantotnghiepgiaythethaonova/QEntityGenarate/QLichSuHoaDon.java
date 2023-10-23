package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.LichSuHoaDon;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLichSuHoaDon is a Querydsl query type for LichSuHoaDon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLichSuHoaDon extends EntityPathBase<LichSuHoaDon> {

    private static final long serialVersionUID = -26900992L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLichSuHoaDon lichSuHoaDon = new QLichSuHoaDon("lichSuHoaDon");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QHoaDon hoaDon;

    public final NumberPath<Integer> idLichSuHoaDon = createNumber("idLichSuHoaDon", Integer.class);

    //inherited
    public final DateTimePath<java.util.Date> ngayCapNhat = _super.ngayCapNhat;

    //inherited
    public final DateTimePath<java.util.Date> ngayTao = _super.ngayTao;

    //inherited
    public final StringPath nguoiCapNhat = _super.nguoiCapNhat;

    //inherited
    public final StringPath nguoiTao = _super.nguoiTao;

    public final StringPath nguoiThaoTac = createString("nguoiThaoTac");

    public final StringPath thaoTac = createString("thaoTac");

    public QLichSuHoaDon(String variable) {
        this(LichSuHoaDon.class, forVariable(variable), INITS);
    }

    public QLichSuHoaDon(Path<? extends LichSuHoaDon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLichSuHoaDon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLichSuHoaDon(PathMetadata metadata, PathInits inits) {
        this(LichSuHoaDon.class, metadata, inits);
    }

    public QLichSuHoaDon(Class<? extends LichSuHoaDon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hoaDon = inits.isInitialized("hoaDon") ? new QHoaDon(forProperty("hoaDon"), inits.get("hoaDon")) : null;
    }

}

