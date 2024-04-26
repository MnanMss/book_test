package cn.mila.book_test_master.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author mila
 * @date 2024/4/26
 */
@Data
public class BorrowBookDto {

    @Schema(description = "bookId")
    private Long id;

    /**
     * 借阅者名称
     */
    @Schema(description = "借阅者名称")
    private String borrowerName;

    /**
     * 借阅者ID
     */
    @Schema(description = "借阅者ID")
    private Long borrowerId;

    /**
     * 借阅状态;0表示为借阅，1表示已借阅
     */
    private Integer borrowStatus;
}
