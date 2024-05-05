package cn.mila.book_test_master.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyBatisPlusFillHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 插入时自动填充
        strictFillStrategy(metaObject, "createdTime", LocalDateTime::now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时自动填充
        strictFillStrategy(metaObject, "updatedTime", LocalDateTime::now);
    }
}
