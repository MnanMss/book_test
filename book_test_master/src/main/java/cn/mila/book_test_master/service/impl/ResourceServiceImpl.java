package cn.mila.book_test_master.service.impl;

import cn.mila.book_test_master.dto.resp.ImgVerifyCodeRespDto;
import cn.mila.book_test_master.manager.cache.VerifyCodeManager;
import cn.mila.book_test_master.service.ResourceService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author mila
 * @date 2024/5/9 下午8:57
 */
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final VerifyCodeManager codeManger;


    @SneakyThrows
    @Override
    public ImgVerifyCodeRespDto getImgVerifyCode() {
        String sessionId = IdWorker.get32UUID();
        return ImgVerifyCodeRespDto.builder()
            .img(codeManger.getImgVerifyCode(sessionId))
            .sessionId(sessionId).build();
    }
}
