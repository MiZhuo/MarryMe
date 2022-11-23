package site.mizhuo.marry.portal.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import site.mizhuo.marry.api.CommonResult;
import site.mizhuo.marry.api.ResultCode;
import site.mizhuo.marry.constant.AuthConstant;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.exception.Asserts;
import site.mizhuo.marry.portal.domain.UserGroup;
import site.mizhuo.marry.portal.domain.UserInfo;
import site.mizhuo.marry.portal.api.IAuthService;
import site.mizhuo.marry.portal.mapper.UserGroupMapper;
import site.mizhuo.marry.portal.service.IUserCacheService;
import site.mizhuo.marry.portal.service.IUserService;
import site.mizhuo.marry.portal.mapper.UserInfoMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户管理Service实现类
 * @author mizhuo
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoMapper userMapper;

    @Autowired
    private UserGroupMapper groupMapper;

    @Autowired
    private IUserCacheService userCacheService;

    @Value("${redis.key.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Autowired
    private IAuthService authService;

    @Autowired
    private HttpServletRequest request;

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

    @Override
    public UserInfo getByUsername(String username) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<UserInfo>().eq("username",username);
        List<UserInfo> UserList = userMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(UserList)) {
            return UserList.get(0);
        }
        return null;
    }

    @Override
    public UserInfo getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void register(String username, String password, String telephone, String authCode) {
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误");
        }
        //查询是否已有该用户

        //没有该用户进行添加操作

    }

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        userCacheService.setAuthCode(telephone,sb.toString());
        return sb.toString();
    }

    @Override
    public void updatePassword(String telephone, String password, String authCode) {

        List<UserInfo> userInfoList = userMapper.selectList(null);
        if(CollectionUtils.isEmpty(userInfoList)){
            Asserts.fail("该账号不存在");
        }
        //验证验证码
        if(!verifyAuthCode(authCode,telephone)){
            Asserts.fail("验证码错误");
        }
        UserInfo userInfo = userInfoList.get(0);
        userInfo.setPassword(BCrypt.hashpw(password));
        userMapper.updateById(userInfo);
        userCacheService.delUser(userInfo.getId());
    }

    @Override
    public UserInfo getCurrentUser() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if(StrUtil.isEmpty(userStr)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        UserInfo userInfo = userCacheService.getUser(userDto.getId());
        if (userInfo == null) {
            userInfo = getById(userDto.getId());
            userCacheService.setUser(userInfo);
        }
        return userInfo;
    }

    @Override
    public CommonResult login(String username, String password) {
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>(16);
        params.put("client_id", AuthConstant.MARRY_CLIENT_ID);
        params.put("client_secret","123456");
        params.put("grant_type","password");
        params.put("username",username);
        params.put("password",password);
        return authService.getAccessToken(params);
    }

    private boolean verifyAuthCode(String authCode, String telephone){
        if(StringUtils.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = userCacheService.getAuthCode(telephone);
        return authCode.equals(realAuthCode);
    }

    @Override
    public UserGroup getUserGroupInfo(UserInfo userInfo) {
        // 根据用户角色,查询用户组信息
        QueryWrapper<UserGroup> wrapper = new QueryWrapper<UserGroup>().eq(userInfo.getRole() == 1 ? "bride_groom_id" : "bride_id",userInfo.getId());
        UserGroup userGroup = groupMapper.selectOne(wrapper);
        return userGroup;
    }
}
