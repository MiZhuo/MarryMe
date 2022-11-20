package site.mizhuo.marry.portal.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class MarryInfo implements Serializable {
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

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getBrideGroomName() {
        return brideGroomName;
    }

    public void setBrideGroomName(String brideGroomName) {
        this.brideGroomName = brideGroomName == null ? null : brideGroomName.trim();
    }

    public String getBrideName() {
        return brideName;
    }

    public void setBrideName(String brideName) {
        this.brideName = brideName == null ? null : brideName.trim();
    }

    public Date getMarryTime() {
        return marryTime;
    }

    public void setMarryTime(Date marryTime) {
        this.marryTime = marryTime;
    }

    public String getMarryAddress() {
        return marryAddress;
    }

    public void setMarryAddress(String marryAddress) {
        this.marryAddress = marryAddress == null ? null : marryAddress.trim();
    }

    public BigDecimal getExpectCost() {
        return expectCost;
    }

    public void setExpectCost(BigDecimal expectCost) {
        this.expectCost = expectCost;
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

    public byte[] getRemark() {
        return remark;
    }

    public void setRemark(byte[] remark) {
        this.remark = remark;
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
        MarryInfo other = (MarryInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserGroupId() == null ? other.getUserGroupId() == null : this.getUserGroupId().equals(other.getUserGroupId()))
            && (this.getBrideGroomName() == null ? other.getBrideGroomName() == null : this.getBrideGroomName().equals(other.getBrideGroomName()))
            && (this.getBrideName() == null ? other.getBrideName() == null : this.getBrideName().equals(other.getBrideName()))
            && (this.getMarryTime() == null ? other.getMarryTime() == null : this.getMarryTime().equals(other.getMarryTime()))
            && (this.getMarryAddress() == null ? other.getMarryAddress() == null : this.getMarryAddress().equals(other.getMarryAddress()))
            && (this.getExpectCost() == null ? other.getExpectCost() == null : this.getExpectCost().equals(other.getExpectCost()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (Arrays.equals(this.getRemark(), other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserGroupId() == null) ? 0 : getUserGroupId().hashCode());
        result = prime * result + ((getBrideGroomName() == null) ? 0 : getBrideGroomName().hashCode());
        result = prime * result + ((getBrideName() == null) ? 0 : getBrideName().hashCode());
        result = prime * result + ((getMarryTime() == null) ? 0 : getMarryTime().hashCode());
        result = prime * result + ((getMarryAddress() == null) ? 0 : getMarryAddress().hashCode());
        result = prime * result + ((getExpectCost() == null) ? 0 : getExpectCost().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + (Arrays.hashCode(getRemark()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userGroupId=").append(userGroupId);
        sb.append(", brideGroomName=").append(brideGroomName);
        sb.append(", brideName=").append(brideName);
        sb.append(", marryTime=").append(marryTime);
        sb.append(", marryAddress=").append(marryAddress);
        sb.append(", expectCost=").append(expectCost);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}