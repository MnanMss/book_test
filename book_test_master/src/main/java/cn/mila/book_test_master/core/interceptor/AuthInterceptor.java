package cn.mila.book_test_master.core.interceptor;

import cn.mila.book_test_master.core.auth.AuthStrategy;
import cn.mila.book_test_master.core.auth.UserHolder;
import cn.mila.book_test_master.core.common.exception.BusinessException;
import cn.mila.book_test_master.core.common.resp.RestResp;
import cn.mila.book_test_master.core.constant.ApiRouterConsts;
import cn.mila.book_test_master.core.constant.SystemConfigConsts;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 认证拦截器
 *
 * @author mila
 * @date 2024/5/5 下午9:46
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private final Map<String, AuthStrategy> authStrategy;

    private final ObjectMapper objectMapper;

    /**
     * 执行前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        // 获取JWT
        String token = request.getHeader(SystemConfigConsts.HTTP_AUTH_HEADER_NAME);
        // 获取请求的URI
        String requestUri = request.getRequestURI();

        // 根据请求，得到对应的认证策略
        // 排除前缀uri
        String subUri = requestUri.substring(ApiRouterConsts.API_URL_PREFIX.length() + 1);
        // 截取0到第一个"/"位置的字符串  获取系统名称
        String systemName = subUri.substring(0, subUri.indexOf("/"));
        // 生成策略名称
        String authStrategyName = String.format("%sAuthStrategy", systemName);

        try {
            log.info("开始权限认证：{}", token);
            authStrategy.get(authStrategyName).auth(token, requestUri);
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } catch (BusinessException e) {
            // 认证失败
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(
                objectMapper.writeValueAsString(RestResp.fail(e.getErrorCodeEnum()))
            );
            return false;
        }
    }

    /**
     * handler执行后调用  出现异常不调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 完全处理完请求后调用，出现异常照常调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        // 清理当前线程保存的用户数据
        UserHolder.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
