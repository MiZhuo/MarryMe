package site.mizhuo.marry.friends.controller;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/")
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    /**
     * 添加亲友分组
     * @param groupName 分组名称
     * @return void
     */
    @ApiOperation("添加亲友分组")
    @PostMapping("/group")
    public CommonResult<?> addFriendGroup(@RequestParam("groupName") String groupName){
        friendsService.saveFriendGroup(groupName);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_001);
    }

    /**
     * 获取当前登陆用户的亲友分组
     * @return 分组列表
     */
    @ApiOperation("获取亲友分组")
    @GetMapping("/group")
    public CommonResult<List<FriendGroup>> getGroupList(){
        List<FriendGroup> res = friendsService.queryFriendsGroups();
        if(ArrayUtil.isEmpty(res)){
            return CommonResult.failed(MessageConstant.ERROR_MESSAGE_002);
        }
        return CommonResult.success(res,MessageConstant.SUCCESS_MESSAGE_002);
    }

    /**
     * 更新分组名称
     * @return void
     */
    @ApiOperation("更新分组名称")
    @PutMapping("/group")
    public CommonResult<?> updateFriendGroup(@RequestParam("groupId") Long groupId,@RequestParam("groupName") String groupName){
        friendsService.updateFriendGroup(groupId,groupName,1);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_UPDATE);
    }

    /**
     * 删除分组
     * @return void
     */
    @ApiOperation("删除分组")
    @DeleteMapping("/group/{id}")
    public CommonResult<?> deleteFriendGroup(@PathVariable("id") Long groupId){
        friendsService.updateFriendGroup(groupId,null,0);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_DELETE);
    }

    /**
     * 添加亲友信息
     * @param friend 亲友信息
     * @return void
     */
    @ApiOperation("添加亲友信息")
    @PostMapping("/friend")
    public CommonResult<?> addFriend(FriendInfo friend){
        friendsService.addFriend(friend);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_003);
    }

    /**
     * 根据分组ID获取亲友列表
     * @param id 分组ID
     * @return 亲友列表
     */
    @ApiOperation("根据分组ID获取亲友列表")
    @GetMapping("/search/{id}/{keyword}")
    public CommonResult<Map<String,Object>> getFriendsList(@PathVariable Long id,@PathVariable String keyword){
        Map<String, Object> res = friendsService.queryFriendsList(id,keyword);
        return CommonResult.success(res,MessageConstant.SUCCESS_MESSAGE_004);
    }

    /**
     * 根据亲友ID获取亲友信息
     * @param id 亲友ID
     * @return 亲友信息
     */
    @ApiOperation("根据亲友ID获取亲友信息")
    @GetMapping("/friend/{id}")
    public CommonResult<FriendInfo> getFriendInfo(@PathVariable Long id){
        FriendInfo friend = friendsService.queryFriendInfoById(id);
        return CommonResult.success(friend,MessageConstant.SUCCESS_MESSAGE_005);
    }

    /**
     * 更新亲友信息
     * @param friend 亲友信息
     * @return void
     */
    @ApiOperation("更新亲友信息")
    @PutMapping("/friend")
    public CommonResult<FriendInfo> updateFriendInfo(FriendInfo friend){
        friendsService.updateFriendInfo(friend);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_UPDATE);
    }

    /**
     * 删除亲友
     * @param id 亲友ID
     * @return void
     */
    @ApiOperation("删除亲友")
    @DeleteMapping("/friend/{id}")
    public CommonResult<FriendInfo> deleteFriend(@PathVariable Long id){
        friendsService.deleteFriend(id);
        return CommonResult.success(MessageConstant.SUCCESS_MESSAGE_DELETE);
    }

}
