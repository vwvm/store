package org.vwvm.store.adminApi.controller;

import org.springframework.web.servlet.ModelAndView;
import org.vwvm.store.commons.vo.UIConst;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class BaseController {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseController() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println(this);

        // 根据areaName处理areaPath，并放在request的作用域

    }

    private String areaName = UIConst.AREANAME;
    private String areaPath;

    public String Page_Prefix = UIConst.VIEWNAME;
    public String Page_Suffix = "";
    public String Url_Format = Page_Prefix + "/" + "%s" + Page_Suffix;

    /**
     * 【BaseControll类的处理函数】：具体子类必须调用
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    protected ModelAndView handleBase(HttpServletRequest request, HttpServletResponse response) {
        dealAreaPath(request); // 处理 area相关变量
        ModelAndView mView = getMView(); // 生成空的MView

        return mView;
    }

    /**
     * 【BaseControll类的处理函数】：具体子类必须调用
     *
     * @param request
     * @return
     * @throws Exception
     */
    protected ModelAndView handleBase(HttpServletRequest request) {

        return handleBase(request, null);
    }

    /**
     * 【处理areaPath】
     *
     * @param request
     * @return
     */
    public String dealAreaPath(HttpServletRequest request) {

        if (request.getAttribute("areaName") == null) {
            areaPath = request.getServletContext().getContextPath() + "/" + getAreaName();
            request.setAttribute("areaName", areaName);
            request.setAttribute("areaPath", areaPath);
            request.setAttribute("viewName", UIConst.AREANAME);
            // System.out.println("areaPath=" + areaPath);
        }
        return areaPath;
    }

    /**
     * 【检测是否已登录】
     *
     * @param request
     * @param response
     * @return
     */
    protected boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {

        boolean vIsOK = false;

        if (request.getSession().getAttribute(UIConst.BG_LOGINUSER_KEY) == null) {
            // 跳转到登录页面
            // response.sendRedirect(request.getContextPath() + "/login");
            vIsOK = false;
        } else {
            vIsOK = true;
        }
        return vIsOK;

    }

    /**
     * 【检测是否管理员】
     *
     * @param request
     * @param response
     * @return
     */
    protected boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) {

        boolean vIsOK = false;

        if (request.getSession().getAttribute(UIConst.BG_ISADMIN_KEY) == null) {
            vIsOK = false;
        } else {
            vIsOK = true;
        }
        return vIsOK;

    }

    /**
     * 【获得areaName】
     *
     * @return
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 【获得 areaPath】
     *
     * @return
     */
    public String getAreaPath() {

        return areaPath;
    }

    /**
     * 【 重定向到Login】：/areaName/Login
     *
     * @return
     */
    public String getRedirectLogin() {
        String viewName = getRedirectPath("Login");

        return viewName;
    }

    /**
     * 【产生ModelAndView对象】： 重定向到Login
     *
     * @return
     */
    public ModelAndView getRedirectLoginMView() {

        ModelAndView mView = new ModelAndView();
        mView.setViewName(getRedirectLogin());
        return mView;
    }

    /**
     * 【产生含areaName的转发器路径】：/areaName/pFileName
     *
     * @param pFileName
     * @return
     */
    public String getDispatcherPath(String pFileName) {

        String viewName = String.format(Url_Format, pFileName);
        // System.out.println("getViewName:" + viewName);
        return viewName;
    }

    /**
     * 【产生含areaName的转发器路径】：/areaName/pBeanName_pOperName
     *
     * @param pBeanName
     * @param pOperName
     * @return
     */
    public String getDispatcherPath(String pBeanName, String pOperName) {
        String viewName = String.format(Url_Format, pBeanName + "_" + pOperName);
         System.out.println("getViewName2:" + viewName);
        return viewName;
    }


    /**
     * 【产生含areaName的重定向路径】：redirect:/areaName/pBeanName_pOperName
     *
     * @param pBeanName
     * @param pOperName
     * @return
     */
    public String getRedirectPath(String pBeanName, String pOperName) {
        String path = "redirect:" + "/" + getAreaName() + "/";
        String viewName = String.format(Url_Format, pBeanName + "_" + pOperName);
        path += viewName;
        return path;
    }

    /**
     * 【产生含areaName的重定向路径】：redirect:/areaName/viewName
     *
     * @param viewName
     * @return
     */
    public String getRedirectPath(String viewName) {
        String path = "redirect:" + "/" + getAreaName() + "/";
        path += viewName;
        return path;
    }

    /**
     * 【产生ModelAndView对象】： ModelAndView对象未设置ViewName视图名
     *
     * @return
     */
    public ModelAndView getMView() {

        ModelAndView mView = new ModelAndView();

        return mView;
    }

    /**
     * 【产生ModelAndView对象】： 将viewName处理成【转发器路径】，作为ModelAndView对象的ViewName视图名
     *
     * @param viewName
     * @return
     */
    public ModelAndView getMView(String viewName) {

        ModelAndView mView = new ModelAndView();

        if (viewName != null && !viewName.isEmpty()) {
            mView.setViewName(getDispatcherPath(viewName));
        }

        return mView;
    }

    /**
     * 【产生ModelAndView对象】： 将BeanName和OperName处理成【转发器路径】，作为ModelAndView对象的ViewName视图名
     *
     * @param pBeanName
     * @param pOperName
     * @return
     */
    public ModelAndView getMView(String pBeanName, String pOperName) {

        ModelAndView mView = new ModelAndView();

        mView.setViewName(getDispatcherPath(pBeanName, pOperName));
        return mView;
    }

	/**
	 * 将上传文件的相对路径转为物理路径
	 * @param request
	 * @return
	 */
	public String getDirRoot(HttpServletRequest request){
        String strDir = null;
        // strDir = request.getServletContext().getRealPath(UIConst.UP_ROOT);
        strDir = "D:/up";  // 最好保存到配置文件中
        System.out.println(strDir);
		java.io.File dir = new java.io.File(strDir);
		if (!dir.exists()){
			dir.mkdirs();
		}
		return strDir;
	}

}
