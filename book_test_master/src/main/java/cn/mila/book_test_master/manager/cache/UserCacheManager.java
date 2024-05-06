package cn.mila.book_test_master.manager.cache;

import cn.mila.book_test_master.core.constant.CacheConsts;
import cn.mila.book_test_master.dao.entity.User;
import cn.mila.book_test_master.dao.mapper.UserMapper;
import cn.mila.book_test_master.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户缓存 管理类
 *
 * @author mila
 * @date 2024/5/5 下午9:55
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UserCacheManager {

    private final UserMapper userMapper;

    @Cacheable(cacheManager = CacheConsts.CAFFEINE_CACHE_MANAGER,
        value = CacheConsts.USER_CACHE_NAME)
    public UserDto getUser(Long userId) {
        log.info("缓存查找失败，获取用户 {}", userId);
        User user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            return null;
        }
        return UserDto.builder()
            .userId(userId)
            .build();
    }

}
