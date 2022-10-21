package edu.hdu.variant1.service.impl;

import com.github.pagehelper.PageHelper;
import edu.hdu.variant1.bean.Course;
import edu.hdu.variant1.mapper.CourseMapper;
import edu.hdu.variant1.service.CourseService;
import edu.hdu.variant1.utils.FilePathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper cm;

    final FilePathUtil PathUtils = FilePathUtil.getInstance();


    @Override
    public List<Course> getAllCourse(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return cm.getAllCourse();
    }

    @Override
    public Integer insertCourse(String cId, String cName, Float time, String description, MultipartFile file, String uId) throws IOException {
        log.info("Information into service: courseId={}, courseName={}, time={}, description={}, file={}",
                cId, cName, time, description, file.getName());

        Date date = new Date();
        String filePath = saveFile(file);

        return cm.insertCourse(cId, cName, date, time, description, filePath, uId);
    }

    @Override
    public String getFilePathByCourseId(String cId) {
        return cm.getFilePathByCourseId(cId);
    }

    @Override
    public Integer deleteCourseByCourseId(String cId) {
        return cm.deleteCourseByCourseId(cId);
    }

    @Override
    public Integer updateCourse(String cId, String cName, Float time, String description, String uId, MultipartFile file) throws IOException {
        Course newCourse = new Course();
        Course oldCourse = cm.getCourseById(cId).get(0);

        newCourse.setId(oldCourse.getId()); // id comes from previous id
        newCourse.setCId(cId);
        newCourse.setCName(cName);
        newCourse.setTime(time);
        newCourse.setDescription(description);
        newCourse.setUId(uId);
        newCourse.setCreated(new Date());

        if (file.isEmpty()) { // if there is no file updated from frontier
            newCourse.setFilePath(oldCourse.getFilePath()); // then leave the file_path unchanged
        } else {
            String newFilePath = saveFile(file);
            newCourse.setFilePath(newFilePath);
        }

        return cm.updateCourse(newCourse);
    }

    @Override
    public List<Course> getCourseByUserId(String uId) {
        return cm.getCourseByUserId(uId);
    }

    @Override
    public Integer getAllCourseNum() {
        return cm.getAllCourseNum();
    }

    @Override
    public Integer getCourseByIdNum(String cId) {
        return cm.getCourseByIdNum(cId);
    }

    @Override
    public List<Course> getCourseById(String cId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return cm.getCourseById(cId);
    }

    public String saveFile(MultipartFile file) throws IOException {
        String filePath;
        String fileName = null;

        if (!file.isEmpty()) {
            String originalFileName = file.getOriginalFilename();
            if (originalFileName != null) {
                fileName = PathUtils.setUUIDName() + PathUtils.getExtName(originalFileName);
                filePath = PathUtils.getSavePath() + fileName;

                log.info("Saving path: {}", filePath);
                file.transferTo(new File(filePath));
            }
        }

        return PathUtils.getSourcePath(fileName);
    }
}
