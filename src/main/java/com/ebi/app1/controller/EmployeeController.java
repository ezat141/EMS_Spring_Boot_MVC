package com.ebi.app1.controller;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.EmployeeSaveDto;
import com.ebi.app1.model.GeneralResponse;
import com.ebi.app1.service.EmployeeServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeServiceInt employeeServiceInt;
    @Value("${success.message}")
    private String successMessage;
    @Value("${success.code}")
    private String successCode;

    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        List<EmployeeDto> employees = employeeServiceInt.getAllEmployees();
        GeneralResponse <List<EmployeeDto>> response = new GeneralResponse<>(successCode, successMessage, employees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeServiceInt.saveEmployee(employeeDto);
        GeneralResponse <EmployeeDto> response = new GeneralResponse<>(successCode, successMessage, employeeDto1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeSaveDto employeeSaveDto){
        EmployeeSaveDto employeeSaveDto1 = employeeServiceInt.updateEmployee(employeeSaveDto);
        GeneralResponse <EmployeeSaveDto> response = new GeneralResponse<>(successCode, successMessage, employeeSaveDto1);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeServiceInt.getEmployeeById(id);
        GeneralResponse <EmployeeDto> response = new GeneralResponse<>(successCode, successMessage, employeeDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PatchMapping

    public ResponseEntity<?> updatePatchEmployee(@RequestBody EmployeeSaveDto employeeSaveDto) {
        EmployeeSaveDto employeeSaveDto1 = employeeServiceInt.updateEmployee(employeeSaveDto);
        GeneralResponse<EmployeeSaveDto> response = new GeneralResponse<>(successCode, successMessage, employeeSaveDto1);


        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeServiceInt.deleteEmployee(id);
//        String str = "Deleted employee with id " + id + " successfully";
        GeneralResponse <String> response = new GeneralResponse<>(successCode, successMessage, null);

         return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

