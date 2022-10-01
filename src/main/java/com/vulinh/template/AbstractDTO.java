package com.vulinh.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(Include.NON_NULL)
public abstract class AbstractDTO implements Serializable {

  private static final long serialVersionUID = -6123040993496246510L;

  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;

  @JsonIgnore private boolean isDeleted;
}
