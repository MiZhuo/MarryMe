package site.mizhuo.marry.friends.controller;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.mizhuo.marry.friends.constant.MessageConstant;
import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;
import site.mizhuo.marry.friends.service.FriendsService;
import site.mizhuo.marry.common.CommonResult;

import java.util.List;
import java.util.Map;

/**
 * @author mizhuo
 */
@RestController
@Api(tags = "FriendsController", value = "亲友管理")
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    /**
     * 添加亲友分组
     * @param groupName
     * @return
     */
    @ApiOperation("添加亲友分组")
    @PostMapping("/addFriendGroup")
    public CommonResult<?> addFriendGroup(@RequestParam("groupName") String groupName){
        friendsService.saveFriendGroup(groupName);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_001);
    }

    /**
     * 获取当前登陆用户的亲友分组
     * @return
     */
    @ApiOperation("获取亲友分组")
    @PostMapping("/getGroups")
    public CommonResult<List<FriendGroup>> getGroupList(){
        List<FriendGroup> res = friendsService.queryFriendsGroups();
        if(ArrayUtil.isEmpty(res)){
            return CommonResult.failed(MessageConstant.ERROR_MESSAGE_002);
        }
        return CommonResult.success(res,MessageConstant.SUCCESS_MESSAGE_002);
    }

    /**
     * 更新分组名称
     * @return
     */
    @ApiOperation("更新分组名称")
    @PostMapping("/updateGroupName")
    public CommonResult<?> updateFriendGroup(@RequestParam("groupId") Long groupId,@RequestParam("groupName") String groupName){
        friendsService.updateFriendGroup(groupId,groupName,1);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_UPDATE);
    }

    /**
     * 更新分组名称
     * @return
     */
    @ApiOperation("删除分组")
    @PostMapping("/deleteFriendGroup")
    public CommonResult<?> deleteFriendGroup(@RequestParam("groupId") Long groupId){
        friendsService.updateFriendGroup(groupId,null,0);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_DELETE);
    }

    /**
     * 添加亲友信息
     * @param friend
     * @return
     */
    @ApiOperation("添加亲友信息")
    @PostMapping("/addFriend")
    public CommonResult<?> addFriend(FriendInfo friend){
        friendsService.addFriend(friend);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_003);
    }

    /**
     * 根据分组ID获取亲友列表
     * @param params
     * @return
     */
    @ApiOperation("根据分组ID获取亲友列表")
    @PostMapping("/getList")
    public CommonResult<Page<FriendInfo>> getFriendsList(Map<String,Object> params){
        Page<FriendInfo> res = friendsService.queryFriendsList(params);
        return CommonResult.success(res,MessageConstant.SUCCESS_MESSAGE_004);
    }

    /**
     * 根据亲友ID获取亲友信息
     * @param id
     * @return
     */
    @ApiOperation("根据亲友ID获取亲友信息")
    @PostMapping("/getFriendInfo")
    public CommonResult<FriendInfo> getFriendInfo(@RequestParam("id") Long id){
        FriendInfo friend = friendsService.queryFriendInfoById(id);
        return CommonResult.success(friend,MessageConstant.SUCCESS_MESSAGE_005);
    }

    /**
     * 更新亲友信息
     * @param friend
     * @return
     */
    @ApiOperation("更新亲友信息")
    @PostMapping("/updateFriendInfo")
    public CommonResult<FriendInfo> updateFriendInfo(FriendInfo friend){
        friendsService.updateFriendInfo(friend);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_UPDATE);
    }

    /**
     * 删除亲友
     * @param id
     * @return
     */
    @ApiOperation("删除亲友")
    @PostMapping("/deleteFriend")
    public CommonResult<FriendInfo> deleteFriend(@RequestParam("id") Long id){
        friendsService.deleteFriend(id);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_DELETE);
    }
}
