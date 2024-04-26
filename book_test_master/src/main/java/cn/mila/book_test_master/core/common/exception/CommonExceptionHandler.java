package cn.mila.book_test_master.core.common.exception;

import cn.mila.book_test_master.core.common.resp.RestResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 通用的异常处理器
 * @author mila
 * @date 2024/4/26
 */
@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public RestResp<Void> handlerBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return RestResp.fail(e.getErrorCodeEnum());
    }

    /**
     * 处理系统异常
     */
    @ExceptionHandler(Exception.class)
    public RestResp<Void> handlerException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResp.error();
    }
}
