package cn.mila.book_test_master.core.constant;

/**
 * API 路由常量
 *
 * @author mila
 * @date 2024/4/25
 */
public class ApiRouterConsts {

    private ApiRouterConsts() {
        throw new IllegalStateException(SystemConfigConsts.CONST_INSTANCE_EXCEPTION_MSG);
    }

    /**
     * API请求路径前缀
     */
    public static final String API_URL_PREFIX = "/api";

    /**
     * 前台门户前缀
     */
    public static final String API_FRONT_URL_PREFIX = "/front";

    /**
     * 前台门户图书操作路径前缀 /api/front/book
     */
    public static final String API_BOOK_URL_PREFIX = API_URL_PREFIX + API_FRONT_URL_PREFIX + "/book";

    /**
     * 前台门户用户操作前缀 /api/front/user
     */
    public static final String API_USER_URL_PREFIX = API_URL_PREFIX + API_FRONT_URL_PREFIX + "/user";
}
