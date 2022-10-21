package edu.hdu.variant1.bean;

import lombok.Data;

import java.util.List;

@Data
public class Page<Course> {
    private Integer pageSum;
    private Integer pageNum;
    private Integer pageSize;
    private List<Course> course;

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
