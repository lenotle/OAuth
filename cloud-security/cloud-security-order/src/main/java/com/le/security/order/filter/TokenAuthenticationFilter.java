package com.le.security.order.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.le.security.order.common.EncryptUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: xll
 * @Date: 2022/1/19 16:50
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    /**
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("json-token");
        if (StringUtils.isBlank(header)) {
            return;
        }
        // 1.解析token
        String json = EncryptUtil.decodeUTF8StringBase64(header);
        JSONObject token = JSON.parseObject(json);

        String username = token.getString("principal");
        JSONArray authoritiesArray = token.getJSONArray("authorities");
        String[] authorities = authoritiesArray.toArray(new String[authoritiesArray.size()]);

        // 2. 填充Authentication
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.createAuthorityList(authorities));
        authenticationToken.setDetails(
                new WebAuthenticationDetailsSource()
                        .buildDetails(request)
        );

        // 3. 保存到SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
