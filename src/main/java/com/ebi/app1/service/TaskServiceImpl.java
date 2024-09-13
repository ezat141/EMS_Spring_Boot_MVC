package com.ebi.app1.service;

import com.ebi.app1.entity.EmployeeEntity;
import com.ebi.app1.entity.TaskEntity;
import com.ebi.app1.exceprions.CustomException;
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
        if(taskDto.getName() == null || taskDto.getName().isEmpty()){
            throw new CustomException("400","Bad Request","  Task name is required.");
        }
        if (taskDto.getDescription() == null || taskDto.getDescription().isEmpty()){
            throw new CustomException("400", "Bad Request", "Description is required.");

        }
        if(taskDto.getDate() == null){
            throw new CustomException("400", "Bad Request", "Date is required.");

        }
        TaskEntity taskEntity = modelMapper.map(taskDto, TaskEntity.class);
        taskRepo.save(taskEntity);
        return taskDto;
    }

    @Override
    public TaskSaveDto updatePatchTask(TaskSaveDto taskSaveDto) {


        if(taskSaveDto != null || taskSaveDto.getId() == null) {
            throw new CustomException("400", "Bad Request", "Task ID is required for patch update");
        }
        Optional<TaskEntity> taskEntityOptional = taskRepo.findById(taskSaveDto.getId());
        if (taskEntityOptional.isEmpty()) {
            throw new CustomException("404", "Not Found", "Task not found with id: " + taskSaveDto.getId());
        }
        TaskEntity taskEntity = taskEntityOptional.get();
                if(taskSaveDto.getName() != null) {
                    taskEntity.setName(taskSaveDto.getName());
                }
                if(taskSaveDto.getDescription() != null) {
                    taskEntity.setDescription(taskSaveDto.getDescription());
                }
                if (taskSaveDto.getDate() != null) {
                    taskEntity.setDate(taskSaveDto.getDate());
                }
                if (taskSaveDto.getEmployeeId() != null) {


                    Optional<EmployeeEntity> employeeEntityOptional = employeeRepo.findById(taskSaveDto.getEmployeeId());
                    if (employeeEntityOptional.isEmpty()) {
                        throw new CustomException("404", "Not Found", "Employee not found with id: " + taskSaveDto.getEmployeeId());

                    }
                    taskEntity.setEmployeeEntity(employeeEntityOptional.get());


                }

        TaskEntity savedTaskEntity = taskRepo.save(taskEntity);
        return modelMapper.map(savedTaskEntity, TaskSaveDto.class);
    }
}
