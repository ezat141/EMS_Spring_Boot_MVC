package com.ebi.app1.service;

import com.ebi.app1.model.EmployeeDto;

import java.util.List;

public interface EmployeeServiceInt {
     List<EmployeeDto> getAllEmployees();
     EmployeeDto getEmployeeById(Long id);
     EmployeeDto saveEmployee(EmployeeDto employeeDto);
     EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id);
     EmployeeDto updatePatchEmployee(EmployeeDto employeeDto, Long id);
     boolean deleteEmployee(Long id);

}
