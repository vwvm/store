package org.vwvm.store.adminApi.controller;

import com.liuvei.common.RandFun;
import com.liuvei.common.ValidateCodeFun;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.vwvm.store.commons.vo.UIConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码处理
 */
@Controller
@RequestMapping(UIConst.AREAPATH)
public class ValidateCodeController2 extends BaseController {

    @RequestMapping("/ValidateCode")
    protected ModelAndView validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用基类的处理
        ModelAndView mView = super.handleBase(request, response);
        HttpSession session = request.getSession();

        //1)生成4位随机数字组成的字符串
        String strCode = RandFun.rand4Num().toString();
        //2)随机字符串放入会话
        session.setAttribute(UIConst.BG_VALIDATE_CODE_KEY, strCode);

        //3)随机字符串转为图片
        java.awt.image.BufferedImage image = ValidateCodeFun.generalImage(strCode);

        //4)BufferedImage图片，作为相应管道的输入流
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //将图像输出到Servlet输出流中。
        javax.servlet.ServletOutputStream outStream = response.getOutputStream();
        javax.imageio.ImageIO.write(image, "jpeg", outStream);

        //由于直接使用response来产生相应流，所以不需要通过视图解析器来处理
        return null;
    }
}
