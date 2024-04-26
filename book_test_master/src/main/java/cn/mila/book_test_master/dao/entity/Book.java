package cn.mila.book_test_master.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 图书信息
 * </p>
 *
 * @author mila
 * @date 2024/04/25
 */
@TableName("book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT) // 插入时自动填充
    private LocalDateTime createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE) // 更新时自动填充
    private LocalDateTime updatedTime;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 借阅时间
     */
    private LocalDateTime borrowTime;

    /**
     * 归还时间
     */
    private LocalDateTime returnTime;

    /**
     * 借阅状态;0表示为借阅，1表示已借阅
     */
    private Integer borrowStatus;

    /**
     * 借阅者名称
     */

    private String borrowerName;

    /**
     * 借阅者ID
     */
    private Long borrowerId;

    @TableField(exist = false)
    private String borrowStatusName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public LocalDateTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(LocalDateTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(Integer borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", createdTime=" + createdTime +
            ", updatedTime=" + updatedTime +
            ", bookName=" + bookName +
            ", borrowTime=" + borrowTime +
            ", returnTime=" + returnTime +
            ", borrowStatus=" + borrowStatus +
            ", borrowerName=" + borrowerName +
            ", borrowerId=" + borrowerId +
            "}";
    }

    public String getBorrowStatusName() {
        return borrowStatusName;
    }

    public void setBorrowStatusName(String borrowStatusName) {
        this.borrowStatusName = borrowStatusName;
    }
}
