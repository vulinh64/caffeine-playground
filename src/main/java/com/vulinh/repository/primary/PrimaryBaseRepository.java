package com.vulinh.repository.primary;

import com.vulinh.template.AbstractEntity;
import com.vulinh.template.FailFastJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface PrimaryBaseRepository<I extends Serializable, E extends AbstractEntity> extends FailFastJpaRepository<I, E> {
}