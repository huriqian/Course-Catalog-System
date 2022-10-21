package edu.hdu.variant1.controller;

import edu.hdu.variant1.bean.Message;
import edu.hdu.variant1.bean.Ret;
import edu.hdu.variant1.service.CourseService;
import edu.hdu.variant1.utils.FilePathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileController is used to upload and download the files of the courses.
 */
@RestController
@Slf4j
public class FileController {
    @Autowired
    private CourseService cs;

    final FilePathUtil PathUtils = FilePathUtil.getInstance();

    @PostMapping("/upload")
    public Ret upload(@RequestParam("course_id") String cId,
                      @RequestParam("course_name") String cName,
                      @RequestParam("time") Float time,
                      @RequestParam("description") String description,
                      @RequestParam("user_id") String uId,
                      @RequestPart("file") MultipartFile file) throws IOException {
        log.info("Information into controller: courseId={}, courseName={}, time={}, description={}, file={}",
                cId, cName, time, description, file.getName());

        Ret ret = new Ret();

        Integer row = cs.insertCourse(cId, cName, time, description, file, uId);

        if (row == 1) {
            ret.setSuccessRet();
        } else {
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("Upload failed", "0")});
        }

        return ret;
    }

    @PutMapping("/upload")
    public Ret update(@RequestParam("course_id") String cId,
                      @RequestParam("course_name") String cName,
                      @RequestParam("time") Float time,
                      @RequestParam("description") String description,
                      @RequestParam("user_id") String uId,
                      @RequestPart("file") MultipartFile file) throws IOException {
        Ret ret = new Ret();
        Integer row = cs.updateCourse(cId, cName, time, description, uId, file);

        if (row == 1) {
            ret.setSuccessRet();
        } else {
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("Update failed", "0")});
        }

        return ret;
    }

    @GetMapping("/download")
    public Ret fileDownload(@RequestHeader("course_id") String cId, HttpServletResponse response) {
        String sourceFilePath = cs.getFilePathByCourseId(cId);

        Ret ret = new Ret();

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            String filePath = PathUtils.getAbsPathPrefix() + sourceFilePath;
            File file = new File(filePath);
            if (!file.exists()) {
                ret.setFailureRet();
                ret.setMessage(new Message[]{new Message("File does not exist", "0")});
                response.setStatus(404);
            }

            response.setContentType("application/force-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            response.addHeader("Access-Control-Expose-Headers", "filename");
            response.addHeader("filename", file.getName());

            inputStream = new BufferedInputStream(Files.newInputStream(Paths.get(filePath)));
            outputStream = response.getOutputStream();

            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }

            response.flushBuffer();
            ret.setSuccessRet();

        } catch (IOException e) {
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("Download failed", "0")});
            response.setStatus(405);
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {

                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignored) {
                }
            }
        }

        return ret;
    }


}
