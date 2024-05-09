package cn.mila.book_test_master.manager.cache;

import cn.mila.book_test_master.core.common.util.ImgVerifyCodeUtils;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * 验证码缓存
 *
 * @author mila
 * @date 2024/5/9 下午8:37
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class VerifyCodeManager {

    private final Cache<String, String> imgVerifyCodeCache;


    public String getImgVerifyCode(String sessionId) throws IOException {

        String verifyCode = ImgVerifyCodeUtils.getRandomVerifyCode(4);
        String img = ImgVerifyCodeUtils.genVerifyCodeImg(verifyCode);
        imgVerifyCodeCache.put(sessionId, verifyCode);
        return img;
//        return imgVerifyCodeCache.get(sessionId, (s) -> {
//            String verifyCode = ImgVerifyCodeUtils.getRandomVerifyCode(4);
//            try {
//                log.info("获取sessionId：{}，对应验证码并存入缓存", sessionId);
//                return ImgVerifyCodeUtils.genVerifyCodeImg(verifyCode);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }

    public boolean judgeVerifyCode(String sessionId, String verifyCode) {
        log.info("尝试获取code:{}", imgVerifyCodeCache.getIfPresent(sessionId));
        return Objects.equals(verifyCode, imgVerifyCodeCache.getIfPresent(sessionId));
    }

    public void removeImgVerifyCode(String sessionId) {
        imgVerifyCodeCache.invalidate(sessionId);
    }
}

