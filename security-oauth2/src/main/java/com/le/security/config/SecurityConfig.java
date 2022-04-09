package com.le.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: xll
 * @Date: 2022/1/14 15:38
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 方法拦截
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 用户信息
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // base in memory
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password(passwordEncoder().encode("123")).authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password(passwordEncoder().encode("123")).authorities("p2").build());
//        manager.createUser(User.withUsername("wangwu").password(passwordEncoder().encode("123")).authorities("p3").build());
//        return manager;
//    }

    // 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
//                .authorizeRequests()   // url拦截
//                    .antMatchers("/r/r1").hasAnyAuthority("p1", "p3")
//                    .antMatchers("/r/r2").hasAuthority("p2")
//                    .antMatchers("/r/**").authenticated()
//                    .anyRequest().permitAll()
//                .and()
                    .formLogin()
                        .loginPage("/login-view")
                        .loginProcessingUrl("/login")
                        .successForwardUrl("/login-success")
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        // stateLess无状态登录, if_require登录时创建, never不会创建，其它地方创建了直接引用， always总是创建一个
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login-view?logout");

    }

}
