package cn.mila.book_test_master.core.config;

import cn.mila.book_test_master.core.constant.ApiRouterConsts;
import cn.mila.book_test_master.core.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mila
 * @date 2024/5/5 下午8:26
 */
@Configuration
@RequiredArgsConstructor
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
}
