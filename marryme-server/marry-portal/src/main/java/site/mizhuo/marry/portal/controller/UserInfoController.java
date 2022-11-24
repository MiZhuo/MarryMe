package site.mizhuo.marry.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.mizhuo.marry.common.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.portal.service.IUserService;


/**
 * 用户登录注册管理Controller
 * @author mizhuo
 */
@RestController
@Api(tags = "UserInfoController", value = "用户信息管理")
@RequestMapping("/sso")
public class UserInfoController {

    @Autowired
    IUserService userService;

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @ApiOperation("根据用户名获取用户信息")
    @PostMapping("/loadByUsername")
    public UserDto loadUserByUsername(@RequestParam String username) {
        return userService.loadUserByUsername(username);
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping( "/login")
    public CommonResult<?> login(@RequestParam String username,
                              @RequestParam String password) {
        return userService.login(username, password);
    }

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation("获取用户信息")
    @PostMapping("/info")
    public CommonResult<?> info() {
        UserDto User = userService.getCurrentUser();
        return CommonResult.success(User);
    }

    /**
     * 获取验证码
     * @param telephone
     * @return
     */
    @ApiOperation("获取验证码")
    @PostMapping("/getAuthCode")
    public CommonResult<?> getAuthCode(@RequestParam String telephone) {
        String authCode = userService.generateAuthCode(telephone);
        return CommonResult.success(authCode,"获取验证码成功");
    }

}
