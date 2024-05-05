package cn.mila.book_test_master.core.constant;

/**
 * @author mila
 * @date 2024/5/5 下午10:01
 */
public class CacheConsts {

    /**
     * caffeine缓存管理器
     */
    public static final String CAFFEINE_CACHE_MANAGER = "caffeineCacheManager";

    /**
     * 用户缓存
     */
    public static final String USER_CACHE_NAME = "userCache";

    public enum CacheEnum {
        USER_CACHE(USER_CACHE_NAME, 60 * 60, 500);
        /**
         * 缓存的名字
         */
        private String name;
        /**
         * 失效时间（秒） 0--远不失效
         */
        private int ttl;
        /**
         * 最大容量
         */
        private int maxSize;

        CacheEnum(String userCacheName, int ttl, int maxSize) {
            this.name = userCacheName;
            this.ttl = ttl;
            this.maxSize = maxSize;
        }

        public String getName() {
            return name;
        }

        public int getTtl() {
            return ttl;
        }

        public int getMaxSize() {
            return maxSize;
        }
    }
}
