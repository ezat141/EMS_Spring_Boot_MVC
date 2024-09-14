package com.ebi.app1.controller;

import com.ebi.app1.exceprions.CustomException;
import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.EmployeeSaveDto;
import com.ebi.app1.model.GeneralResponse;
import com.ebi.app1.service.EmployeeServiceInt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
@RequiredArgsConstructor

public class EmployeeController {

    private final EmployeeServiceInt employeeServiceInt;
    @Value("${success.message}")
    private String successMessage;
    @Value("${success.code}")
    private String successCode;

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllEmployees(){
        List<EmployeeDto> employees = employeeServiceInt.getAllEmployees();
        GeneralResponse <List<EmployeeDto>> response = new GeneralResponse<>(successCode, successMessage, employees);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeServiceInt.saveEmployee(employeeDto);
        GeneralResponse <EmployeeDto> response = new GeneralResponse<>(successCode, successMessage, employeeDto1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeSaveDto employeeSaveDto){
        EmployeeSaveDto employeeSaveDto1 = employeeServiceInt.updateEmployee(employeeSaveDto);
        GeneralResponse <EmployeeSaveDto> response = new GeneralResponse<>(successCode, successMessage, employeeSaveDto1);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employeeDto = employeeServiceInt.getEmployeeById(id);
        GeneralResponse <EmployeeDto> response = new GeneralResponse<>(successCode, successMessage, employeeDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PatchMapping
    @ResponseBody

    public ResponseEntity<?> updatePatchEmployee(@RequestBody EmployeeSaveDto employeeSaveDto) {
        EmployeeSaveDto employeeSaveDto1 = employeeServiceInt.updateEmployee(employeeSaveDto);
        GeneralResponse<EmployeeSaveDto> response = new GeneralResponse<>(successCode, successMessage, employeeSaveDto1);


        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeServiceInt.deleteEmployee(id);
//        String str = "Deleted employee with id " + id + " successfully";
        GeneralResponse <String> response = new GeneralResponse<>(successCode, successMessage, null);

         return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/view")
    public String getAllEmployeesView(Model model){
        List<EmployeeDto> employees = employeeServiceInt.getAllEmployees();
        GeneralResponse <List<EmployeeDto>> response = new GeneralResponse<>(successCode, successMessage, employees);
        model.addAttribute("response", response);
        return "employee";
    }

    @GetMapping("/register")
    public String getEmployeeRegisterView(Model model){
        model.addAttribute("employee", new EmployeeDto());
        return "addEmployee";
    }

    @PostMapping("/register")
    public String registerEmployee(EmployeeDto employeeDto, Model model){
        EmployeeDto employeeDtoSaved = employeeServiceInt.saveEmployee(employeeDto);
        model.addAttribute("employee", new EmployeeDto());
        return "redirect:view";

    }

    @ExceptionHandler(value = CustomException.class)
    String notFoundElement(CustomException customException, Model model){
        model.addAttribute("error", customException.getMessage());
        return "error";
    }

}

