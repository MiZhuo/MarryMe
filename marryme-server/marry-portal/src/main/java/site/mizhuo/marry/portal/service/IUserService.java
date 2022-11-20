package site.mizhuo.marry.portal.service;

import org.springframework.transaction.annotation.Transactional;
import site.mizhuo.marry.api.CommonResult;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.portal.domain.UserInfo;

/**
 * 用户管理Service
 * @author mizhuo
 */
public interface IUserService {

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDto loadUserByUsername(String username);
    
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    UserInfo getByUsername(String username);

    /**
     * 根据编号获取用户
     * @param id
     * @return
     */
    UserInfo getById(Long id);

    /**
     * 用户注册
     * @param username
     * @param password
     * @param telephone
     * @param authCode
     */
    @Transactional
    void register(String username, String password, String telephone, String authCode);

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    String generateAuthCode(String telephone);

    /**
     * 修改密码
     * @param telephone
     * @param password
     * @param authCode
     */
    @Transactional
    void updatePassword(String telephone, String password, String authCode);

    /**
     * 获取当前登录用户
     * @return
     */
    UserInfo getCurrentUser();

    /**
     * 登录后获取token
     * @param username
     * @param password
     * @return
     */
    CommonResult login(String username, String password);
}
