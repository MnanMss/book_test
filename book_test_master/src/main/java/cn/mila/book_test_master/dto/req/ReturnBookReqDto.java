package cn.mila.book_test_master.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mila
 * @date 2024/4/26
 */
@Data
public class ReturnBookReqDto {

    @Schema(description = "bookId")
    private Long id;

    @Schema(description = "借阅者名称")
    private String borrowerName;
}
