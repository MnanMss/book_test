package cn.mila.book_test_master.core.common.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * @author mila
 * @date 2024/5/9 下午7:25
 */
@UtilityClass
@Slf4j
public class ImgVerifyCodeUtils {

    /**
     * 随机产生字符串选取的字符
     */
    private final String randomChar = "0123456789";

    /**
     * 图片宽
     */
    private final int width = 100;

    /**
     * 图片高
     */
    private final int height = 38;

    private final Random random = new Random();

    /**
     * 获得字体
     *
     * @return
     */
    private Font getFont() {
        return new Font("Fixed", Font.PLAIN, 23);
    }

    public String genVerifyCodeImg(String verifierCode) throws IOException {
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 产生image对象的graphics对象，该对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        // 设置绘制的图片大小   绘制一个矩形
        g.fillRect(0, 0, width, height);
        // 设置字体颜色
        g.setColor(new Color(204, 204, 204));

        // 绘制干扰线
        // 干扰线数量
        int lineSize = 40;
        for (int i = 0; i < lineSize; i++) {
            drawLine(g);
        }

        // 绘制字符串
        drawString(g, verifierCode);
        // 释放Graphics的资源
        g.dispose();
        // 将图片转换为Base64字符串
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", stream);
        log.info("生成验证码：{}", verifierCode);
        return Base64.getEncoder().encodeToString(stream.toByteArray());
    }

    /**
     * 绘制字符串
     *
     * @param g
     * @param verfiCode
     */
    private static void drawString(Graphics g, String verfiCode) {
        for (int i = 0; i < verfiCode.length(); i++) {
            // 设置字体
            g.setFont(getFont());
            // 设置颜色为随机颜色
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            // 随机平移绘图原点的位置   设置的分别是x , y 方向上的偏移量
            g.translate(random.nextInt(3), random.nextInt(3));
            // 绘制验证码字符
            g.drawString(String.valueOf(verfiCode.charAt(i)), i * 13, 23);
        }
    }

    /**
     * 绘制干扰线
     */
    private static void drawLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = 13;
        int yl = 13;
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 产生随机的校验码
     *
     * @param num
     * @return
     */
    public String getRandomVerifyCode(int num) {
        int randNumberSize = randomChar.length();
        StringBuilder verifyCode = new StringBuilder();
        for (int i = 0; i < num; i++) {
            verifyCode.append(randomChar.charAt(random.nextInt(randNumberSize)));
        }
        return verifyCode.toString();
    }
}
