package org.vwvm.paperManagement.api;


import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vwvm.paperManagement.commons.lang.Const;
import org.vwvm.paperManagement.commons.vo.ResultsVO;
import org.vwvm.paperManagement.commons.utils.RedisUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@RestController
@CrossOrigin
public class AuthController {

    public static String VERIFY_CODE_KEY = "verity";
    @Resource
    Producer producer;

    @Resource
    RedisUtil redisUtil;


    @GetMapping("/captcha")
    public ResultsVO captcha() throws IOException {

        // 生成随机数
        String key = UUID.randomUUID().toString();
        // 使用google 生成
        String code = producer.createText();

        // 为了测试
        // key = "aaaaa";
        // code = "11111";

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 图片写流
        ImageIO.write(image, "jpg", outputStream);

        Base64.Encoder encoder = Base64.getEncoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encodeToString(outputStream.toByteArray());

        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);

        return ResultsVO.succeed(
                MapUtil.builder()
                        .put("token", key)
                        .put("captchaImg", base64Img)
                        .build()

        );
    }

    // /**
    //  * 获取用户信息接口
    //  * @param principal
    //  * @return
    //  */
    // @GetMapping("/sys/userInfo")
    // public ResultsVO userInfo(Principal principal) {
    //
    // 	SysUser sysUser = sysUserService.getByUsername(principal.getName());
    //
    // 	return ResultsVO.succeed(MapUtil.builder()
    // 			.put("id", sysUser.getId())
    // 			.put("username", sysUser.getUsername())
    // 			.put("avatar", sysUser.getAvatar())
    // 			.put("created", sysUser.getCreated())
    // 			.map()
    // 	);
    // }


}
