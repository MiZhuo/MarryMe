package site.mizhuo.marryfriends.service;

import site.mizhuo.marryfriends.entity.FriendGroup;
import site.mizhuo.marryfriends.entity.FriendInfo;

import java.util.List;

/**
 * @author mizhuo
 */
public interface IFriendsService {
    List<FriendGroup> queryFriendsGroups(Long id);

    List<FriendInfo> queryFriendsList(String friendGroupId);
}
