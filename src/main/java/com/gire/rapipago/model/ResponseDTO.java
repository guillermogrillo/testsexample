package com.gire.rapipago.model;

import lombok.Data;

@Data
public class ResponseDTO {

    private int code;

    private String data;

    private String error;

    public ResponseDTO() {

    }

    public ResponseDTO(int code, String data, String error) {
        this.code = code;
        this.data = data;
        this.error = error;
    }


}
