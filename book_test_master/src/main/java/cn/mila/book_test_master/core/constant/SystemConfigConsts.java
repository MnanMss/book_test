package cn.mila.book_test_master.core.constant;

/**
 * @author mila
 * @date 2024/5/6 上午10:49
 */
public class SystemConfigConsts {

    private SystemConfigConsts() {
        throw new IllegalStateException(CONST_INSTANCE_EXCEPTION_MSG);
    }

    /**
     * Http 请求认证 Header
     */
    public static final String HTTP_AUTH_HEADER_NAME = "Authorization";

    /**
     * JWT 过期时间 两小时
     */
    public static final Long JWT_EXPIRE_TIME = Integer.toUnsignedLong(2 * 60 * 60 * 1000);

    /**
     * 前台系统标识
     */
    public static final String BOOK_FRONT_KEY = "front";

    /**
     * 常量类实例化异常信息
     */
    public static final String CONST_INSTANCE_EXCEPTION_MSG = "Constant class";
}
