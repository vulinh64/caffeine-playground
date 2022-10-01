package com.vulinh.template;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/*
 * For the sake of performance, write mapping methods yourself and don't rely on something like Jackson's object mapper or so!
 */
public interface AbstractMapper<E extends AbstractEntity, D extends AbstractDTO> {

  E toEntity(D dto);

  D toDto(E entity);

  default Page<E> toEntityPage(Page<? extends D> dtoPage) {
    return dtoPage.map(this::toEntity);
  }

  default Page<D> toDtoPage(Page<? extends E> entityPage) {
    return entityPage.map(this::toDto);
  }

  default List<E> toEntityList(List<? extends D> dtoList) {
    return dtoList.parallelStream().map(this::toEntity).collect(Collectors.toList());
  }

  default List<D> toDtoList(List<? extends E> entityList) {
    return entityList.parallelStream().map(this::toDto).collect(Collectors.toList());
  }
}
