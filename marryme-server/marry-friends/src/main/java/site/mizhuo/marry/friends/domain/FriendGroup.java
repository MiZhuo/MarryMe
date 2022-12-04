package site.mizhuo.marry.friends.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mizhuo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FriendGroup implements Serializable {
    private static final long serialVersionUID = -8297679479993375379L;

    private Long id;

    private Long userGroupId;

    private String name;

    private Integer status;
}