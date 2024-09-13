package com.ebi.app1.controller;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.EmployeeSaveDto;
import com.ebi.app1.model.TaskDto;
import com.ebi.app1.model.TaskSaveDto;
import com.ebi.app1.service.EmployeeServiceInt;
import com.ebi.app1.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @Value("${success.message}")
    private String successMessage;
    @Value("${success.code}")
    private String successCode;

    @PostMapping
    public TaskDto addTask(@RequestBody TaskDto taskDto){
        return taskService.addTask(taskDto);
    }

    @PatchMapping
    public TaskSaveDto updatePatchTask(@RequestBody TaskSaveDto taskSaveDto) {

        return taskService.updatePatchTask(taskSaveDto);
    }



}
