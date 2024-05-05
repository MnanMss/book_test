package cn.mila.book_test_master;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("cn.mila.book_test_master.dao.mapper")
@EnableCaching
public class BookTestMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookTestMasterApplication.class, args);
    }
}
