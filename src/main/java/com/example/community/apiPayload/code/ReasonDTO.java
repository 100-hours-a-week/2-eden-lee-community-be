package com.example.community.apiPayload.code;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReasonDTO {
    private HttpStatus httpStatus;
    private String code;
    private String message;
    private boolean isSuccess;
}
