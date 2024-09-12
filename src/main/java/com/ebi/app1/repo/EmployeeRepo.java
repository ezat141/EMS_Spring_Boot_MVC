package com.ebi.app1.repo;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepo {
     List<EmployeeEntity> getAllEmployees();
     EmployeeEntity getEmployeeById(Long id);
     EmployeeEntity saveEmployee(EmployeeEntity employeeEntity);
     EmployeeEntity updateEmployee(EmployeeEntity employeeEntity, Long id);
     EmployeeEntity updatePatchEmployee(EmployeeEntity employeeEntity, Long id);
     boolean deleteEmployee(Long id);

}
