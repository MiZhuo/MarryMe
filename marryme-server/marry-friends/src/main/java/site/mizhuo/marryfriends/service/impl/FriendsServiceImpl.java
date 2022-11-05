package site.mizhuo.marryfriends.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.mizhuo.marryfriends.entity.FriendGroup;
import site.mizhuo.marryfriends.entity.FriendInfo;
import site.mizhuo.marryfriends.mapper.FriendGroupMapper;
import site.mizhuo.marryfriends.mapper.FriendInfoMapper;
import site.mizhuo.marryfriends.service.IFriendsService;

import java.util.List;

/**
 * @author mizhuo
 */
@Service
public class FriendsServiceImpl implements IFriendsService{

    @Autowired
    FriendInfoMapper infoMapper;

    @Autowired
    FriendGroupMapper groupMapper;


    @Override
    public List<FriendGroup> queryFriendsGroups(Long id) {
        QueryWrapper<FriendGroup> wrapper = new QueryWrapper<>();
        //todo add mybatis function
        return groupMapper.selectList(null);
    }

    @Override
    public List<FriendInfo> queryFriendsList(String friendGroupId) {
        return null;
    }
}
