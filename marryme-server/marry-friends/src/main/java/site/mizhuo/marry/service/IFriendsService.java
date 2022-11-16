package site.mizhuo.marry.service;

import site.mizhuo.marry.domain.FriendGroup;
import site.mizhuo.marry.domain.FriendInfo;

import java.util.List;

/**
 * @author mizhuo
 */
public interface IFriendsService {
    List<FriendGroup> queryFriendsGroups(Long id);

    List<FriendInfo> queryFriendsList(String friendGroupId);
}
