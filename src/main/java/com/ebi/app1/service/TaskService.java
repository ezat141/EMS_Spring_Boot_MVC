package com.ebi.app1.service;

import com.ebi.app1.model.EmployeeDto;
import com.ebi.app1.model.EmployeeSaveDto;
import com.ebi.app1.model.TaskDto;
import com.ebi.app1.model.TaskSaveDto;

public interface TaskService {
    TaskDto addTask(TaskDto taskDto);
    TaskSaveDto updatePatchTask(TaskSaveDto taskSaveDto);



}
