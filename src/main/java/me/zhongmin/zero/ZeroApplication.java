package me.zhongmin.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import java.util.LinkedList;

//(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan(basePackages = "me.zhongmin.zero")
@EnableCaching //启用spring缓存
public class ZeroApplication {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        SpringApplication.run(ZeroApplication.class, args);
    }

}
