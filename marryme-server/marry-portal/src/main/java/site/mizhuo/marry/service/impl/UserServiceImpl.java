package site.mizhuo.marry.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
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
import site.mizhuo.marry.domain.UserInfo;
import site.mizhuo.marry.mapper.UserInfoMapper;
import site.mizhuo.marry.service.IAuthService;
import site.mizhuo.marry.service.IUserCacheService;
import site.mizhuo.marry.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 会员管理Service实现类
 *
 * @author macro
 * @date 2018/8/3
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserInfoMapper userMapper;
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
    public UserInfo getByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        List<UserInfo> memberList = userMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
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
        userCacheService.delMember(userInfo.getId());
    }

    @Override
    public UserInfo getCurrentMember() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if(StrUtil.isEmpty(userStr)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        UserDto userDto = JSONUtil.toBean(userStr, UserDto.class);
        UserInfo userInfo = userCacheService.getMember(userDto.getId());
        if(userInfo!=null){
            return userInfo;
        }else{
            userInfo = getById(userDto.getId());
            userCacheService.setMember(userInfo);
            return userInfo;
        }
    }


    @Override
    public UserDto loadUserByUsername(String username) {
        UserInfo member = getByUsername(username);
        if(member!=null){
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(member,userDto);
            userDto.setRoles(CollUtil.toList("前台会员"));
            return userDto;
        }
        return null;
    }

    @Override
    public CommonResult login(String username, String password) {
        if(StrUtil.isEmpty(username)||StrUtil.isEmpty(password)){
            Asserts.fail("用户名或密码不能为空！");
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_id", AuthConstant.PORTAL_CLIENT_ID);
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

}
