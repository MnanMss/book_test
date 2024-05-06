package cn.mila.book_test_master.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "借阅者名称不能为空")
    private String borrowerName;

    @Schema(description = "借阅者id")
    private Long borrowerId;
}
