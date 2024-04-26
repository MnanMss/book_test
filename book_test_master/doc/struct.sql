DROP TABLE IF EXISTS user;
CREATE TABLE user(
                     `id` INT AUTO_INCREMENT COMMENT '主键' ,
                     `created_time` DATETIME NOT NULL  COMMENT '创建时间' ,
                     `user_name` VARCHAR(255) NOT NULL  COMMENT '用户名' ,
                     `password` varchar(50) NOT NULL  COMMENT '密码' ,
                     PRIMARY KEY (ID)
)  COMMENT = '用户';
CREATE UNIQUE INDEX  unique_user_name_idx  on user(user_name);

DROP TABLE IF EXISTS book;
CREATE TABLE book(
                     `id` bigint(20) NOT NULL  COMMENT '主键' ,
                     `created_time` DATETIME  DEFAULT NULL COMMENT '创建时间' ,
                     `updated_time` DATETIME  DEFAULT NULL COMMENT '更新时间' ,
                     `book_name` VARCHAR(255) NOT NULL  COMMENT '图书名称' ,
                     `borrow_time` DATETIME  DEFAULT NULL COMMENT '借阅时间' ,
                     `return_time` DATETIME  DEFAULT NULL COMMENT '归还时间' ,
                     `borrow_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '借阅状态;0表示为借阅，1表示已借阅' ,
                     `borrower_name` VARCHAR(255)   COMMENT '借阅者名称' ,
                     `borrower_id` bigint(20)   COMMENT '借阅者ID' ,
                     PRIMARY KEY (ID)
)  COMMENT = '图书信息';
