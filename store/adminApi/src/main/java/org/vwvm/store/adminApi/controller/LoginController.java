package org.vwvm.store.adminApi.controller;

import com.liuvei.common.SysFun;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.vwvm.store.beans.adminBean.Member;
import org.vwvm.store.commons.vo.UIConst;
import org.vwvm.store.services.adminService.IMemberService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;


@Api(value = "登录的控制器", tags = "登录接口")
@Controller
@CrossOrigin
@RequestMapping(UIConst.AREAPATH)
public class LoginController extends BaseController {

    @Resource
    private IMemberService memberService;

    @RequestMapping("/login")
    protected ModelAndView loginView(HttpServletRequest request, HttpServletResponse response) {

        handleBase(request, response);
        ModelAndView modelAndView = getMView("login");
        modelAndView.addObject("redirectUrl", request.getParameter("redirectUrl"));
        modelAndView.setViewName("login");
        modelAndView.addObject("message", "Hello World, Hello Kitty");

        return modelAndView;

    }

    @RequestMapping("/loginDeal")
    public ModelAndView loginDeal(HttpServletRequest request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView modelAndView = getMView("login");
        HttpSession session = request.getSession();
        String redirectUrl = request.getParameter("redirectUrl");

        if (!SysFun.isNullOrEmpty(redirectUrl)) {
            redirectUrl = java.net.URLDecoder.decode(redirectUrl, StandardCharsets.UTF_8);
            request.setAttribute("redirectUrl", redirectUrl);
        }


        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = SysFun.md5(password);
        String validateCode = request.getParameter("validateCode");
        request.setAttribute("username", username);
        String vMsg = "";
        if (SysFun.isNullOrEmpty(username)) {
            vMsg += "账号不能为空";
        }
        if (SysFun.isNullOrEmpty(password)) {
            vMsg += "密码不能为空";
        }
        if (SysFun.isNullOrEmpty(validateCode)) {
            vMsg += "验证码不能为空";
        }
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            modelAndView.setViewName("login");
            return modelAndView;
        }


        if (!validateCode.equals(session.getAttribute(UIConst.BG_VALIDATE_CODE_KEY))) {
            vMsg += "验证码不正确";
        }
        //如果验证失败，期将失败内容放到作用城变量，并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        Member bean = memberService.loadByName(username);
        System.out.println(bean);
        if (bean == null) {
            request.setAttribute("msg", "账号不存在");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        if (!bean.getUserPass().equals(password)) {
            System.out.println(password);
            request.setAttribute("msg", "密码错误");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        System.out.println("登录成功。");
        //皇录成功后。将当故叠录用户放到session.
        //其他负面。根撤seion是否存在来刚断是否已经理录。
        request.getSession().setAttribute(UIConst.BG_LOGINUSER_KEY, bean);
        //登录城功后。就将是否曾理员的标识保存在ession
        if (bean.getUserName().equalsIgnoreCase("admin")) {
            request.getSession().setAttribute(UIConst.BG_ISADMIN_KEY, true);
        }

        //登录成功靜。如采顯转页面不为空，關重定向判購转页面
        if (!SysFun.isNullOrEmpty(redirectUrl)) {
            modelAndView.setViewName("redirect:" + redirectUrl);
            return modelAndView;
        }

        //之后。转发到main
        //mView.setVHewName (getDispatcherPath(Wain)://相些于请求转发出。ur1不会变更
        modelAndView.setViewName("main");//相当于响应重定向。ur1会变更
        return modelAndView;
    }

    @RequestMapping("/login1")
    public String loginView1() {

        return "Member_list";

    }

    @RequestMapping("/login2")
    protected ModelAndView loginView2(HttpServletRequest request, HttpServletResponse response) {

        handleBase(request, response);
        ModelAndView modelAndView = getMView("login");
        modelAndView.addObject("redirectUrl", request.getParameter("redirectUrl"));
        modelAndView.setViewName("Member_list");
        modelAndView.addObject("message", "Hello World, Hello Kitty");

        return modelAndView;

    }

}
