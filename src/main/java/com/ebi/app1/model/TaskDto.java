package com.ebi.app1.model;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskDto {
    private String name;
    private String description;
    private String date;

}
