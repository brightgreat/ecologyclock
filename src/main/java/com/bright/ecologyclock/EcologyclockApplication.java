package com.bright.ecologyclock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bright.ecologyclock")
public class EcologyclockApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcologyclockApplication.class, args);
    }

}
