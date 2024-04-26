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
    OK("1", "成功"),
    FAIL("2", "失败"),
    ERROR("3", "系统异常"),
    PASSWORD_ERROR("4", "密码错误"),
    ACCOUNT_NOT_FOUND("5", "账号不存在"),
    BOOK_BORROW_ERROR("6", "图书借阅异常"),
    BOOK_RETURN_ERROR("7", "图书还书异常");

    /**
     * 错误码
     */
    private final String code;

    /**
     * 中文描述
     */
    private final String message;

}
