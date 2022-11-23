package site.mizhuo.marry.friends.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;
import site.mizhuo.marry.friends.mapper.FriendGroupMapper;
import site.mizhuo.marry.friends.mapper.FriendInfoMapper;
import site.mizhuo.marry.friends.service.IFriendsService;

import java.util.List;

/**
 * @author mizhuo
 */
@Service
public class FriendsServiceImpl implements IFriendsService {

    @Autowired
    FriendInfoMapper infoMapper;

    @Autowired
    FriendGroupMapper groupMapper;


    @Override
    public List<FriendGroup> queryFriendsGroups() {
        return null;
    }

    @Override
    public List<FriendInfo> queryFriendsList(String friendGroupId) {
        return null;
    }
}
