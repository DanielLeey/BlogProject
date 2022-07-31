package com.lee.security.component;

import com.lee.domain.ResponseResult;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 此类的目的是为了解决 SpringSecurity中filter抛出的类被ExceptionTranslationFilter处理
 * 无法通过全局异常处理类处理
 *
 */
@RestController
public class FilterErrorController extends BasicErrorController {
    public FilterErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        //可以通过断点调试来查看body中的信息
        HttpStatus status = getStatus(request);
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(status.value());
        responseResult.setMsg(String.valueOf(body.get("error")));
        return new ResponseEntity<>(responseResult, HttpStatus.OK);
    }
}
