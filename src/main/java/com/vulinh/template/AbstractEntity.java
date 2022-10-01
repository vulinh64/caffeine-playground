package com.vulinh.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
public abstract class AbstractEntity implements Serializable {

  private static final long serialVersionUID = -5162043753800272849L;

  @CreatedDate
  @Column(name = "created_date")
  protected LocalDateTime createdDate;

  @LastModifiedDate
  @Column(name = "updated_date")
  protected LocalDateTime updatedDate;

  @Column(name = "is_deleted")
  protected boolean isDeleted;

  public void markAsRemoved() {
    isDeleted = true;
  }
}
