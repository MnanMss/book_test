package cn.mila.book_test_master.service;

import cn.mila.book_test_master.dto.req.UserReqDto;
import cn.mila.book_test_master.dto.resp.UserLoginRespDto;

/**
 * @author mila
 * @date 2024/4/26
 */
public interface UserService {

    void register(UserReqDto userReqDto);

    UserLoginRespDto login(UserReqDto userReqDto);
}
