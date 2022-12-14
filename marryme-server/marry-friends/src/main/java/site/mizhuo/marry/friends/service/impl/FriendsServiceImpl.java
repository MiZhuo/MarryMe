package site.mizhuo.marry.friends.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.exception.ApiException;
import site.mizhuo.marry.friends.constant.MessageConstant;
import site.mizhuo.marry.friends.domain.FriendGroup;
import site.mizhuo.marry.friends.domain.FriendInfo;
import site.mizhuo.marry.friends.mapper.FriendGroupMapper;
import site.mizhuo.marry.friends.mapper.FriendInfoMapper;
import site.mizhuo.marry.friends.service.FriendsService;
import site.mizhuo.marry.utils.ChineseCharacterUtils;
import site.mizhuo.marry.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mizhuo
 */
@Slf4j
@Service
public class FriendsServiceImpl implements FriendsService {

    private static final int MAX_GROUP_COUNT = 4;

    private static final String IGNORE_KEYWORD = "undefined";

    @Autowired
    FriendInfoMapper infoMapper;

    @Autowired
    FriendGroupMapper groupMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional(rollbackFor=ApiException.class)
    public void saveFriendGroup(String groupName) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        LambdaQueryWrapper<FriendGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendGroup::getUserGroupId,userGroupId)
                .eq(FriendGroup::getStatus,1);
        Long count = groupMapper.selectCount(wrapper);
        if(count >= MAX_GROUP_COUNT){
            log.error(MessageConstant.ERROR_MESSAGE_001);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_001);
        }
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setUserGroupId(userGroupId);
        friendGroup.setName(groupName);
        friendGroup.setStatus(1);
        groupMapper.insert(friendGroup);
    }

    @Override
    public List<FriendGroup> queryFriendsGroups() {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        LambdaQueryWrapper<FriendGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(FriendGroup::getName,FriendGroup::getId)
                .eq(FriendGroup::getUserGroupId,userGroupId)
                .eq(FriendGroup::getStatus,1)
                .orderByAsc(FriendGroup::getId);
        return groupMapper.selectList(wrapper);
    }

    @Override
    public void updateFriendGroup(Long groupId, String groupName, int status) {
        UserDto user = CommonUtils.getCurrentUser(request);
        Long userGroupId = Optional.ofNullable(user)
                .map(UserDto::getGroupId).orElse(null);
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setId(groupId);
        friendGroup.setUserGroupId(userGroupId);
        if(StringUtils.isNotEmpty(groupName)){
            friendGroup.setName(groupName);
        }
        friendGroup.setStatus(status);
        LambdaUpdateWrapper<FriendGroup> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(FriendGroup::getId,groupId)
                .eq(FriendGroup::getUserGroupId,userGroupId);
        groupMapper.update(friendGroup,wrapper);
    }

    @Transactional(rollbackFor=ApiException.class)
    @Override
    public void addFriend(FriendInfo friend) {
        if(friend.getFriendGroupId() == null){
            log.error(MessageConstant.ERROR_MESSAGE_003);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_003);
        }
        checkFriendExists(friend);
        if(StringUtils.isNotEmpty(friend.getFriendName())) {
            friend.setFriendNameEn(ChineseCharacterUtils.getSpells(friend.getFriendName()));
        }
        friend.setCreateTime(DateUtil.date());
        friend.setUpdateTime(DateUtil.date());
        infoMapper.insert(friend);
    }

    @Transactional(rollbackFor=ApiException.class)
    @Override
    public Map<String, Object> queryFriendsList(Long id,String keyword) {
        LambdaQueryWrapper<FriendGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendGroup::getId,id)
                .eq(FriendGroup::getStatus,1);
        if(!groupMapper.exists(wrapper)){
            log.error(MessageConstant.ERROR_MESSAGE_005);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_005);
        }
        LambdaQueryWrapper<FriendInfo> wrapper2 = new LambdaQueryWrapper<>();
        wrapper2.eq(FriendInfo::getFriendGroupId,id)
                .eq(FriendInfo::getStatus,1)
                .orderByAsc(FriendInfo::getFriendName);
        List<FriendInfo> friendInfos = infoMapper.selectList(wrapper2);
        //如果搜索条件不为空,则根据条件过滤结果集
        if(StringUtils.isNotEmpty(keyword) && !IGNORE_KEYWORD.equals(keyword)){
            friendInfos = friendInfos.parallelStream().filter(friendInfo -> {
                return friendInfo.getFriendNameEn().toLowerCase().contains(keyword.toLowerCase())
                        || friendInfo.getFriendName().contains(keyword)
                        || friendInfo.getFriendMobile().contains(keyword);
            }).collect(Collectors.toList());
        }
        Map<String,Object> res = new HashMap<>(16);
        //以姓名首字母分组,并根据字母排序
        Map<String, List<FriendInfo>> flapMap = friendInfos.parallelStream()
                .collect(Collectors.groupingBy(f -> f.getFriendNameEn().substring(0, 1)))
                .entrySet().parallelStream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        //首字母集合
        List<String> indexList = friendInfos.parallelStream().map(f -> f.getFriendNameEn().substring(0,1)).distinct().sorted().collect(Collectors.toList());
        res.put("data",flapMap);
        res.put("indexList",indexList);
        res.put("totalSize",friendInfos.size());
        return res;
    }

    @Override
    public FriendInfo queryFriendInfoById(Long id) {
        LambdaQueryWrapper<FriendInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendInfo::getId,id)
                .eq(FriendInfo::getStatus,1)
                .orderByAsc(FriendInfo::getFriendName);
        return infoMapper.selectOne(wrapper);
    }

    @Override
    @Transactional(rollbackFor=ApiException.class)
    public void updateFriendInfo(FriendInfo friend) {
        FriendInfo friendInfo = queryFriendInfoById(friend.getId());
        if(friendInfo == null){
            log.error(MessageConstant.ERROR_MESSAGE_004);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_004);
        }
        checkFriendExists(friend);
        if(StringUtils.isNotEmpty(friend.getFriendName())) {
            friend.setFriendNameEn(ChineseCharacterUtils.getSpells(friend.getFriendName()));
        }
        friend.setUpdateTime(DateUtil.date());
        infoMapper.updateById(friend);
    }

    @Override
    public void deleteFriend(Long id) {
        LambdaUpdateWrapper<FriendInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(FriendInfo::getStatus,0)
                .set(FriendInfo::getUpdateTime,DateUtil.date())
                .eq(FriendInfo::getId,id)
                .eq(FriendInfo::getStatus,1);
        infoMapper.update(null,wrapper);
    }

    private void checkFriendExists(FriendInfo friend){
        LambdaQueryWrapper<FriendInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FriendInfo::getFriendName,friend.getFriendName())
                .eq(FriendInfo::getFriendGroupId,friend.getFriendGroupId())
                .ne(FriendInfo::getId,friend.getId());
        if(infoMapper.exists(wrapper)) {
            log.error(MessageConstant.ERROR_MESSAGE_006);
            throw new ApiException(MessageConstant.ERROR_MESSAGE_006);
        }
    }
}
