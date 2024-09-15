package com.ebi.app1.controller;

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
    @GetMapping("/home")
    public String getAllEmployeesView(Model model){
        List<EmployeeDto> employees = employeeServiceInt.getAllEmployees();
        GeneralResponse <List<EmployeeDto>> response = new GeneralResponse<>(successCode, successMessage, employees);
        model.addAttribute("response", response);
        model.addAttribute("employee", new EmployeeDto());
        model.addAttribute("employeesavedto", new EmployeeSaveDto());
        return "index1";
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
        return "redirect:/employee/home";

    }
    @PostMapping("/update")
    public String updateEmployee( EmployeeSaveDto employeeSaveDto, Model model) {
        EmployeeSaveDto employeeSaveDto1 = employeeServiceInt.updateEmployee(employeeSaveDto);
        GeneralResponse <EmployeeSaveDto> response = new GeneralResponse<>(successCode, successMessage, employeeSaveDto1);
        model.addAttribute("employeesavedto", new EmployeeSaveDto());

        return "redirect:/employee/home";
    }

    @PostMapping("/patch")
    public String updatePatchEmployeeView( EmployeeSaveDto employeeSaveDto, Model model) {
        EmployeeSaveDto employeeSaveDto1 = employeeServiceInt.updatePatchEmployee(employeeSaveDto);
//        GeneralResponse<EmployeeSaveDto> response = new GeneralResponse<>(successCode, successMessage, employeeSaveDto1);
        model.addAttribute("employeesavedto", new EmployeeSaveDto());

        return "redirect:/employee/home";
    }

    @PostMapping("/search")
    public String getEmployeeById( EmployeeSaveDto employeeSaveDto , Model model) {
        EmployeeDto employeeDto = employeeServiceInt.getEmployeeById(employeeSaveDto.getId());
        GeneralResponse <EmployeeDto> response = new GeneralResponse<>(successCode, successMessage, employeeDto);
        model.addAttribute("employeesavedto", response);

        return "showAll";

    }



    @PostMapping("/delete")
    String deleteEmployee( EmployeeSaveDto employeeSaveDto , Model model)
    {
        model.addAttribute("employeesavedto", new EmployeeSaveDto());
        employeeServiceInt.deleteEmployee(employeeSaveDto.getId());

        return  "redirect:/employee/home";
    }




    @GetMapping("/update/{id}")
    public String getUpdateEmployeeView(@PathVariable Long id, Model model) {
        EmployeeDto employeeDto = employeeServiceInt.getEmployeeById(id); // Fetching the employee to pre-populate the form
        model.addAttribute("employeeSaveDto", employeeDto); // Passing employee data to the view
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteEmployeeView(@PathVariable Long id, Model model) {
        model.addAttribute("employeeId", id); // Passing the employee ID to the view
        return "redirect:/"; // This will render the 'deleteEmployee.html' view
    }

    @GetMapping("/delete-all")
    public String getDeleteAllEmployeesView(Model model) {
        return "redirect:/"; // This will render the 'deleteAllEmployees.html' view
    }








}

