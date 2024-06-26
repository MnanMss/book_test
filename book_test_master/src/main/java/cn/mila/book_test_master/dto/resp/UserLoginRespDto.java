package cn.mila.book_test_master.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 用户响应 DTO
 *
 * @author mila
 * @date 2024/4/26
 */
@Data
@Builder
public class UserLoginRespDto {

    @Schema(description = "用户ID")
    private Long uid;

    @Schema(description = "用户昵称")
    private String userName;

    @Schema(description = "token")
    private String token;

}
