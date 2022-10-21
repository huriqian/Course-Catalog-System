package edu.hdu.variant1.utils;

import org.springframework.boot.system.ApplicationHome;

import java.util.UUID;

/**
 * Singleton FilePathUtil
 */
public class FilePathUtil {

    private final String sourcePath = "\\src\\main\\resources\\static\\upload\\";

    /**
     * applicationHome can get the absolute path prefix in the local server.
     */
    private final ApplicationHome applicationHome = new ApplicationHome(this.getClass());
    private final String absolutePathPrefix = applicationHome.getDir().getParentFile().
            getParentFile().getAbsolutePath();


    private FilePathUtil() {
    }

    /**
     * This is a clever use of the classloading mechanism in JVM to ensure that the instance
     * is initialized with only one thread (thread-safe).
     * The static inner class is not initialized immediately when the Singleton class is
     * loaded, but when getInstance is used (lazy loading).
     */
    private static class SingletonInstance {
        private static final FilePathUtil INSTANCE = new FilePathUtil();
    }

    public static FilePathUtil getInstance() {
        return SingletonInstance.INSTANCE;
    }

    /**
     * Get the Absolute saving path of the file.
     * @return : the absolute saving path in the project
     */
    public String getSavePath() {
        return absolutePathPrefix + sourcePath;
    }

    /**
     * Get the Absolute Root path of the local server.
     * We can use the method to splice the path and download the file in the server.
     * @return : the absolute root path of the local server
     */
    public String getAbsPathPrefix() {
        return absolutePathPrefix;
    }

    /**
     * Get the Extension name of the file.
     * @param fileName : fileName we want to separate
     * @return : the extension name(.pdf/ .txt/ .jpg...)
     */
    public String getExtName(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * Get a UUID name of the file, which can avoid duplication.
     * @return : a new UUID name of the file
     */
    public String setUUIDName() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    /**
     * Get the relative path in the project, which can be used for downloading
     * @param fileName : the file name with UUID and extension
     * @return : the relative path in the project(\\src\\main\\resources\\static\\upload\\)
     */
    public String getSourcePath(String fileName) {
        return sourcePath + fileName;
    }
}
