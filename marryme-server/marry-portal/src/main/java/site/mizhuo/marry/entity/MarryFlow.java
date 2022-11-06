package site.mizhuo.marry.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class MarryFlow implements Serializable {
    private Long id;

    private Long userGroupId;

    private Integer flowIndex;

    private Date flowTime;

    private Long nextFlowId;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private byte[] stepInfo;

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

    public Integer getFlowIndex() {
        return flowIndex;
    }

    public void setFlowIndex(Integer flowIndex) {
        this.flowIndex = flowIndex;
    }

    public Date getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(Date flowTime) {
        this.flowTime = flowTime;
    }

    public Long getNextFlowId() {
        return nextFlowId;
    }

    public void setNextFlowId(Long nextFlowId) {
        this.nextFlowId = nextFlowId;
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

    public byte[] getStepInfo() {
        return stepInfo;
    }

    public void setStepInfo(byte[] stepInfo) {
        this.stepInfo = stepInfo;
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
        MarryFlow other = (MarryFlow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserGroupId() == null ? other.getUserGroupId() == null : this.getUserGroupId().equals(other.getUserGroupId()))
            && (this.getFlowIndex() == null ? other.getFlowIndex() == null : this.getFlowIndex().equals(other.getFlowIndex()))
            && (this.getFlowTime() == null ? other.getFlowTime() == null : this.getFlowTime().equals(other.getFlowTime()))
            && (this.getNextFlowId() == null ? other.getNextFlowId() == null : this.getNextFlowId().equals(other.getNextFlowId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (Arrays.equals(this.getStepInfo(), other.getStepInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserGroupId() == null) ? 0 : getUserGroupId().hashCode());
        result = prime * result + ((getFlowIndex() == null) ? 0 : getFlowIndex().hashCode());
        result = prime * result + ((getFlowTime() == null) ? 0 : getFlowTime().hashCode());
        result = prime * result + ((getNextFlowId() == null) ? 0 : getNextFlowId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + (Arrays.hashCode(getStepInfo()));
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
        sb.append(", flowIndex=").append(flowIndex);
        sb.append(", flowTime=").append(flowTime);
        sb.append(", nextFlowId=").append(nextFlowId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", status=").append(status);
        sb.append(", stepInfo=").append(stepInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}