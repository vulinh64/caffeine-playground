package com.vulinh.template;

public interface AbstractMapper<E extends AbstractEntity, D extends AbstractDTO> {

    E toEntity(D dto);

    D toDto(E entity);
}