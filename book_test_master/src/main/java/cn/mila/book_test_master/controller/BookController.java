package cn.mila.book_test_master.controller;

import cn.mila.book_test_master.core.common.resp.PageRespDto;
import cn.mila.book_test_master.core.common.resp.RestResp;
import cn.mila.book_test_master.core.constant.ApiRouterConsts;
import cn.mila.book_test_master.dao.entity.Book;
import cn.mila.book_test_master.dto.req.BorrowBookDto;
import cn.mila.book_test_master.dto.req.ReturnBookDto;
import cn.mila.book_test_master.dto.req.SearchReqDto;
import cn.mila.book_test_master.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 图书相关模块
 *
 * @author mila
 * @date 2024/4/26
 */

@RestController
@RequestMapping(ApiRouterConsts.API_BOOK_URL_PREFIX)
@Slf4j
@CrossOrigin("http://localhost:5173")
@Tag(name = "BookController", description = "图书相关模块")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * 分页查询
     *
     * @param reqDto 分页查询DTO
     * @return
     */
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public RestResp<PageRespDto<Book>> page(@RequestBody SearchReqDto reqDto) {
        log.info("分页查询，{}", reqDto);
        PageRespDto<Book> page = bookService.page(reqDto);
        return RestResp.ok(page);
    }

    /**
     * 借书
     *
     * @param borrowBookDto 借书信息DTO
     * @return
     */
    @PostMapping("/borrow")
    @Operation(summary = "借书")
    public RestResp<Void> borrowBook(@RequestBody BorrowBookDto borrowBookDto) {
        log.info("借书，{}", borrowBookDto);
        bookService.borrowBook(borrowBookDto);
        return RestResp.ok();
    }

    /**
     * 还书
     *
     * @param returnBookDto 还书信息DTO
     * @return
     */
    @PostMapping("/returnBook")
    @Operation(summary = "还书")
    public RestResp<Void> returnBook(@RequestBody ReturnBookDto returnBookDto) {
        log.info("还书，{}", returnBookDto);
        bookService.returnBook(returnBookDto);
        return RestResp.ok();
    }
}
