package site.mizhuo.marry.auth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.mizhuo.marry.api.CommonResult;

/**
 * 全局处理Oauth2抛出的异常
 * @author mizhuo
 */
@RestControllerAdvice
public class Oauth2ExceptionHandler {
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult<?> handleOauth2(OAuth2Exception e) {
        return CommonResult.failed(e.getMessage());
    }
}
