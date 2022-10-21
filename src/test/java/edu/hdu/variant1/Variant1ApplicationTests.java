package edu.hdu.variant1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@SpringBootTest
@Slf4j
class Variant1ApplicationTests {

    @Test
    void isFileExists(HttpServletResponse response) throws IOException{
        System.out.println(System.getProperty("user.dir"));

    }

}
