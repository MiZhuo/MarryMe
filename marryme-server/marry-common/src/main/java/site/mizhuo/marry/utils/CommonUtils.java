package site.mizhuo.marry.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JWSObject;
import site.mizhuo.marry.common.ResultCode;
import site.mizhuo.marry.constant.AuthConstant;
import site.mizhuo.marry.domain.UserDto;
import site.mizhuo.marry.exception.Asserts;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author mizhuo
 */
public class CommonUtils {

    /**
     * 获取当前登录用户信息
     * @return 用户信息
     */
    public static UserDto getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader(AuthConstant.JWT_TOKEN_HEADER);
        //从token中解析用户信息
        String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
        JWSObject jwsObject = null;
        try {
            jwsObject = JWSObject.parse(realToken);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String userStr = jwsObject.getPayload().toString();
        if(StrUtil.isEmpty(userStr)){
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        return JSONUtil.toBean(userStr, UserDto.class);
    }
}
