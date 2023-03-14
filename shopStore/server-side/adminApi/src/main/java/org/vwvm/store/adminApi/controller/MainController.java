package org.vwvm.store.adminApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.vwvm.store.commons.vo.UIConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
@RequestMapping(UIConst.AREAPATH)
public class MainController extends BaseController {


    public MainController() {
        super();
    }

    /**
     * 显示主页面
     *
     * @param request
     * @param response
     * @return
     */


    @RequestMapping("/Main")
    protected ModelAndView mainView(HttpServletRequest

                                            request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Main");
        return mView;
    }

    /**
     * 显示欢迎页
     *
     * @param request
     * @param response
     * @return
     */


    @RequestMapping("/Main_welcome")
    protected ModelAndView welcomeView(HttpServletRequest request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView modelAndView = getMView("login");
        modelAndView.setViewName("welcome");
        return modelAndView;
    }
}