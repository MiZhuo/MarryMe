package site.mizhuo.marry.friends.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;
import site.mizhuo.marry.friends.service.IFriendsService;
import site.mizhuo.marry.api.CommonResult;

import java.util.List;

/**
 * @author mizhuo
 */
@RestController
@Api(tags = "FriendsController", value = "亲友管理")
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    IFriendsService friendsService;

    /**
     * 获取当前登陆用户的亲友分组
     * @return
     */
    @ApiOperation("获取亲友分组")
    @PostMapping("/getGroups")
    public CommonResult<List<FriendGroup>> getGroupList(){
        List<FriendGroup> res = friendsService.queryFriendsGroups();
        return CommonResult.success(res);
    }

    /**
     * 根据分组ID获取亲友列表
     * @param groupId
     * @return
     */
    @PostMapping("/getList")
    public CommonResult<List<FriendInfo>> getFriendsList(@RequestParam("groupId") String groupId){
        List<FriendInfo> res = friendsService.queryFriendsList(groupId);
        return CommonResult.success(res);
    }
}
