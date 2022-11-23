package site.mizhuo.marry.portal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mizhuo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MarryInfo implements Serializable {

    private static final long serialVersionUID = 710438649753756138L;

    private Long id;

    private Long userGroupId;

    private String brideGroomName;

    private String brideName;

    private Date marryTime;

    private String marryAddress;

    private BigDecimal expectCost;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private byte[] remark;

}