package com.shixun.ssjserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;

@SpringBootApplication
public class SsjserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsjserverApplication.class, args);
    }

//    @Bean
//    MultipartConfigElement multipartConfigElement()
//    {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setLocation("C:\\Users\\asus\\Desktop\\ssjserver\\src\\main\\resources");
//        return factory.createMultipartConfig();
//    }

}
