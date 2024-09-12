package com.ebi.app1.controller;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.entity.EmployeeEntity;
import com.ebi.app1.service.EmployeeServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id){
        return employeeServiceInt.updateEmployee(employeeDto, id);

    }
    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeServiceInt.getEmployeeById(id);

    }

    @PatchMapping("/{id}")

    public EmployeeDto updatePatchEmployee(@RequestBody EmployeeDto employeeDto,@PathVariable Long id) {

        return employeeServiceInt.updatePatchEmployee(employeeDto, id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        return employeeServiceInt.deleteEmployee(id);
    }
}

