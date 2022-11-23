package site.mizhuo.marry.portal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.mizhuo.marry.api.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.portal.domain.UserGroup;
import site.mizhuo.marry.portal.domain.UserInfo;
import site.mizhuo.marry.portal.service.IUserService;


/**
 * 用户登录注册管理Controller
 * @author mizhuo
 */
@RestController
@Api(tags = "UserInfoController", value = "用户登录注册管理")
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
     * 用户注册
     * @param username
     * @param password
     * @param telephone
     * @param authCode
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult<?> register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        userService.register(username, password, telephone, authCode);
        return CommonResult.success(null,"注册成功");
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
        UserInfo User = userService.getCurrentUser();
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

    /**
     * 修改密码
     * @param telephone
     * @param password
     * @param authCode
     * @return
     */
    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public CommonResult<?> updatePassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode) {
        userService.updatePassword(telephone,password,authCode);
        return CommonResult.success(null,"密码修改成功");
    }

}
