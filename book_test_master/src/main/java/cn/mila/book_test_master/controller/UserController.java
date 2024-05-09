package cn.mila.book_test_master.controller;

import cn.mila.book_test_master.core.common.resp.RestResp;
import cn.mila.book_test_master.core.constant.ApiRouterConsts;
import cn.mila.book_test_master.dto.req.UserLoginReqDto;
import cn.mila.book_test_master.dto.req.UserRegisterReqDto;
import cn.mila.book_test_master.dto.resp.UserLoginRespDto;
import cn.mila.book_test_master.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关模块
 *
 * @author mila
 * @date 2024/4/26
 */
@RestController
@Tag(name = "UserController", description = "用户相关模块")
@RequestMapping(ApiRouterConsts.API_USER_URL_PREFIX)
@Slf4j
//@CrossOrigin("http://localhost:5173")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 用户注册接口
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public RestResp<Void> register(@RequestBody @Valid UserRegisterReqDto userRegisterReqDto) {
        log.info("用户注册：{}", userRegisterReqDto);
        userService.register(userRegisterReqDto);
        return RestResp.ok();
    }

    /**
     * 用户登录接口
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public RestResp<UserLoginRespDto> login(@RequestBody @Valid UserLoginReqDto userLoginReqDto) {
        log.info("用户登录：{}", userLoginReqDto);
        UserLoginRespDto userRespDto = userService.login(userLoginReqDto);
        return RestResp.ok(userRespDto);
    }
}
