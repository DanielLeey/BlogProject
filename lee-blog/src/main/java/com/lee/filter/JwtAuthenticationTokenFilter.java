package com.lee.filter;

import com.alibaba.fastjson.JSON;
import com.lee.domain.ResponseResult;
import com.lee.domain.entity.LoginUserDetails;
import com.lee.enums.AppHttpCodeEnum;
import com.lee.utils.JwtUtil;
import com.lee.utils.RedisCache;
import com.lee.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 认证过滤器，过滤请求，如果是带着token需要解析，不带token放行
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取token
        String token = httpServletRequest.getHeader("token");
        //如果为空则放行
        if (Objects.isNull(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //不为空则解析
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //如果解析失败，则可能token过期 或是无效， 需要重新登录
            //过滤器中异常不会被全局异常拦截器拦截，需要特殊处理
            //返回给前端ResponseResult, 需要转为json
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN, "请重新登录");
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(responseResult));
            return;
        }
        //token有效 在redis中查找
        String userId = claims.getSubject();
        LoginUserDetails loginUserDetails = (LoginUserDetails) redisCache.getCacheObject("bloglogin:" + userId);
        if(Objects.isNull(loginUserDetails)) {
            //如果redis中过期 需要重新登录
            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN, "请重新登录");
            WebUtils.renderString(httpServletResponse, JSON.toJSONString(responseResult));
            return;
        }
        //没有过期 存入SecurityContextHolder 权限暂时为null
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
