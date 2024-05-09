package cn.mila.book_test_master.dto.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * @author mila
 * @date 2024/5/9 下午8:58
 */
@Data
@Builder
public class ImgVerifyCodeRespDto {

    /**
     * 当前会话ID，由后端服务器提供，用于标识验证码属于哪个会话
     */
    @Schema(description = "sessionId")
    public String sessionId;

    /**
     * Base64 编码的验证码图片
     */
    @Schema(description = "Base64 编码的验证码图片")
    public String img;
}
