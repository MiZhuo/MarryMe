package site.mizhuo.marry.auth.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.mizhuo.marry.domain.UserDto;

/**
 *
 * @author mizhuo
 * @date 2022/11/03
 */
@FeignClient(name = "marry-portal",path = "/sso")
public interface UserApi {

    /**
     * 根据用户名获取通用用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @PostMapping("/loadByUsername")
    UserDto loadUserByUsername(@RequestParam("username") String username);
}
