package site.mizhuo.marry.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PostMapping("/sso/loadByUsername")
    UserDto loadUserByUsername(@RequestParam("username") String username);
}
