package site.mizhuo.marryfriends.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.mizhuo.marrycommon.api.CommonResult;
import site.mizhuo.marryfriends.service.IFriendsService;

/**
 * @author mizhuo
 */
@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    IFriendsService friendsService;

    @GetMapping("/getFriendsList")
    public CommonResult<?> getFriendsList(){

        return CommonResult.success(null);
    }
}
