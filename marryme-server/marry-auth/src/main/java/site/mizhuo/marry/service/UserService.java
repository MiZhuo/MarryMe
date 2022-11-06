package site.mizhuo.marry.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.mizhuo.marry.domain.UserDto;

/**
 *
 * @author mizhuo
 * @date 2022/11/03
 */
@FeignClient("marry-portal")
public interface UserService {

    @GetMapping("/user/loadByUsername")
    UserDto loadUserByUsername(@RequestParam String username);
}
