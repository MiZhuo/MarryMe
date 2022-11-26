package site.mizhuo.marry.portal.service;

import site.mizhuo.marry.common.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.portal.domain.UserGroup;
import site.mizhuo.marry.portal.domain.UserInfo;

/**
 * 用户管理Service
 * @author mizhuo
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDto loadUserByUsername(String username);
    
    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    UserInfo getByUsername(String username);

    /**
     * 根据编号获取用户信息
     * @param id
     * @return
     */
    UserInfo getById(Long id);

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    String generateAuthCode(String telephone);

    /**
     * 登录后获取token
     * @param username
     * @param password
     * @return
     */
    CommonResult login(String username, String password);

    /**
     * 获取用户组信息
     * @param user
     * @return
     */
    UserGroup getUserGroupInfo(UserInfo user);
}
