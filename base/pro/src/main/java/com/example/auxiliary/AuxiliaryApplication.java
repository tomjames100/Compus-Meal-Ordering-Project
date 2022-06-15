package com.example.auxiliary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.auxiliary.mapper")
public class AuxiliaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuxiliaryApplication.class, args);
    }

}
