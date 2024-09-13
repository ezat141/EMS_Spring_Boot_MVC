package com.ebi.app1.exceprions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
    private String errorDescription;
    private LocalDateTime timestamp = LocalDateTime.now();
    private String path;

}
