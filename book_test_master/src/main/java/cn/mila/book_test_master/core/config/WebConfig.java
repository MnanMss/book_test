package cn.mila.book_test_master.core.config;

import cn.mila.book_test_master.core.constant.ApiRouterConsts;
import cn.mila.book_test_master.core.interceptor.AuthInterceptor;
import cn.mila.book_test_master.core.json.JacksonObjectDateMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author mila
 * @date 2024/5/5 下午8:26
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 权限认证拦截
        registry.addInterceptor(authInterceptor)
            .addPathPatterns(ApiRouterConsts.API_USER_URL_PREFIX + "/**",
                ApiRouterConsts.API_BOOK_URL_PREFIX + "/**")
            .excludePathPatterns(ApiRouterConsts.API_USER_URL_PREFIX + "/register",
                ApiRouterConsts.API_USER_URL_PREFIX + "/login");
    }

    /**
     * 扩展消息转换器 统一时间格式
     *
     * @param converters 消息转换器容器
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");
        // 创建消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 为消息转换器设置对象转换器
        converter.setObjectMapper(new JacksonObjectDateMapper());
        // 将自己的消息转换器加入容器
        converters.add(1, converter);
        // 解决加了上述消息转换器后出现报错的问题
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(
            List.of(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.ALL));
        // 一定要将优先级设成最高
        converters.add(0, stringConverter);
    }
}
