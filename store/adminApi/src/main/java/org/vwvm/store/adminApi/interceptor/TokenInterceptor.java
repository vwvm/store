package org.vwvm.store.adminApi.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.vwvm.store.commons.utils.JwtUtils;
import org.vwvm.store.commons.vo.ResStatus;
import org.vwvm.store.commons.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录认证拦截器
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }

        String token = request.getHeader("token");
        if (token == null) {
            ResultVO resultVO = new ResultVO(ResStatus.NOT_LOGIN_IN, "请先登录", null);
            doResponse(response, resultVO);
            return false;
        } else {
            if (JwtUtils.parseJwt(token) == 0) {
                return true;
            }else if (JwtUtils.parseJwt(token) == 1){
                ResultVO resultVO = new ResultVO(ResStatus.LOGIN_INFORMATION_EXPIRED, "登录信息过期", null);
                doResponse(response, resultVO);
                return false;
            }
            else if (JwtUtils.parseJwt(token) == 2){
                ResultVO resultVO = new ResultVO(ResStatus.LOGIN_INFORMATION_EXPIRED, "不支持的JWT", null);
                doResponse(response, resultVO);
                return false;
            }
            else if (JwtUtils.parseJwt(token) == 3){
                ResultVO resultVO = new ResultVO(ResStatus.LOGIN_INFORMATION_EXPIRED, "JWT格式错误", null);
                doResponse(response, resultVO);
                return false;
            }
            else if (JwtUtils.parseJwt(token) == 4){
                ResultVO resultVO = new ResultVO(ResStatus.LOGIN_INFORMATION_EXPIRED, "非法请求", null);
                doResponse(response, resultVO);
                return false;
            }
            else{
                ResultVO resultVO = new ResultVO(ResStatus.TOKEN_IS_NOT_VALID, "解析异常", null);
                doResponse(response, resultVO);
                return false;
            }
        }
    }

    private void doResponse(HttpServletResponse response, ResultVO resultVO) throws IOException {


        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String s = new ObjectMapper().writeValueAsString(resultVO);
        out.print(s);
        out.flush();
        out.close();
    }
}
