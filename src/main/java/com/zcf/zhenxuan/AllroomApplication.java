package com.zcf.zhenxuan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(value="com.zcf.zhenxuan.mapper")
@SpringBootApplication
@EnableScheduling
public class AllroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllroomApplication.class, args);
        System.out.print("启动成功");
    }
}
