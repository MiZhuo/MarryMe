package site.mizhuo.marry.friends.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @param groupName 分组名称
     */
    void saveFriendGroup(String groupName);

    /**
     * 获取当前登陆用户的亲友分组
     * @return 分组列表
     */
    List<FriendGroup> queryFriendsGroups();

    /**
     * 更新分组名称
     * @param groupId 分组ID
     * @param groupName 分组名称
     * @param status 状态
     */
    void updateFriendGroup(Long groupId, String groupName, int status);

    /**
     * 添加亲友
     * @param friend 亲友信息
     */
    void addFriend(FriendInfo friend);

    /**
     * 根据分组ID获取亲友列表
     * @param id 分组ID
     * @param keyword 关键字
     * @return 亲友列表
     */
    Map<String, Object> queryFriendsList(Long id, String keyword);

    /**
     * 根据ID获取亲友信息
     * @param id 亲友ID
     * @return 亲友信息
     */
    FriendInfo queryFriendInfoById(Long id);

    /**
     * 更新亲友信息
     * @param friend 亲友信息
     */
    void updateFriendInfo(FriendInfo friend);

    /**
     * 删除亲友
     * @param id 亲友ID
     */
    void deleteFriend(Long id);

}
