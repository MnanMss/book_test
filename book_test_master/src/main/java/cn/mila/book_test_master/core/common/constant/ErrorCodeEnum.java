package cn.mila.book_test_master.core.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码枚举类。
 *
 * @author mila
 * @date 2024/4/25
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum {

    /**
     * 正确执行后的返回
     */
    OK("00000", "一切ok"),

    // 注册相关
    /**
     * 用户名已存在
     */
    USER_NAME_EXIST("A0111", "用户名已存在"),

    // 登录相关
    /**
     * 密码错误
     */
    PASSWORD_ERROR("A0210", "密码错误"),
    /**
     * 账号不存在
     */
    ACCOUNT_NOT_FOUND("A0201", "账号不存在"),

    /**
     * 用户登录已过期
     */
    USER_LOGIN_EXPIRED("A0230", "用户登录已过期"),
    // 服务相关

    /**
     * 图书借阅异常
     */
    BOOK_BORROW_ERROR("A3000", "图书借阅异常"),

    /**
     * 图书还书异常
     */
    BOOK_RETURN_ERROR("A3001", "图书还书异常"),
    // 系统相关

    /**
     * 系统异常
     */
    ERROR("B0001", "系统异常");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 中文描述
     */
    private final String message;

}
