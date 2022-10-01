package com.vulinh.helper;

import com.vulinh.template.AbstractDTO;
import com.vulinh.template.AbstractValidator;

public abstract class SimpleAbstractValidator<D extends AbstractDTO>
    implements AbstractValidator<D> {

  @Override
  public D validateOnCreate(D dto) {
    return dto;
  }

  @Override
  public D validateOnUpdate(D dto) {
    return dto;
  }
}
