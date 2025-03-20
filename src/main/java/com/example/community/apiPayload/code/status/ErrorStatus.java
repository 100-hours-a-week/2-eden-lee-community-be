package com.example.community.apiPayload.code.status;

import com.example.community.apiPayload.code.BaseCode;
import com.example.community.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"400","잘못된 요청입니다."),
    SAME_PASSWORD(HttpStatus.BAD_REQUEST,"400","새 비밀번호는 기존 비밀번호와 달라야 합니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"401","인증이 필요합니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "해당 사용자를 찾을 수 없습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 에러, 관리자에게 문의 바랍니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .httpStatus(httpStatus)
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }
}
