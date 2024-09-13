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
            throw new CustomException("400","Not Fount","No employees found");
        }
        List<EmployeeDto> employeeDto = new ArrayList<>();

        employeeDto= employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDto.class)).collect(Collectors.toList());

        return employeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepo.findById(id);
        if(employeeEntity.isEmpty()){
            throw new CustomException("400","Not Fount","No employees found");

        }

        return employeeEntity.map(entity -> modelMapper.map(entity, EmployeeDto.class)).orElse(null);
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        if(employeeEntity.getFirst_name() == null){
            throw new CustomException("400","Not found exception","The first name is required");
        }
        if (employeeEntity.getSecond_name() == null){
            throw new CustomException("400","Not found exception","The second name is required");
        }
        if(employeeEntity.getSalary() == null){
            throw new CustomException("400","Not found exception","The salary is required");
        }
        employeeRepo.save(employeeEntity);
        return employeeDto;
    }

    @Override
    public EmployeeSaveDto updateEmployee(EmployeeSaveDto employeeSaveDto) {

        EmployeeEntity employeeEntity = modelMapper.map(employeeSaveDto, EmployeeEntity.class);
        if(employeeEntity.getFirst_name() == null){

        }

        EmployeeEntity employee = employeeRepo.save(employeeEntity);

        return modelMapper.map(employee, EmployeeSaveDto.class);
    }

    @Override
    public EmployeeSaveDto updatePatchEmployee(EmployeeSaveDto employeeSaveDto) {

        EmployeeEntity savedEmployeeEntity = null;

        if(employeeSaveDto != null) {
            Optional<EmployeeEntity> employeeEntityOptional = employeeRepo.findById(employeeSaveDto.getId());
            if(employeeSaveDto.getFirst_name() != null) {
                employeeEntityOptional.get().setFirst_name(employeeSaveDto.getFirst_name());
            }
            if(employeeSaveDto.getSecond_name() != null) {
                employeeEntityOptional.get().setSecond_name(employeeSaveDto.getSecond_name());
            }
            if (employeeSaveDto.getSalary() != null) {
                employeeEntityOptional.get().setSalary(employeeSaveDto.getSalary());
            }
            savedEmployeeEntity = employeeRepo.save(employeeEntityOptional.get()) ;

        }

        return modelMapper.map(savedEmployeeEntity, EmployeeSaveDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);


    }
}


