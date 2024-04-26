package cn.mila.book_test_master.core.constant;

/**
 * API 路由常量
 *
 * @author mila
 * @date 2024/4/25
 */
public class ApiRouterConsts {

    private ApiRouterConsts() {
        throw new IllegalStateException("constant class");
    }

    /**
     * API请求路径前缀
     */
    public static final String API_URL_PREFIX = "/api";

    /**
     * 图书操作路径前缀 /api/book
     */
    public static final String API_BOOK_URL_PREFIX = API_URL_PREFIX + "/book";

    /**
     * 授权操作前缀 /api/user
     */
    public static final String API_USER_URL_PREFIX = API_URL_PREFIX + "/user";
}
