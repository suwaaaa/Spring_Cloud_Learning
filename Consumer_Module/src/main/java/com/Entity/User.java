package com.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Accessors(chain = true)//链式写法
public class User implements Serializable {

    private Integer userId;

    private String userName;

    private Integer age;

    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;

    public User(Integer userId, String userName, Integer age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }
}
