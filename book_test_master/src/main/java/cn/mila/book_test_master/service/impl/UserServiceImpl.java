package cn.mila.book_test_master.service.impl;

import cn.mila.book_test_master.core.common.constant.ErrorCodeEnum;
import cn.mila.book_test_master.core.common.exception.BusinessException;
import cn.mila.book_test_master.core.constant.DatabaseConstants;
import cn.mila.book_test_master.dao.entity.User;
import cn.mila.book_test_master.dao.mapper.UserMapper;
import cn.mila.book_test_master.dto.req.UserReqDto;
import cn.mila.book_test_master.dto.resp.UserRespDto;
import cn.mila.book_test_master.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mila
 * @date 2024/4/26
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param userReqDto 用户请求 DTO
     */
    @Override
    public void register(UserReqDto userReqDto) {
        User user = new User();
        BeanUtils.copyProperties(userReqDto, user);
        String password = user.getPassword();
        // 进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);
        userMapper.insert(user);
    }

    /**
     * 用户登录
     *
     * @param userReqDto 用户请求 DTO
     * @return 用户返回 DTO
     */
    @Override
    public UserRespDto login(UserReqDto userReqDto) {
        Map<String, Object> param = new HashMap<>();
        param.put(DatabaseConstants.UserTable.COLUMN_USER_NAME, userReqDto.getUserName());
        List<User> users = userMapper.selectByMap(param);
        // 账号不存在
        if (users == null || users.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_NOT_FOUND);
        }
        String password = DigestUtils.md5DigestAsHex(userReqDto.getPassword().getBytes());
        if (!password.equals(users.get(0).getPassword())) {
            //密码错误
            throw new BusinessException(ErrorCodeEnum.PASSWORD_ERROR);
        }
        return UserRespDto.builder()
            .uid(users.get(0).getId())
            .userName(users.get(0).getUserName())
            .build();
    }
}
