package com.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class RequestResult implements Serializable {

    private String requestRequestValue;

    private Integer requestCode;

    public RequestResult(Integer requestCode, String requestRequestValue) {
        this.requestRequestValue = requestRequestValue;
        this.requestCode = requestCode;
    }
}
