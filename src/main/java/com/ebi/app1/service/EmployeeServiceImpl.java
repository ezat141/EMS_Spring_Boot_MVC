package com.ebi.app1.service;

import com.ebi.app1.exceprions.CustomException;
import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.entity.EmployeeEntity;
import com.ebi.app1.model.EmployeeSaveDto;
import com.ebi.app1.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeServiceInt{
     private final ModelMapper modelMapper;
    private final EmployeeRepo employeeRepo;


    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepo.findAll();
        if(employeeEntities.isEmpty()){
            throw new CustomException("404", "No Employees Found", "The employee list is empty.");
        }
        List<EmployeeDto> employeeDto = new ArrayList<>();

        employeeDto= employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class)).collect(Collectors.toList());

        return employeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepo.findById(id)
                .orElseThrow(() -> new CustomException("404", "No Employees Found", "No employee found with id: " + id));

        return modelMapper.map(employeeEntity, EmployeeDto.class);
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        if(employeeEntity.getFirst_name() == null || employeeEntity.getFirst_name().isEmpty()){
            throw new CustomException("400","Bad Request","First name is required.");
        }
        if (employeeEntity.getSecond_name() == null || employeeEntity.getSecond_name().isEmpty()){
            throw new CustomException("400", "Bad Request", "Second name is required.");

        }
        if(employeeEntity.getSalary() == null){
            throw new CustomException("400", "Bad Request", "Salary is required.");

        }
        employeeRepo.save(employeeEntity);
        return employeeDto;
    }

    @Override
    public EmployeeSaveDto updateEmployee(EmployeeSaveDto employeeSaveDto) {

        EmployeeEntity employeeEntity = employeeRepo.findById(employeeSaveDto.getId())
                .orElseThrow(() -> new CustomException("404", "Employee Not Found", "Cannot update non-existing employee with id: " + employeeSaveDto.getId()));


        if(employeeSaveDto.getFirst_name() != null){
            employeeEntity.setFirst_name(employeeSaveDto.getFirst_name());
        }
        if(employeeSaveDto.getSecond_name() != null){
            employeeEntity.setSecond_name(employeeSaveDto.getSecond_name());
        }
        if(employeeSaveDto.getSalary() != null){
            employeeEntity.setSalary(employeeSaveDto.getSalary());
        }

        EmployeeEntity savedEmployeeEntity = employeeRepo.save(employeeEntity);

        return modelMapper.map(savedEmployeeEntity, EmployeeSaveDto.class);
    }

    @Override
    public EmployeeSaveDto updatePatchEmployee(EmployeeSaveDto employeeSaveDto) {

        EmployeeEntity savedEmployeeEntity = null;

        if(employeeSaveDto != null || employeeSaveDto.getId() == null){
            throw new CustomException("400", "Bad Request", "The id is required for patch update.");

        }
            EmployeeEntity employeeEntity = employeeRepo.findById(employeeSaveDto.getId())
                    .orElseThrow(() -> new CustomException("404", "Employee Not Found", "Cannot patch non-existing employee with id: " + employeeSaveDto.getId()));
            if(employeeSaveDto.getFirst_name() != null) {
                employeeEntity.setFirst_name(employeeSaveDto.getFirst_name());
            }
            if(employeeSaveDto.getSecond_name() != null) {
                employeeEntity.setSecond_name(employeeSaveDto.getSecond_name());
            }
            if (employeeSaveDto.getSalary() != null) {
                employeeEntity.setSalary(employeeSaveDto.getSalary());
            }
            savedEmployeeEntity = employeeRepo.save(employeeEntity) ;

        return modelMapper.map(savedEmployeeEntity, EmployeeSaveDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        if(employeeRepo.findById(id).isPresent()){
            employeeRepo.deleteById(id);
        }
        else {
            throw new CustomException("404", "Employee Not Found", "Cannot delete non-existing employee with id: " + id);
        }



    }
}


