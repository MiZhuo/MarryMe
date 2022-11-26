package site.mizhuo.marry.friends.service;

import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;

import java.util.List;

/**
 * @author mizhuo
 */
public interface FriendsService {
    /**
     * 获取当前登陆用户的亲友分组
     * @return
     */
    List<FriendGroup> queryFriendsGroups();

    /**
     * 根据分组ID获取亲友列表
     * @param friendGroupId
     * @return
     */
    List<FriendInfo> queryFriendsList(String friendGroupId);
}
