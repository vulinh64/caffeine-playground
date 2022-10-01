package com.vulinh.mapper;

import com.vulinh.dto.EmployeeDTO;
import com.vulinh.model.secondary.Employee;
import com.vulinh.template.AbstractMapper;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true))
public interface EmployeeMapper extends AbstractMapper<Employee, EmployeeDTO> {}
