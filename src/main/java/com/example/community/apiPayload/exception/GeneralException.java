package com.example.community.apiPayload.exception;

import com.example.community.apiPayload.code.BaseCode;
import com.example.community.apiPayload.code.ReasonDTO;
import lombok.*;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseCode code;

    public ReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}