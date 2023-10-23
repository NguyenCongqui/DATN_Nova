package com.example.duantotnghiepgiaythethaonova.QEntityGenarate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.duantotnghiepgiaythethaonova.entity.TrangThai;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTrangThai is a Querydsl query type for TrangThai
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTrangThai extends EntityPathBase<TrangThai> {

    private static final long serialVersionUID = 765620645L;

    public static final QTrangThai trangThai = new QTrangThai("trangThai");

    public final NumberPath<Integer> idTrangThai = createNumber("idTrangThai", Integer.class);

    public final StringPath name = createString("name");

    public QTrangThai(String variable) {
        super(TrangThai.class, forVariable(variable));
    }

    public QTrangThai(Path<? extends TrangThai> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrangThai(PathMetadata metadata) {
        super(TrangThai.class, metadata);
    }

}

