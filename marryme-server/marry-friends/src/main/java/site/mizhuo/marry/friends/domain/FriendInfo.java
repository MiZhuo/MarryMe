package site.mizhuo.marry.friends.domain;

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
public class FriendInfo implements Serializable {
    private static final long serialVersionUID = 6668032160303626757L;

    private Long id;

    private Long friendGroupId;

    private Long inviterId;

    private String friendName;

    private String friendNameEn;

    private String friendSex;

    private String friendMobile;

    private Integer isInvit;

    private Date createTime;

    private Date updateTime;

    private Integer status;

}