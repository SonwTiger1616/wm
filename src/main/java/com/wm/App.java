package com.wm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(scanBasePackages = {"com.wm"})
@ServletComponentScan("com.wm.interceptor.filter")
@MapperScan("com.wm.dao")
public class App {

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
