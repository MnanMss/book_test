package cn.mila.book_test_master;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.mila.book_test_master.dao.mapper")
public class BookTestMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookTestMasterApplication.class, args);
    }
}
