package cn.mila.book_test_master.dto.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户登录、组车，请求DTO
 *
 * @author mila
 * @date 2022/4/26
 */
@Data
public class UserReqDto {
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "密码")
    private String password;
}
