package cn.mila.book_test_master.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author mila
 * @date 2024/5/2 下午4:22
 */
@ConfigurationProperties(prefix = "book.cors")
public record CorsProperties(List<String> allowOrigins) {

}
