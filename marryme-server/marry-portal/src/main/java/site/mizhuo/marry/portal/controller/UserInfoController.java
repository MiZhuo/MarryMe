package site.mizhuo.marry.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.mizhuo.marry.api.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.portal.domain.UserInfo;
import site.mizhuo.marry.portal.service.IUserService;


/**
 * 会员登录注册管理Controller
 *
 * @author macro
 * @date 2018/8/3
 */
@RestController
@Api(tags = "UserInfoController", value = "会员登录注册管理")
@RequestMapping("/sso")
public class UserInfoController {
    @Autowired
    IUserService userService;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public CommonResult<?> register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        userService.register(username, password, telephone, authCode);
        return CommonResult.success(null,"注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping( "/login")
    public CommonResult<?> login(@RequestParam String username,
                              @RequestParam String password) {
        return userService.login(username, password);
    }

    @ApiOperation("获取会员信息")
    @PostMapping("/info")
    public CommonResult<?> info() {
        UserInfo member = userService.getCurrentMember();
        return CommonResult.success(member);
    }

    @ApiOperation("获取验证码")
    @PostMapping("/getAuthCode")
    public CommonResult<?> getAuthCode(@RequestParam String telephone) {
        String authCode = userService.generateAuthCode(telephone);
        return CommonResult.success(authCode,"获取验证码成功");
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public CommonResult<?> updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode) {
        userService.updatePassword(telephone,password,authCode);
        return CommonResult.success(null,"密码修改成功");
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @PostMapping("/loadByUsername")
    public UserDto loadUserByUsername(@RequestParam String username) {
        return userService.loadUserByUsername(username);
    }
}
