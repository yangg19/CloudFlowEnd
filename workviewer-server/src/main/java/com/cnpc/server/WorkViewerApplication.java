package com.cnpc.server;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cnpc.server.mapper")
public class WorkViewerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkViewerApplication.class,args);
    }
}
