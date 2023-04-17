package org.vwvm.paperManagement.security;

import com.google.code.kaptcha.Constants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {


        //获取当前请求
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String code = req.getParameter("verity");//从当前请求中拿到code参数
        System.out.println("!!!code=" + code);
        String verifyCode = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);//从session中获取生成的验证码字符串
        System.out.println("!!!" + Constants.KAPTCHA_SESSION_KEY + "=" + verifyCode);
        // TODO 比较验证码是否相同
        // if (StringUtils.isBlank(code) || StringUtils.isBlank(verifyCode) || !Objects.equals(code, verifyCode)) {
        //     throw new AuthenticationServiceException("验证码错误!");
        // }
        // super.additionalAuthenticationChecks(userDetails, authentication);//调用父类DaoAuthenticationProvider的方法做密码的校验
    }
}
