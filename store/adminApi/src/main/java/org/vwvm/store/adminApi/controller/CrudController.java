package org.vwvm.store.adminApi.controller;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract class CrudController extends BaseController {


    public CrudController() {
        super();
    }

    /**
     * doCrud()是核心方法，其他是标准抽象方法——由子类重写
     * @param request
     * @param response
     * @return
     */
    protected ModelAndView doCrud(HttpServletRequest request, HttpServletResponse response){
        //调用基类的处理
        ModelAndView mView = super.handleBase(request,response);

        //取得操作类型
        String oper = request.getParameter("oper");
        if (oper == null){
            oper = "";
        }else {
            oper = oper.trim().toLowerCase();
        }
        // 根据不同的操作类型，调用不同的处理方法
        switch (oper) {
            case "list":
                mView = listView(request, response); // 列表页面
                break;
            case "listdeal":
                mView = listDeal(request, response); // 列表处理
                break;
            case "insert":
                mView = insertView(request, response); // 添加页面
                break;
            case "insertdeal":
                mView = insertDeal(request, response); // 添加处理
                break;
            case "update":
                mView = updateView(request, response); // 修改页面
                break;
            case "updatedeal":
                mView = updateDeal(request, response); // 修改处理
                break;
            case "detail":
                mView = detailView(request, response); // 详情页面
                break;
            case "detaildeal":
                mView = detailDeal(request, response); // 详情处理
                break;
            case "delete":
                mView = deleteView(request, response); // 删除页面
                break;
            case "deletedeal":
                mView = deleteDeal(request, response); // 删除处理
                break;
            default:
                mView = listView(request, response); // 列表页面：默认
                break;
        }
        return mView;
    }


    protected abstract ModelAndView listView(HttpServletRequest request, HttpServletResponse response);
    protected abstract ModelAndView listDeal(HttpServletRequest request,HttpServletResponse response);
    protected abstract ModelAndView insertView(HttpServletRequest request,HttpServletResponse response);
    protected abstract ModelAndView insertDeal(HttpServletRequest request,HttpServletResponse response);
    protected abstract ModelAndView updateView(HttpServletRequest request,HttpServletResponse response);
    protected abstract ModelAndView updateDeal(HttpServletRequest request,HttpServletResponse response);
    protected abstract ModelAndView detailView(HttpServletRequest request,HttpServletResponse response);
    protected abstract ModelAndView detailDeal(HttpServletRequest request,HttpServletResponse response);
    protected abstract ModelAndView deleteView(HttpServletRequest request,HttpServletResponse response);
    protected abstract ModelAndView deleteDeal(HttpServletRequest request,HttpServletResponse response);



}
