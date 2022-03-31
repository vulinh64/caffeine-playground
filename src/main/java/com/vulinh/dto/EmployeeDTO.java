package com.vulinh.dto;

import com.vulinh.template.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
