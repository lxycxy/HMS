package com.back.backend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String name;

    private String password;

    private String role;

    private String tel;

    private Integer age;

    private String sex;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
}
