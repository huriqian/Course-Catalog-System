package edu.hdu.variant1.mapper;

import edu.hdu.variant1.bean.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> getCourseById(String cId);

    List<Course> getAllCourse();

    /**
     * This method can insert data of course into DB.
     * @param cId : course ID
     * @param cName : course name
     * @param date : created time
     * @param time : duration of the course
     * @param description : brief description of course
     * @param filePath : saving path on the server
     * @param uId : teacher who created the course
     * @return Rows that is influenced
     */
    Integer insertCourse(String cId, String cName, Date date, Float time, String description, String filePath, String uId);

    String getFilePathByCourseId(String cId);

    Integer deleteCourseByCourseId(String cId);

    Integer updateCourse(Course course);

    List<Course> getCourseByUserId(String uId);

    Integer getAllCourseNum();

    Integer getCourseByIdNum(String cId);
}
