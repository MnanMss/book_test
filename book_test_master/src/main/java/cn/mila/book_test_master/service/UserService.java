package cn.mila.book_test_master.service;

import cn.mila.book_test_master.dto.req.UserLoginReqDto;
import cn.mila.book_test_master.dto.req.UserRegisterReqDto;
import cn.mila.book_test_master.dto.resp.UserLoginRespDto;

/**
 * @author mila
 * @date 2024/4/26
 */
public interface UserService {

    void register(UserRegisterReqDto userLoginReqDto);

    UserLoginRespDto login(UserLoginReqDto userLoginReqDto);
}
