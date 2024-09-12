package com.ebi.app1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskSaveDto {
    private Long id;
    private String name;
    private String description;
    private String date;
    private Long employeeId;
}
