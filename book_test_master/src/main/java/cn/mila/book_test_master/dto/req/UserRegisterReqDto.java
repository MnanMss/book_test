package cn.mila.book_test_master.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author mila
 * @date 2024/5/9 下午10:29
 */
@Data
public class UserRegisterReqDto {

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @Schema(description = "验证码")
    @NotBlank(message = "验证码不能为空！")
    @Pattern(regexp = "^\\d{4}$", message = "验证码格式不正确！")
    private String valCode;

    /**
     * 请求会话标识，用来标识图形验证码属于哪个会话
     */
    @Schema(description = "sessionId")
    @NotBlank
    @Length(min = 32, max = 32)
    private String sessionId;
}
