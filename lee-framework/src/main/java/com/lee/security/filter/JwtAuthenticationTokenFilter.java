package com.lee.security.filter;

import com.lee.security.entity.LoginUserDetails;
import com.lee.security.utils.JwtTokenUtil;
import com.lee.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 从请求头中获取token
        String authHeader = httpServletRequest.getHeader("Authorization");
        //如果请求头中带有Authorization不为空，则解析token，否则放行
        if (Objects.nonNull(authHeader) && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            //解析token中的用户名，取出redis中存的UserDetails，并校验token和redis中是否一致，token是否过期
            //1.解析token中的用户名
            String userNameFromToken = jwtTokenUtil.getUserNameFromToken(authToken);
            //2.获取redis中的UserDetails
            LoginUserDetails loginUserDetails = (LoginUserDetails) redisCache.getCacheObject("bloglogin:" + userNameFromToken);
            //3.校验token和redis中是否一致，token是否过期
            if (jwtTokenUtil.validateToken(authToken, loginUserDetails)) {
                //没有过期 存入SecurityContextHolder 权限暂时为null
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetails, null, loginUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
 /*       //如果为空则放行
        if (Objects.isNull(authHeader)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
        //不为空则解析
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(authToken);
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
    }*/
}
