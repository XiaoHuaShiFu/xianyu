package com.wudagezhandui.shixun.xianyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wudagezhandui.shixun.xianyu.dao")
public class XianyuApplication {

    public static void main(String[] args) {
        SpringApplication.run(XianyuApplication.class, args);
    }

}
