package cn.mila.book_test_master.core.auth;

import cn.mila.book_test_master.core.common.constant.ErrorCodeEnum;
import cn.mila.book_test_master.core.common.exception.BusinessException;
import cn.mila.book_test_master.core.constant.SystemConfigConsts;
import cn.mila.book_test_master.core.util.JwtUtils;
import cn.mila.book_test_master.dto.UserDto;
import cn.mila.book_test_master.manager.cache.UserCacheManager;
import org.springframework.util.StringUtils;

import java.util.Objects;

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
     * @param requestUri 请求的URI    用于某些授权时排除特定URI
     * @throws BusinessException 认证失败则抛出业务异常
     */
    void auth(String token, String requestUri) throws BusinessException;

    /**
     * 统一账号认证授权
     *
     * @param jwtUtils         jwt工具
     * @param userCacheManager 用户缓存管理对象
     * @param token            token
     * @return 用户id
     */
    default Long authSSO(JwtUtils jwtUtils, UserCacheManager userCacheManager,
        String token) {
        if (!StringUtils.hasText(token)) {
            // token 为空
            // 用户登录已过期
            throw new BusinessException(ErrorCodeEnum.USER_LOGIN_EXPIRED);
        }
        Long userId = jwtUtils.parseToken(token, SystemConfigConsts.BOOK_FRONT_KEY);
        if (Objects.isNull(userId)) {
            // token 解析失败
            throw new BusinessException(ErrorCodeEnum.USER_LOGIN_EXPIRED);
        }
        UserDto user = userCacheManager.getUser(userId);
        if (Objects.isNull(user)) {
            // 用户不存在
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_NOT_FOUND);
        }
        // 设置当前线程 UserId
        UserHolder.setUserId(userId);
        // 返回userId
        return userId;
    }
}
