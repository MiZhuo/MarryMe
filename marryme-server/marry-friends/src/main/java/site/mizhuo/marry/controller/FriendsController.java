package site.mizhuo.marry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.mizhuo.marry.api.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.entity.FriendGroup;
import site.mizhuo.marry.entity.FriendInfo;
import site.mizhuo.marry.service.IFriendsService;

import java.util.List;

/**
 * @author mizhuo
 */
@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    IFriendsService friendsService;

    @PostMapping("/getGroups")
    public CommonResult<List<FriendGroup>> getGroupList(){
        UserDto userDto = new UserDto();
        List<FriendGroup> res = friendsService.queryFriendsGroups(userDto.getId());
        return CommonResult.success(res);
    }

    @PostMapping("/getList")
    public CommonResult<List<FriendInfo>> getFriendsList(String friendGroupId){
        List<FriendInfo> res = friendsService.queryFriendsList(friendGroupId);
        return CommonResult.success(res);
    }
}
