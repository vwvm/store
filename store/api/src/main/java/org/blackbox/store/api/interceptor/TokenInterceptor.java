package org.blackbox.store.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.blackbox.store.commons.utils.JwtUtils;
import org.blackbox.store.commons.vo.ResStatus;
import org.blackbox.store.commons.vo.ResultVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {

        String token = request.getParameter("token");
        if (token == null) {
            ResultVO resultVO = new ResultVO(ResStatus.NO, "请先登录", null);
            doResponse(response, resultVO);
            return false;
        } else {
            if (JwtUtils.parseJwt(token)) {
                return true;
            }else {
                ResultVO resultVO = new ResultVO(ResStatus.TokenIsNotValid, "token不合法", null);
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
