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
public class UserGroup implements Serializable {

    private static final long serialVersionUID = -6566732585222859428L;

    private Long id;

    private Long brideGroomId;

    private Long brideId;

    private Date createTime;

    private Date updateTime;

    private Integer status;

}