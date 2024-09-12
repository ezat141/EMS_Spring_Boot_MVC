package com.ebi.app1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSaveDto {
    private Long id;
    private String first_name;
    private String second_name;
    private String salary;
}
