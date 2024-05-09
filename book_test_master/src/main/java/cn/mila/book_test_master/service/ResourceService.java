package cn.mila.book_test_master.service;

import cn.mila.book_test_master.dto.resp.ImgVerifyCodeRespDto;

/**
 * @author mila
 * @date 2024/5/9 下午8:57
 */
public interface ResourceService {

    /**
     * 获取图片验证码
     *
     * @return Base64 编码的验证码图片
     */
    ImgVerifyCodeRespDto getImgVerifyCode();
}
