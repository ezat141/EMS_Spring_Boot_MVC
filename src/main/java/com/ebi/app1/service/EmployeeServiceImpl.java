package com.ebi.app1.service;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.entity.EmployeeEntity;
import com.ebi.app1.repo.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeServiceInt{
     private final ModelMapper modelMapper;
    private final EmployeeRepo employeeRepo;


    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepo.getAllEmployees();
        List<EmployeeDto> employeeDto = new ArrayList<>();

        employeeDto= employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class)).collect(Collectors.toList());

        return employeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepo.getEmployeeById(id);
        EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);

        return employeeDto;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeRepo.saveEmployee(employeeEntity);
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long id) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity employee = employeeRepo.updateEmployee(employeeEntity, id);

        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto updatePatchEmployee(EmployeeDto employeeDto, Long id) {
        EmployeeEntity employeeEntity = modelMapper.map(this.getEmployeeById(id), EmployeeEntity.class);
        if(employeeDto != null) {
            if(employeeDto.getName() != null) {
                employeeEntity.setName(employeeDto.getName());
            }
            if (employeeDto.getSalary() != null) {
                employeeEntity.setSalary(employeeDto.getSalary());
            }
            // TODO save in database
        }
        return employeeDto;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        return true;
    }
}


