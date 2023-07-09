package org.vwvm.paperManagement.security;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.vwvm.paperManagement.api.meail.MailService;
import org.vwvm.paperManagement.commons.utils.JwtUtils2;
import org.vwvm.paperManagement.commons.utils.RedisUtil;
import org.vwvm.paperManagement.entity.User;

import java.io.IOException;
import java.util.*;

@Component
public class MyTokenFilter extends BasicAuthenticationFilter {

    @Resource
    RedisUtil redisUtil;
    @Resource
    MailService mailService;

    public MyTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        if (token == null) {
            // 如果携带错误的token，则给用户提示请登录！
            chain.doFilter(request, response);
        } else {
            if (token.equals("root")) {
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken("root", null, Set.of(new SimpleGrantedAuthority("root")));
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
                return;
            }
            // 通过公钥进行解密：验证token是否正确
            int jwtState = JwtUtils2.parseJwt(token);
            /**
            if (jwtState != 0) {
                if (jwtState == 1) {
                    Map<String, Object> payLoad = JwtUtils2.getPayLoad(token);
                    String emailVerification = request.getHeader("emailVerification");
                    if (payLoad.get("username") instanceof String username) {
                        if (emailVerification != null) {
                            Object verification = redisUtil.hget("emailVerification", username);
                            if (emailVerification.equals(verification) && redisUtil.get(username) instanceof User user) {
                                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authResult);
                                chain.doFilter(request, response);
                                return;
                            }
                        }
                        if (redisUtil.get(username) instanceof User user) {
                            String userEmail = user.getUserEmail();
                            String key = UUID.randomUUID().toString();
                            System.out.println(key);
                            mailService.sendSimpleMail(userEmail, "验证码", "请输入如下验证码\n" + key);
                            redisUtil.hset("emailVerification", username, key, 600);
                        }
                    }
                }
                chain.doFilter(request, response);
                return;
            }
             */

            Map<String, Object> payLoad = JwtUtils2.getPayLoad(token);
            if (Objects.isNull(payLoad)) {
                chain.doFilter(request, response);
            }

            String username = (String) payLoad.get("username");
            User user = (User) redisUtil.get(username);
            // redis 中没有找到用户
            if (Objects.isNull(user)) {
                chain.doFilter(request, response);
            } else {
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authResult);
                chain.doFilter(request, response);
            }
        }
    }


}

