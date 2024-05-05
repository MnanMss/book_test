package cn.mila.book_test_master.service.impl;

import cn.mila.book_test_master.core.common.constant.CommonConsts;
import cn.mila.book_test_master.core.common.constant.ErrorCodeEnum;
import cn.mila.book_test_master.core.common.exception.BusinessException;
import cn.mila.book_test_master.core.common.resp.PageRespDto;
import cn.mila.book_test_master.dao.entity.Book;
import cn.mila.book_test_master.dao.mapper.BookMapper;
import cn.mila.book_test_master.dao.mapper.UserMapper;
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
    private final UserMapper userMapper;

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
        Book book = new Book();
        BeanUtils.copyProperties(borrowBookDto, book);
        book.setBorrowTime(LocalDateTime.now());
        if (book.getBorrowStatus().equals(CommonConsts.BORROWED)) {
            throw new BusinessException(ErrorCodeEnum.BOOK_BORROW_ERROR);
        }
        book.setBorrowStatus(CommonConsts.BORROWED);
        LambdaUpdateWrapper<Book> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Book::getId, borrowBookDto.getId());
        bookMapper.update(book, wrapper);
    }

    @Override
    public void returnBook(ReturnBookReqDto returnBookDto) {
        Book book = bookMapper.selectById(returnBookDto.getId());
        // 防止别人还书
        if (!book.getBorrowerName().equals(returnBookDto.getBorrowerName())) {
            throw new BusinessException(ErrorCodeEnum.BOOK_RETURN_ERROR);
        }
        book.setReturnTime(LocalDateTime.now());
        book.setBorrowStatus(CommonConsts.UN_BORROWED);
        LambdaUpdateWrapper<Book> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Book::getId, returnBookDto.getId());
        bookMapper.update(book, wrapper);
    }
}
