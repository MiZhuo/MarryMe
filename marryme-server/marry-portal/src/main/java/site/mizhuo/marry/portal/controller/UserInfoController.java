package site.mizhuo.marry.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.mizhuo.marry.common.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.portal.service.UserService;


/**
 * 用户登录注册管理Controller
 * @author mizhuo
 */
@RestController
@Api(tags = "UserInfoController", value = "用户信息管理")
@RequestMapping("/sso")
public class UserInfoController {

    @Autowired
    UserService userService;

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @ApiOperation("根据用户名获取用户信息")
    @PostMapping("/loadByUsername")
    public UserDto loadUserByUsername(@RequestParam String username) {
        return userService.loadUserByUsername(username);
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @ApiOperation("用户登录")
    @PostMapping( "/login")
    public CommonResult<?> login(@RequestParam String username,
                              @RequestParam String password) {
        return userService.login(username, password);
    }

    /**
     * 获取验证码
     * @param telephone 手机号
     * @return 验证码
     */
    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode/{telephone}")
    public CommonResult<String> getAuthCode(@PathVariable String telephone) {
        String authCode = userService.generateAuthCode(telephone);
        return CommonResult.success(authCode,"获取验证码成功");
    }

}
