package site.mizhuo.marry.portal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mizhuo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -2831968064966921436L;

    private Long id;

    private String username;

    private String password;

    private Integer role;

    private String icon;

    private String mobile;

    private String wechartOpenId;

    private Date createTime;

    private Date loginTime;

    private Integer status;
}