package site.mizhuo.marry.friends.service;

import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;

import java.util.List;

/**
 * @author mizhuo
 */
public interface IFriendsService {
    List<FriendGroup> queryFriendsGroups(Long id);

    List<FriendInfo> queryFriendsList(String friendGroupId);
}
