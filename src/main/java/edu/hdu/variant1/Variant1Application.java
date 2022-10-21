package edu.hdu.variant1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.hdu.variant1.mapper")
public class Variant1Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Variant1Application.class);
        springApplication.setAllowCircularReferences(Boolean.TRUE);
        springApplication.run(args);
    }

}
