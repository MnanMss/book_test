package cn.mila.book_test_master.service.impl;

import cn.mila.book_test_master.core.auth.UserHolder;
import cn.mila.book_test_master.core.common.constant.CommonConsts;
import cn.mila.book_test_master.core.common.constant.ErrorCodeEnum;
import cn.mila.book_test_master.core.common.exception.BusinessException;
import cn.mila.book_test_master.core.common.resp.PageRespDto;
import cn.mila.book_test_master.dao.entity.Book;
import cn.mila.book_test_master.dao.mapper.BookMapper;
import cn.mila.book_test_master.dto.req.BorrowBookReqDto;
import cn.mila.book_test_master.dto.req.ReturnBookReqDto;
import cn.mila.book_test_master.dto.req.SearchReqDto;
import cn.mila.book_test_master.service.BookService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @author mila
 * @date 2024/4/26
 */
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    @Override
    public PageRespDto<Book> page(SearchReqDto reqDto) {
        // select * from where borrowerName = ? and bookName = ?
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<Book>();

        if (reqDto.getBorrowerName() != null && !reqDto.getBorrowerName().isEmpty()) {
            queryWrapper.eq(Book::getBorrowerName, reqDto.getBorrowerName());
        }

        if (reqDto.getBookName() != null && !reqDto.getBookName().isEmpty()) {
            queryWrapper.like(Book::getBookName, reqDto.getBookName());
        }

        Page<Book> page = new Page<>(reqDto.getPageNum(), reqDto.getPageSize());
        Page<Book> resPage = bookMapper.selectPage(page, queryWrapper);
        resPage.getRecords().forEach((t) -> {
            if (t.getBorrowStatus().equals(CommonConsts.BORROWED)) {
                t.setBorrowStatusName(CommonConsts.BORROWED_NAME);
            } else {
                t.setBorrowStatusName(CommonConsts.UN_BORROWED_NAME);
            }
        });
        return new PageRespDto<>(resPage.getPages(), resPage.getSize(), resPage.getTotal(), resPage.getRecords());
    }

    @Override
    public void borrowBook(BorrowBookReqDto borrowBookDto) {
        // update book set borrow_time = ?, borrow_status = ? ,borrower_name = ? , borrower_id = ?
        Book book = bookMapper.selectById(borrowBookDto.getId());
        // 检查status
        if (book.getBorrowStatus().equals(CommonConsts.BORROWED)) {
            throw new BusinessException(ErrorCodeEnum.BOOK_BORROW_ERROR);
        }
        // 校验当前操作者   不能代替借书
        if (!borrowBookDto.getBorrowerName().equals(UserHolder.getUserName())) {
            throw new BusinessException(ErrorCodeEnum.BOOK_BORROW_ERROR);
        }
        BeanUtils.copyProperties(borrowBookDto, book);
        book.setBorrowTime(LocalDateTime.now());
        book.setBorrowStatus(CommonConsts.BORROWED);
        LambdaUpdateWrapper<Book> wrapper = new LambdaUpdateWrapper<>();
        // 重置归还时间
        wrapper.set(Book::getReturnTime, null);
        wrapper.eq(Book::getId, borrowBookDto.getId());
        bookMapper.update(book, wrapper);
    }

    @Override
    public void returnBook(ReturnBookReqDto returnBookDto) {
        Book book = bookMapper.selectById(returnBookDto.getId());
        // 防止别人还书
        if (!returnBookDto.getBorrowerName().equals(UserHolder.getUserName()) ||
            !returnBookDto.getBorrowerId().equals(UserHolder.getUserId())) {
            throw new BusinessException(ErrorCodeEnum.BOOK_RETURN_ERROR);
        }
        // 检查status
        if (book.getBorrowStatus().equals(CommonConsts.UN_BORROWED)) {
            throw new BusinessException(ErrorCodeEnum.BOOK_RETURN_ERROR);
        }
        // 重置借阅者姓名
        book.setBorrowerName(null);
        book.setReturnTime(LocalDateTime.now());
        book.setBorrowStatus(CommonConsts.UN_BORROWED);
        LambdaUpdateWrapper<Book> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Book::getId, returnBookDto.getId());
        // 重置借阅者姓名
        wrapper.set(Book::getBorrowerName, null);
        // 重置借书时间
        wrapper.set(Book::getBorrowTime, null);
        bookMapper.update(book, wrapper);
    }
}
