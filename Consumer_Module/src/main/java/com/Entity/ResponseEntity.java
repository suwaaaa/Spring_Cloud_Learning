package com.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class ResponseEntity implements Serializable {

    private Integer statusCode;

    private String statusCodeValue;

}
