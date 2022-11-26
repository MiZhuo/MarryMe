package site.mizhuo.marry.friends.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import site.mizhuo.marry.common.CommonResult;
import site.mizhuo.marry.domain.UserDto;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mizhuo
 * @date 2022/11/23
 */
@FeignClient(name = "marry-portal",path = "/sso")
public interface UserApi {

}
