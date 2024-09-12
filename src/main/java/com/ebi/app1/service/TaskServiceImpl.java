package com.ebi.app1.service;

import com.ebi.app1.entity.EmployeeEntity;
import com.ebi.app1.entity.TaskEntity;
import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.EmployeeSaveDto;
import com.ebi.app1.model.TaskDto;
import com.ebi.app1.model.TaskSaveDto;
import com.ebi.app1.repo.EmployeeRepo;
import com.ebi.app1.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final ModelMapper modelMapper;
    private final TaskRepo taskRepo;
    private final EmployeeRepo employeeRepo;


    @Override
    public TaskDto addTask(TaskDto taskDto) {
        TaskEntity taskEntity = modelMapper.map(taskDto, TaskEntity.class);
        taskRepo.save(taskEntity);
        return taskDto;
    }

    @Override
    public TaskSaveDto updatePatchTask(TaskSaveDto taskSaveDto) {
        TaskEntity taskEntity = null;

        if(taskSaveDto != null) {
            Optional<TaskEntity> taskEntityOptional = taskRepo.findById(taskSaveDto.getId());
            if(taskEntityOptional.isPresent()) {
                if(taskSaveDto.getName() != null) {
                    taskEntityOptional.get().setName(taskSaveDto.getName());
                }
                if(taskSaveDto.getDescription() != null) {
                    taskEntityOptional.get().setDescription(taskSaveDto.getDescription());
                }
                if (taskSaveDto.getDate() != null) {
                    taskEntityOptional.get().setDate(taskSaveDto.getDate());
                }
                if (taskSaveDto.getEmployeeId() != null) {


                    Optional<EmployeeEntity> employeeEntityOptional = employeeRepo.findById(taskSaveDto.getEmployeeId());
                    if (employeeEntityOptional.isPresent()) {
                        taskEntityOptional.get().setEmployeeEntity(employeeEntityOptional.get());

                    }
                }
                taskEntity = taskRepo.save(taskEntityOptional.get()) ;

            }



        }

        return modelMapper.map(taskEntity, TaskSaveDto.class);
    }
}
