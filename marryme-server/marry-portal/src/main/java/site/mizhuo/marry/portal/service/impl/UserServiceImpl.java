package site.mizhuo.marry.portal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import site.mizhuo.marry.common.CommonResult;
import site.mizhuo.marry.constant.AuthConstant;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.exception.Asserts;
import site.mizhuo.marry.portal.domain.UserGroup;
import site.mizhuo.marry.portal.domain.UserInfo;
import site.mizhuo.marry.portal.api.AuthApi;
import site.mizhuo.marry.portal.mapper.UserGroupMapper;
import site.mizhuo.marry.portal.service.UserCacheService;
import site.mizhuo.marry.portal.service.UserService;
import site.mizhuo.marry.portal.mapper.UserInfoMapper;

import java.util.*;

/**
 * 用户管理Service实现类
 * @author mizhuo
 */
@Service
public class UserServiceImpl implements UserService {

    private static final int MAX_AUTH_CODE_LENGTH = 6;

    @Autowired
    private UserInfoMapper userMapper;

    @Autowired
    private UserGroupMapper groupMapper;

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private AuthApi authApi;

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public UserDto loadUserByUsername(String username) {
        UserInfo user = getByUsername(username);
        if(user!=null){
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(user,userDto);
            userDto.setRoles(CollUtil.toList("前台用户"));
            UserGroup group = getUserGroupInfo(user);
            if(group != null){
                userDto.setGroupId(group.getId());
            }
            return userDto;
        }
        return null;
    }

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public UserInfo getByUsername(String username) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>().eq("username",username);
        List<UserInfo> userInfoList = userMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(userInfoList)) {
            return userInfoList.get(0);
        }
        return null;
    }

    /**
     * 根据编号获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public UserInfo getById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 登录后获取token
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @Override
    public CommonResult<?> login(String username, String password) {
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>(16);
        params.put("client_id", AuthConstant.MARRY_CLIENT_ID);
        params.put("client_secret","mizhuo123");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);
        return authApi.getAccessToken(params);
    }

    /**
     * 获取用户组信息
     * @param userInfo 用户信息
     * @return 用户组信息
     */
    @Override
    public UserGroup getUserGroupInfo(UserInfo userInfo) {
        // 根据用户角色,查询用户组信息
        LambdaQueryWrapper<UserGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(userInfo.getRole() == 1 ? UserGroup::getBrideGroomId : UserGroup::getBrideId,userInfo.getId());
        return groupMapper.selectOne(wrapper);
    }

    /**
     * 生成验证码
     * @param telephone 手机号
     * @return 验证码
     */
    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0;i < MAX_AUTH_CODE_LENGTH;i++){
            sb.append(random.nextInt(10));
        }
        userCacheService.setAuthCode(telephone,sb.toString());
        return sb.toString();
    }

    /**
     * 校验验证码
     * @param authCode 验证码
     * @param telephone 手机号
     * @return 校验是否成功
     */
    private boolean verifyAuthCode(String authCode, String telephone){
        if(ObjectUtils.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = userCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }
}
