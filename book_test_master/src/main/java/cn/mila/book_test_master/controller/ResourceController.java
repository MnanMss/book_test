package cn.mila.book_test_master.controller;

import cn.mila.book_test_master.core.common.resp.RestResp;
import cn.mila.book_test_master.core.constant.ApiRouterConsts;
import cn.mila.book_test_master.dto.resp.ImgVerifyCodeRespDto;
import cn.mila.book_test_master.service.ResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mila
 * @date 2024/5/9 下午8:54
 */
@RestController
@Tag(name = "ResourceController", description = "资源模块")
@RequestMapping(ApiRouterConsts.API_RESOUCES_URL_PREFIX)
@RequiredArgsConstructor
@Slf4j
public class ResourceController {

    private final ResourceService resourceService;

    @Operation(summary = "获取图片验证码")
    @GetMapping("img_verify_code")
    public RestResp<ImgVerifyCodeRespDto> getImgVerifyCode() {
        ImgVerifyCodeRespDto code = resourceService.getImgVerifyCode();
        return RestResp.ok(code);
    }
}
