package cn.mila.book_test_master.service;

import cn.mila.book_test_master.core.common.resp.PageRespDto;
import cn.mila.book_test_master.dao.entity.Book;
import cn.mila.book_test_master.dto.req.BorrowBookReqDto;
import cn.mila.book_test_master.dto.req.ReturnBookReqDto;
import cn.mila.book_test_master.dto.req.SearchReqDto;

/**
 * @author mila
 * @date 2024/4/26
 */
public interface BookService {

    PageRespDto<Book> page(SearchReqDto reqDto);

    void borrowBook(BorrowBookReqDto borrowBookDto);

    void returnBook(ReturnBookReqDto borrowBookDto);
}
