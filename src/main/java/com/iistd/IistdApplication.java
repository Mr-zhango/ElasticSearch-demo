package com.iistd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
public class IistdApplication {
    //启动类
    public static void main( String[] args ){
        SpringApplication.run(IistdApplication.class, args);
    }

}
