package site.mizhuo.marry.service;

import org.springframework.transaction.annotation.Transactional;
import site.mizhuo.marry.api.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.entity.UserInfo;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface IUserService {
    /**
     * 根据用户名获取会员
     */
    UserInfo getByUsername(String username);

    /**
     * 根据会员编号获取会员
     */
    UserInfo getById(Long id);

    /**
     * 用户注册
     */
    @Transactional
    void register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     */
    String generateAuthCode(String telephone);

    /**
     * 修改密码
     */
    @Transactional
    void updatePassword(String telephone, String password, String authCode);

    /**
     * 获取当前登录会员
     */
    UserInfo getCurrentMember();

    /**
     * 获取用户信息
     */
    UserDto loadUserByUsername(String username);

    /**
     * 登录后获取token
     */
    CommonResult login(String username, String password);
}