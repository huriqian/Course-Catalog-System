package edu.hdu.variant1.service;

import edu.hdu.variant1.bean.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    List<Course> getCourseById(String cId, Integer pageNum, Integer pageSize);

    List<Course> getAllCourse(Integer pageNum, Integer pageSize);

    Integer insertCourse(String cId, String cName, Float time, String description, MultipartFile file, String uId) throws IOException;

    String getFilePathByCourseId(String cId);

    Integer deleteCourseByCourseId(String cId);

    Integer updateCourse(String cId, String cName, Float time, String description, String uId, MultipartFile file) throws IOException;

    List<Course> getCourseByUserId(String uId);

    Integer getAllCourseNum();

    Integer getCourseByIdNum(String cId);

}
