package cn.mila.book_test_master.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 查询信息，请求DTO
 *
 * @author mila
 * @date 2024/4/26
 */
@Data
public class SearchReqDto {

    @Schema(description = "借阅人")
    private String borrowerName;
    @Schema(description = "图书名称")
    private String bookName;

    /**
     * 请求页码，默认第 1 页
     */
    @Schema(description = "请求页码，默认第 1 页")
    private int pageNum = 1;

    /**
     * 每页大小，默认每页 10 条
     */
    @Schema(description = "每页大小，默认每页 10 条")
    private int pageSize = 10;
}
