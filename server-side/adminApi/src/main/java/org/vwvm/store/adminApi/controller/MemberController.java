package org.vwvm.store.adminApi.controller;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.vwvm.store.beans.adminBean.Member;
import org.vwvm.store.commons.vo.UIConst;
import org.vwvm.store.services.adminService.IMemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
@CrossOrigin
@Api(tags = "会员管理", value = "用于会员管理")
@RequestMapping(UIConst.AREAPATH)
public class MemberController extends CrudController {
    private IMemberService memberService;

    @Autowired
    public void setMemberController(IMemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 各方法的路径的Bean的前缀
     */

    public final static String BEAN_PREFIX = "/Member_";

    @Override
    @RequestMapping(BEAN_PREFIX + "list")
    protected ModelAndView listView(HttpServletRequest request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Member_list");
        List<Member> vDataList;
        // ---------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
        rowCount = memberService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = (List<Member>) memberService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------
        // 转发到列表页面
        mView.setViewName("Member_list");
        return mView;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected ModelAndView listDeal(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected ModelAndView insertView(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected ModelAndView insertDeal(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping(BEAN_PREFIX + "update")
    protected ModelAndView updateView(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("竟来");
        handleBase(request, response);
        ModelAndView mView = getMView("Member_update.html");
        // Servlet的doxxx方法中的6个标准对象(含request和response)
        //从request里获取session对象和application对象
        javax.servlet.http.HttpSession session = request.getSession();
        javax.servlet.ServletContext application = request.getServletContext();
        //取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");


        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Member bean = memberService.getById(iId);

            if (bean != null) {
                //使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值, 需要将旧值放到作用域, 页面中进行获取
                request.setAttribute("userId", bean.getUserId());
                request.setAttribute("userName", bean.getUserName());
                request.setAttribute("nickName", bean.getNickName());
                request.setAttribute("email", bean.getEmail());
                // 转友到列表
                mView.setViewName("Member_update");
                return mView;
            }
        }
        mView.setViewName("Member_update");
        return mView;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping(BEAN_PREFIX + "updateDeal")
    protected ModelAndView updateDeal(HttpServletRequest request, HttpServletResponse response) {

        handleBase(request, response);
        ModelAndView mView = getMView("Member_updateDeal");
        // *****Servlet的doxxx方法中的6个标准对象(含request和response1
        //从xequest塑获取session对象和application对象

        javax.servlet.http.HttpSession session = request.getSession();
        javax.servlet.ServletContext application = request.getServletContext();

        // 获放请求数据
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String userPass = request.getParameter(" userPass");
        String nickName = request.getParameter("nickName");
        String email = request.getParameter("email");
        // 为了在输入页面网显原来的田值, 而要将用值放到作用城, 页面中遇行获取
        request.setAttribute("userId", userId);
        request.setAttribute("userName", userName);
        request.setAttribute("nickName", nickName);
        request.setAttribute("email", email);

        //(1)服务解验证
        String vMag = "";

        if (SysFun.isNullOrEmpty(userId)) {
            vMag += "主键不能为空";
        }
        if (SysFun.isNullOrEmpty(userName)) {
            vMag += "账号不能为空。";
        }
        if (SysFun.isNullOrEmpty(nickName)) {
            vMag += "昵称不能为空。";
        }
        if (SysFun.isNullOrEmpty(email)) {
            vMag += "邮不能为空";
        }

        Long iId = SysFun.parseLong(userId);
        Member bean = memberService.getById(iId);
        if (bean == null){
            vMag += "记录不存在";
        }

        // 如果验证失败, 则将失败内容放到作用城域变量, 并转发到页而
        if (!SysFun.isNullOrEmpty(vMag)) {
            request.setAttribute("msg", vMag);
            System.out.println(vMag);
            return mView;
        }
        bean.setUserName(userName);
        if (SysFun.isNullOrEmpty(userPass)){

        }else {
            bean.setUserPass(userPass);
        }
        Date date = new Date();
        bean.setNickName(nickName);
        bean.setEmail(email);
        bean.setUpdateOn(date);
        boolean result = false;
        try {
            result = memberService.updateById(bean);
        }catch (Exception e){
            vMag += "修改失败,原因:" + e.getMessage();
        }
        if (result){
            return mView;
        }else {
            return mView;
        }
    }


    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected ModelAndView detailView(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected ModelAndView detailDeal(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected ModelAndView deleteView(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    @RequestMapping(BEAN_PREFIX + "deleteDea")
    protected ModelAndView deleteDeal(HttpServletRequest request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Member_deleteDeal");

        String id = request.getParameter("id");
        System.out.println(id);
        if (!SysFun.isNullOrEmpty(id)){
            Long aLong = SysFun.parseLong(id);

            mView.setViewName(getDispatcherPath("Go", "ok"));
            return mView;
        }
        return null;
    }

    @RequestMapping(BEAN_PREFIX + "deleteDeal")
    @ApiOperation("删除接口")
    protected String deleteDeal1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("this is test");
        ModelAndView mView = getMView("Member_deleteDeal");

        String id = request.getParameter("id");
        System.out.println(id);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write("ok");
        response.getWriter().flush();


        if (!SysFun.isNullOrEmpty(id)){
            Long aLong = SysFun.parseLong(id);

            return "ok";
        }
        return null;
    }


}