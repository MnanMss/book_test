package cn.mila.book_test_master.service.impl;

import cn.mila.book_test_master.core.common.constant.ErrorCodeEnum;
import cn.mila.book_test_master.core.common.exception.BusinessException;
import cn.mila.book_test_master.core.constant.DatabaseConsts;
import cn.mila.book_test_master.core.constant.SystemConfigConsts;
import cn.mila.book_test_master.core.util.JwtUtils;
import cn.mila.book_test_master.dao.entity.User;
import cn.mila.book_test_master.dao.mapper.UserMapper;
import cn.mila.book_test_master.dto.req.UserLoginReqDto;
import cn.mila.book_test_master.dto.req.UserRegisterReqDto;
import cn.mila.book_test_master.dto.resp.UserLoginRespDto;
import cn.mila.book_test_master.manager.cache.VerifyCodeManager;
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

    private final JwtUtils jwtUtils;

    private final VerifyCodeManager verifyCodeManager;

    /**
     * 用户注册
     *
     * @param userLoginReqDto 用户请求 DTO
     */
    @Override
    public void register(UserRegisterReqDto userLoginReqDto) {
        // 校验图形验证码是否正确
        if (!verifyCodeManager.judgeVerifyCode(userLoginReqDto.getSessionId(), userLoginReqDto.getValCode())) {
            // 图形验证码校验失败
            throw new BusinessException(ErrorCodeEnum.USER_VERIFY_CODE_ERROR);
        }
        User user = new User();
        BeanUtils.copyProperties(userLoginReqDto, user);
        Map<String, Object> map = new HashMap<>();
        map.put(DatabaseConsts.UserTable.COLUMN_USER_NAME, user.getUserName());
        List<User> users = userMapper.selectByMap(map);
        // 用户名已存在
        if (users != null && !users.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.USER_NAME_EXIST);
        }
        String password = user.getPassword();
        // 进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(password);
        userMapper.insert(user);
        // 删除验证码
        verifyCodeManager.removeImgVerifyCode(userLoginReqDto.getSessionId());
    }

    /**
     * 用户登录
     *
     * @param userLoginReqDto 用户请求 DTO
     * @return 用户返回 DTO
     */
    @Override
    public UserLoginRespDto login(UserLoginReqDto userLoginReqDto) {

        Map<String, Object> param = new HashMap<>();
        param.put(DatabaseConsts.UserTable.COLUMN_USER_NAME, userLoginReqDto.getUserName());
        List<User> users = userMapper.selectByMap(param);
        // 账号不存在
        if (users == null || users.isEmpty()) {
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_NOT_FOUND);
        }
        String password = DigestUtils.md5DigestAsHex(userLoginReqDto.getPassword().getBytes());
        if (!password.equals(users.get(0).getPassword())) {
            //密码错误
            throw new BusinessException(ErrorCodeEnum.PASSWORD_ERROR);
        }
        // 登录成功生成jwt并返回
        return UserLoginRespDto.builder()
            .uid(users.get(0).getId())
            .userName(users.get(0).getUserName())
            .token(jwtUtils.generateToken(users.get(0).getId(), SystemConfigConsts.BOOK_FRONT_KEY))
            .build();
    }
}
