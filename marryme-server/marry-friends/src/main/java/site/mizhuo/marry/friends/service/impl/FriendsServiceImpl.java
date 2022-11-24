package site.mizhuo.marry.friends.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.mizhuo.marry.common.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.friends.api.UserApi;
import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;
import site.mizhuo.marry.friends.mapper.FriendGroupMapper;
import site.mizhuo.marry.friends.mapper.FriendInfoMapper;
import site.mizhuo.marry.friends.service.IFriendsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author mizhuo
 */
@Service
public class FriendsServiceImpl implements IFriendsService {

    @Autowired
    FriendInfoMapper infoMapper;

    @Autowired
    FriendGroupMapper groupMapper;

    @Autowired
    UserApi userApi;

    @Autowired
    private HttpServletRequest request;

    @Override
    public List<FriendGroup> queryFriendsGroups() {
        CommonResult<UserDto> user = userApi.info();
        Long groupId = Optional.ofNullable(user)
                .map(d -> d.getData())
                .map(u -> u.getGroupId()).get();
        LambdaQueryWrapper<FriendGroup> wrapper = new LambdaQueryWrapper<FriendGroup>();
        wrapper.eq(FriendGroup::getUserGroupId,groupId)
                .eq(FriendGroup::getStatus,1)
                .orderByAsc(FriendGroup::getId);
        return groupMapper.selectList(wrapper);
    }

    @Override
    public List<FriendInfo> queryFriendsList(String friendGroupId) {
        LambdaQueryWrapper<FriendInfo> wrapper = new LambdaQueryWrapper<FriendInfo>();
        wrapper.eq(FriendInfo::getFriendGroupId,friendGroupId)
                .eq(FriendInfo::getStatus,1)
                .orderByAsc(FriendInfo::getFriendName);
        return infoMapper.selectList(wrapper);
    }
}
