package cn.mila.book_test_master.core.constant;

/**
 * 数据库常量
 *
 * @author mila
 * @date 2024/4/26
 */
public class DatabaseConsts {

    /**
     * 用户表
     */
    public static class UserTable {

        private UserTable() {
            throw new IllegalStateException("Constant class");
        }

        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_USER_NAME = "user_name";
    }

    /**
     * 图书表
     */
    public static class BookTable {

        private BookTable() {
            throw new IllegalStateException("Constant class");
        }

        public static final String COLUMN_BOOK_ID = "book_id";
        public static final String COLUMN_BOOK_NAME = "book_name";
        public static final String COLUMN_BOOK_BORROWER_NAME = "borrower_name";
    }
}
