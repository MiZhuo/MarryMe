package site.mizhuo.marry.friends.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.exception.ApiException;
import site.mizhuo.marry.friends.constant.MessageConstant;
import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;
import site.mizhuo.marry.friends.mapper.FriendGroupMapper;
import site.mizhuo.marry.friends.mapper.FriendInfoMapper;
import site.mizhuo.marry.friends.service.FriendsService;
import site.mizhuo.marry.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author mizhuo
 */
@Slf4j
@Service
public class FriendsServiceImpl implements FriendsService {

    private static final int MAX_GROUP_COUNT = 3;

    @Autowired
    FriendInfoMapper infoMapper;

    @Autowired
    FriendGroupMapper groupMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional(rollbackFor=ApiException.class)
    public void saveFriendGroup(String groupName) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        LambdaQueryWrapper<FriendGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendGroup::getUserGroupId,userGroupId)
                .eq(FriendGroup::getStatus,1);
        Long count = groupMapper.selectCount(wrapper);
        if(count >= MAX_GROUP_COUNT){
            log.error(MessageConstant.ERROR_MESSAGE_001);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_001);
        }
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setUserGroupId(userGroupId);
        friendGroup.setFriendType(groupName);
        friendGroup.setStatus(1);
        groupMapper.insert(friendGroup);
    }

    @Override
    public List<FriendGroup> queryFriendsGroups() {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        LambdaQueryWrapper<FriendGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendGroup::getUserGroupId,userGroupId)
                .eq(FriendGroup::getStatus,1)
                .orderByAsc(FriendGroup::getId);
        return groupMapper.selectList(wrapper);
    }

    @Override
    public void updateFriendGroup(Long groupId, String groupName, int status) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setId(groupId);
        friendGroup.setUserGroupId(userGroupId);
        if(StringUtils.isNotEmpty(groupName)){
            friendGroup.setFriendType(groupName);
        }
        friendGroup.setStatus(status);
        LambdaUpdateWrapper<FriendGroup> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(FriendGroup::getId,groupId)
                .eq(FriendGroup::getUserGroupId,userGroupId);
        groupMapper.update(friendGroup,wrapper);
    }

    @Override
    public void addFriend(FriendInfo friend) {
        if(friend.getFriendGroupId() == null){
            log.error(MessageConstant.ERROR_MESSAGE_003);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_003);
        }
        friend.setCreateTime(DateUtil.date());
        friend.setUpdateTime(DateUtil.date());
        infoMapper.insert(friend);
    }

    @Override
    public Page<FriendInfo> queryFriendsList(Map<String,Object> params) {
        LambdaQueryWrapper<FriendGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendGroup::getId,params.get("friendGroupId"))
                .eq(FriendGroup::getStatus,1);
        if(!groupMapper.exists(wrapper)){
            log.error(MessageConstant.ERROR_MESSAGE_005);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_005);
        }
        Page<FriendInfo> page = new Page<>(MapUtil.getLong(params,"pageNum"),MapUtil.getLong(params,"pageSize"));
        LambdaQueryWrapper<FriendInfo> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(FriendInfo::getFriendGroupId,params.get("friendGroupId"))
                .eq(FriendInfo::getStatus,1)
                .orderByAsc(FriendInfo::getFriendName);
        return infoMapper.selectPage(page,wrapper2);
    }

    @Override
    public FriendInfo queryFriendInfoById(Long id) {
        LambdaQueryWrapper<FriendInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendInfo::getId,id)
                .eq(FriendInfo::getStatus,1)
                .orderByAsc(FriendInfo::getFriendName);
        return infoMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor=ApiException.class)
    public void updateFriendInfo(FriendInfo friend) {
        FriendInfo friendInfo = queryFriendInfoById(friend.getId());
        if(friendInfo == null){
            log.error(MessageConstant.ERROR_MESSAGE_004);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_004);
        }
        friend.setUpdateTime(DateUtil.date());
        infoMapper.updateById(friend);
    }

    @Override
    public void deleteFriend(Long id) {
        LambdaUpdateWrapper<FriendInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(FriendInfo::getStatus,0)
                .set(FriendInfo::getUpdateTime,DateUtil.date())
                .eq(FriendInfo::getId,id)
                .eq(FriendInfo::getStatus,1);
        infoMapper.update(null,wrapper);
    }
}
