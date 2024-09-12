package com.ebi.app1.service;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.EmployeeSaveDto;

import java.util.List;

public interface EmployeeServiceInt {
     List<EmployeeDto> getAllEmployees();
     EmployeeDto getEmployeeById(Long id);
     EmployeeDto saveEmployee(EmployeeDto employeeDto);
     EmployeeSaveDto updateEmployee(EmployeeSaveDto employeeSaveDto);
     EmployeeSaveDto updatePatchEmployee(EmployeeSaveDto employeeSaveDto);
     void deleteEmployee(Long id);

}
