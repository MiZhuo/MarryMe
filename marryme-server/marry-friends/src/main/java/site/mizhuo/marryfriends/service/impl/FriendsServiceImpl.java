package site.mizhuo.marryfriends.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.mizhuo.marryfriends.mapper.FriendInfoMapper;
import site.mizhuo.marryfriends.service.IFriendsService;

/**
 * @author mizhuo
 */
@Service
public class FriendsServiceImpl implements IFriendsService{

    @Autowired
    FriendInfoMapper mapper;


}
