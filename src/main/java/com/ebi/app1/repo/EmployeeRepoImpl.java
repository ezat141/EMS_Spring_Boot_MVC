package com.ebi.app1.repo;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepoImpl implements EmployeeRepo {


    @Override
    public List<EmployeeEntity> getAllEmployees() {
        EmployeeEntity employee = new EmployeeEntity(1L, "ahmed", "2000");
        EmployeeEntity employee1 = new EmployeeEntity(2L, "ezzat", "3000");
        List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
        employees.add(employee);
        employees.add(employee1);

        return employees;
    }

    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        EmployeeEntity employee = new EmployeeEntity(id, "ahmed", "2000");
        return employee;
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return employeeEntity;
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity, Long id) {


        return employeeEntity;
    }

    @Override
    public EmployeeEntity updatePatchEmployee(EmployeeEntity employeeEntity, Long id) {

        return employeeEntity;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        return true;
    }
}
