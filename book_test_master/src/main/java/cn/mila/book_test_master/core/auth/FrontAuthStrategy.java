package cn.mila.book_test_master.core.auth;

import cn.mila.book_test_master.core.common.exception.BusinessException;
import cn.mila.book_test_master.core.util.JwtUtils;
import cn.mila.book_test_master.manager.cache.UserCacheManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author mila
 * @date 2024/5/6 上午10:59
 */
@Component
@RequiredArgsConstructor
public class FrontAuthStrategy implements AuthStrategy {

    private final JwtUtils jwtUtils;

    private final UserCacheManager userCacheManager;

    @Override
    public void auth(String token, String requestUri) throws BusinessException {
        // 统一账号认证
        authSSO(jwtUtils, userCacheManager, token);
    }
}
