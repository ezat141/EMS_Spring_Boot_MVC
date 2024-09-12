package com.ebi.app1.controller;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.EmployeeSaveDto;
import com.ebi.app1.service.EmployeeServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeServiceInt employeeServiceInt;

    @GetMapping
    public List<EmployeeDto> getAllEmployees(){
        return employeeServiceInt.getAllEmployees();
    }

    @PostMapping
    public EmployeeDto saveEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeServiceInt.saveEmployee(employeeDto);
    }

    @PutMapping
    public EmployeeSaveDto updateEmployee(@RequestBody EmployeeSaveDto employeeSaveDto){
        return employeeServiceInt.updateEmployee(employeeSaveDto);

    }
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeServiceInt.getEmployeeById(id);

    }

    @PatchMapping

    public EmployeeSaveDto updatePatchEmployee(@RequestBody EmployeeSaveDto employeeSaveDto) {

        return employeeServiceInt.updatePatchEmployee(employeeSaveDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeServiceInt.deleteEmployee(id);
    }
}

