package com.example.fiegerunner.mapper;

public interface Mapper <F,T> {

    T map(F object);

    F reverseMap(T object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
