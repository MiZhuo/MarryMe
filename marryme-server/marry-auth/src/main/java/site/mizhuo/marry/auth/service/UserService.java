package site.mizhuo.marry.auth.service;

import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import site.mizhuo.marry.domain.UserDto;

/**
 *
 * @author mizhuo
 * @date 2022/11/03
 */
@FeignClient("marry-portal")
public interface UserService {

    /**
     * 根据用户名获取通用用户信息
     * @param username
     * @return
     */
    @GetMapping("/sso/loadByUsername")
    UserDto loadUserByUsername(@Param String username);
}
