package site.mizhuo.marry.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mizhuo
 */
public class FriendInfo implements Serializable {
    private Long id;

    private Long friendGroupId;

    private Long inviterId;

    private String friendName;

    private String friendSex;

    private String friendMobile;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFriendGroupId() {
        return friendGroupId;
    }

    public void setFriendGroupId(Long friendGroupId) {
        this.friendGroupId = friendGroupId;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName == null ? null : friendName.trim();
    }

    public String getFriendSex() {
        return friendSex;
    }

    public void setFriendSex(String friendSex) {
        this.friendSex = friendSex == null ? null : friendSex.trim();
    }

    public String getFriendMobile() {
        return friendMobile;
    }

    public void setFriendMobile(String friendMobile) {
        this.friendMobile = friendMobile == null ? null : friendMobile.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FriendInfo other = (FriendInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFriendGroupId() == null ? other.getFriendGroupId() == null : this.getFriendGroupId().equals(other.getFriendGroupId()))
            && (this.getInviterId() == null ? other.getInviterId() == null : this.getInviterId().equals(other.getInviterId()))
            && (this.getFriendName() == null ? other.getFriendName() == null : this.getFriendName().equals(other.getFriendName()))
            && (this.getFriendSex() == null ? other.getFriendSex() == null : this.getFriendSex().equals(other.getFriendSex()))
            && (this.getFriendMobile() == null ? other.getFriendMobile() == null : this.getFriendMobile().equals(other.getFriendMobile()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFriendGroupId() == null) ? 0 : getFriendGroupId().hashCode());
        result = prime * result + ((getInviterId() == null) ? 0 : getInviterId().hashCode());
        result = prime * result + ((getFriendName() == null) ? 0 : getFriendName().hashCode());
        result = prime * result + ((getFriendSex() == null) ? 0 : getFriendSex().hashCode());
        result = prime * result + ((getFriendMobile() == null) ? 0 : getFriendMobile().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", friendGroupId=").append(friendGroupId);
        sb.append(", inviterId=").append(inviterId);
        sb.append(", friendName=").append(friendName);
        sb.append(", friendSex=").append(friendSex);
        sb.append(", friendMobile=").append(friendMobile);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}