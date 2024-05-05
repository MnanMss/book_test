package cn.mila.book_test_master.core.auth;

import cn.mila.book_test_master.core.common.exception.BusinessException;

/**
 * 策略模式实现认证授权
 *
 * @author mila
 * @date 2024/5/5 下午11:12
 */
public interface AuthStrategy {

    /**
     * 用户认证授权
     *
     * @param token      登录token
     * @param requestUri 请求的URI
     * @throws BusinessException 认证失败则抛出业务异常
     */
    void auth(String token, String requestUri) throws BusinessException;
}
