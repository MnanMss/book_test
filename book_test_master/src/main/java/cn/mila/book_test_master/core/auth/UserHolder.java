package cn.mila.book_test_master.core.auth;

import lombok.experimental.UtilityClass;

/**
 * 用户信息 持有类
 *
 * @author mila
 * @date 2024/5/5 下午11:12
 */

/**
 * UtilityClass   生成以下内容 私有构造函数：被 @UtilityClass 注解修饰的类将会自动生成一个私有构造函数，禁止外部实例化该类。 静态工厂方法：@UtilityClass
 * 注解会为类中定义的静态方法生成一个对应的静态工厂方法， 该工厂方法在调用时会自动创建该类的实例，并将其返回。开发者可以直接通过类名调用这些静态方法，而无需手动创建类的实例。 常量字段：被 @UtilityClass
 * 注解修饰的类可以自动生成常量字段， 并且这些字段会被自动标记为 final 和 static。这些常量字段可以通过类名直接访问，而无需创建类的实例。
 */
@UtilityClass
public class UserHolder {

    /**
     * 当前线程用户ID
     */
    private static final ThreadLocal<Long> userIdTL = new ThreadLocal<>();

    public void setUserId(Long userId) {
        userIdTL.set(userId);
    }

    public Long getUserId() {
        return userIdTL.get();
    }

    public void clear() {
        userIdTL.remove();
    }
}
