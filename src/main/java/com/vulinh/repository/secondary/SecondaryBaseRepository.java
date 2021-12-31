package com.vulinh.repository.secondary;

import com.vulinh.template.AbstractEntity;
import com.vulinh.template.FailFastJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface SecondaryBaseRepository<I extends Serializable, E extends AbstractEntity> extends FailFastJpaRepository<I, E> {
}