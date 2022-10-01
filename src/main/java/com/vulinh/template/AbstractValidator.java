package com.vulinh.template;

public interface AbstractValidator<D extends AbstractDTO> {

  D validateOnCreate(D dto);

  D validateOnUpdate(D dto);
}
