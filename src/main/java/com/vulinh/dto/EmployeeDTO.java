package com.vulinh.dto;

import com.vulinh.template.AbstractDTO;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EmployeeDTO extends AbstractDTO {

  private static final long serialVersionUID = -8835076679376578546L;

  private String id;
  private String firstName;
  private String lastName;
  private Integer gender;
  private LocalDate birthDate;
}
