package com.vulinh.template;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.function.Supplier;

@RequiredArgsConstructor
public abstract class AbstractService<
    I, E extends AbstractEntity, D extends AbstractDTO, R extends FailFastJpaRepository<I, E>> {

  protected D findById(I id) {
    return mapper.toDto(repository.findById(id, notFoundExceptionSupplier));
  }

  protected Page<D> findAll() {
    return findAll(Pageable.unpaged());
  }

  protected Page<D> findAll(Pageable pageable) {
    return repository.findAll(pageable).map(mapper::toDto);
  }

  protected D create(D dto) {
    return performSaveOrUpdate(validator.validateOnCreate(dto));
  }

  protected D update(D dto) {
    return performSaveOrUpdate(validator.validateOnUpdate(dto));
  }

  protected D delete(I id) {
    var deletedEntity = repository.findById(id, notFoundExceptionSupplier);
    return delete(deletedEntity);
  }

  protected D delete(E entity) {
    entity.markAsRemoved();
    return mapper.toDto(repository.saveAndFlush(entity));
  }

  protected void deleteAllByIds(Iterable<I> ids) {
    repository.deleteAllByIdInBatch(ids);
  }

  protected void deleteAll(Iterable<E> entities) {
    repository.deleteAllInBatch(entities);
  }

  private D performSaveOrUpdate(D dto) {
    var entity = mapper.toEntity(dto);
    return mapper.toDto(repository.saveAndFlush(entity));
  }

  private final Supplier<? extends RuntimeException> notFoundExceptionSupplier;
  private final AbstractValidator<D> validator;
  private final AbstractMapper<E, D> mapper;
  private final R repository;
}
