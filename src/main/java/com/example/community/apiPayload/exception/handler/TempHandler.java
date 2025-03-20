package com.example.community.apiPayload.exception.handler;

import com.example.community.apiPayload.code.BaseCode;
import com.example.community.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseCode errorCode) {
        super(errorCode);
    }
}
