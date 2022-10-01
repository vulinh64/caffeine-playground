package com.vulinh.template;

import com.vulinh.constant.CacheConstant;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.function.Supplier;

@NoRepositoryBean
public interface FailFastJpaRepository<I, E extends AbstractEntity> extends JpaRepository<E, I> {

  @Cacheable(
      cacheNames = CacheConstant.FIND_BY_ID_CACHE,
      keyGenerator = CacheConstant.FIND_BY_ID_GEN_BEAN)
  default E findById(I id, Supplier<? extends RuntimeException> exceptionSupplier) {
    return findById(id).orElseThrow(exceptionSupplier);
  }

  @Override
  @Cacheable(
      cacheNames = CacheConstant.FIND_ALL_PAGING,
      keyGenerator = CacheConstant.FIND_ALL_PAGING_GEN_BEAN)
  Page<E> findAll(Pageable pageable);
}
