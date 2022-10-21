package edu.hdu.variant1.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Course {
    private Integer id;
    private String cId; // 课程代码
    private String cName; // 课程名称
    private String uId; // 添加课程的老师
    private Float time;
    private String description;
    private String filePath;
    private Date created;
}
