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
public class MarryFlow implements Serializable {

    private static final long serialVersionUID = 8059220365667579743L;

    private Long id;

    private Long userGroupId;

    private Integer flowIndex;

    private Date flowTime;

    private Long nextFlowId;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private byte[] stepInfo;

}