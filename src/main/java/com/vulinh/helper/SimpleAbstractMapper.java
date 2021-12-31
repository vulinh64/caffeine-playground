package com.vulinh.helper;

import com.vulinh.template.AbstractDTO;
import com.vulinh.template.AbstractEntity;
import com.vulinh.template.AbstractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public abstract class SimpleAbstractMapper<E extends AbstractEntity, D extends AbstractDTO> implements AbstractMapper<E, D> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    @Autowired
    private ObjectMapperWrapper objectMapper;

    @Override
    public E toEntity(D dto) {
        return objectMapper.convert(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return objectMapper.convert(entity, dtoClass);
    }
}