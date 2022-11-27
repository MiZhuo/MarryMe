package site.mizhuo.marry.friends.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;

import java.util.List;
import java.util.Map;

/**
 * @author mizhuo
 */
public interface FriendsService {
    /**
     * 添加亲友分组
     * @param groupName
     */
    void saveFriendGroup(String groupName);

    /**
     * 获取当前登陆用户的亲友分组
     * @return
     */
    List<FriendGroup> queryFriendsGroups();

    /**
     * 更新分组名称
     * @param groupId
     * @param groupName
     * @param status
     */
    void updateFriendGroup(Long groupId, String groupName, int status);

    /**
     * 添加亲友
     * @param friend
     */
    void addFriend(FriendInfo friend);

    /**
     * 根据分组ID获取亲友列表
     * @param params
     * @return
     */
    Page<FriendInfo> queryFriendsList(Map<String,Object> params);

    /**
     * 根据ID获取亲友信息
     * @param id
     * @return
     */
    FriendInfo queryFriendInfoById(Long id);

    /**
     * 更新亲友信息
     * @param friend
     */
    void updateFriendInfo(FriendInfo friend);

    /**
     * 删除亲友
     * @param id
     */
    void deleteFriend(Long id);
}
