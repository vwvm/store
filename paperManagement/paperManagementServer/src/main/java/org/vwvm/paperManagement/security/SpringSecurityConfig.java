package org.vwvm.paperManagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableMethodSecurity()
public class SpringSecurityConfig {

    MySecurityUserService mySecurityUserService;


    @Autowired
    public SpringSecurityConfig(MySecurityUserService mySecurityUserService
    ) {
        this.mySecurityUserService = mySecurityUserService;
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * @return 覆盖默认加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 指定默认
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", MyPasswordEncoder.getInstance());
        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(MyPasswordEncoder.getInstance());
        // 自定义密码加密器
        return delegatingPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http
            , MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter
            , MyTokenFilter myTokenFilter
            , MyAuthenticationHandler myAuthenticationHandler
    ) throws

            Exception {

        // 请求接口配置
        http.formLogin(form -> form
                .loginPage("/user/no")
                .permitAll()
        );

        http.cors().and().csrf().disable()          // 跨域，//禁用csrf(防止跨站请求伪造攻击)
        ;
        // 路径配置
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/view").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/no").permitAll()
                .requestMatchers("/doc.html", "/v3/**", "/favicon.ico").permitAll()
                .requestMatchers("/student/student",
                        "/minio/download/**",
                        "/teacher/getTeacherByUserId").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/captcha").permitAll()
                // .requestMatchers("/**").hasAuthority("root")
                .anyRequest().authenticated()
        ;

        // filter 配置
        http
                .addFilterAt(myUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(myTokenFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        // 异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(myAuthenticationHandler)

        ;


        return http.build();
    }


}
