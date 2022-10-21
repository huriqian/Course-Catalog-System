package edu.hdu.variant1.bean;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String uId; // 学号 / 老师代号 / 管理员代号
    private String uName; // 用户名称
    private Integer age;
    private Integer gender;
    private Date created;
}
