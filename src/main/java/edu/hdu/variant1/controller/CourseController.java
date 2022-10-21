package edu.hdu.variant1.controller;

import edu.hdu.variant1.bean.Course;
import edu.hdu.variant1.bean.Message;
import edu.hdu.variant1.bean.Page;
import edu.hdu.variant1.bean.Ret;
import edu.hdu.variant1.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * CourseController is used for the exhibition of the courses.
 * Including searching, paging query, exhibiting and so on.
 */
@RestController
public class CourseController {

    @Autowired
    private CourseService cs;

    @GetMapping("/course")
    public Page<Course> getAllCourseByPageHelper(@RequestHeader("page_num")Integer pageNum,
                                                 @RequestHeader("page_size")Integer pageSize) {
        Page<Course> coursePage = new Page<>(pageNum, pageSize);
        coursePage.setPageSum(cs.getAllCourseNum());
        coursePage.setCourse(cs.getAllCourse(pageNum, pageSize));
        return coursePage;
    }

    @DeleteMapping("/course")
    public Ret deleteCourse(@RequestHeader("course_id") String cId) {
        Ret ret = new Ret();
        Integer row = cs.deleteCourseByCourseId(cId);
        if (row == 1) {
            ret.setSuccessRet();
        } else {
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("Delete failed", "0")});
        }
        return ret;
    }

    @GetMapping("/course/query")
    public Page<Course> getCourseById(@RequestHeader("course_id") String cId,
                                      @RequestHeader("page_num") Integer pageNum,
                                      @RequestHeader("page_size") Integer pageSize) {
        Page<Course> coursePage = new Page<>(pageNum, pageSize);
        coursePage.setPageSum(cs.getCourseByIdNum(cId));
        coursePage.setCourse(cs.getCourseById(cId,pageNum, pageSize));
        return coursePage;
    }

    @GetMapping("/manage_course")
    public List<Course> getCourseByTeacher(@RequestHeader("user_id") String uId) {
        return cs.getCourseByUserId(uId);
    }
}
