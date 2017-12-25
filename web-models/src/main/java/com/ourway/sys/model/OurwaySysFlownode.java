package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "OURWAY_SYS_FLOWNODE")
public class OurwaySysFlownode extends UUidEntity {

    @Column(name = "FLOWNODE_REF_OWID", nullable = true, length = 64)
    private String flownodeRefOwid;
    @Column(name = "NODE_CODE", nullable = true, length = 64)
    private String nodeCode;
    @Column(name = "NODE_PER_CODE", nullable = true, length = 64)
    private String nodePerCode;
    @Column(name = "NODE_NAME", nullable = true, length = 160)
    private String nodeName;
    @Column(name = "NODE_LABEL", nullable = true, length = 64)
    private String nodeLabel;
    @Column(name = "NODE_DES", nullable = true, length = 240)
    private String nodeDes;
    @Column(name = "NODE_MSG_TO", nullable = true, length = 240)
    private String nodeMsgTo;
    @Column(name = "MEMO", nullable = true, length = 240)
    private String memo;
    @Column(name = "NODE_STATUS", nullable = true)
    private Integer nodeStatus;
    @Column(name = "NODE_IS_CREATOR", nullable = true)
    private Short nodeIsCreator;

    public Short getNodeIsCreator() {
        return nodeIsCreator;
    }

    public void setNodeIsCreator(Short nodeIsCreator) {
        this.nodeIsCreator = nodeIsCreator;
    }

    public String getNodeMsgTo() {
        return nodeMsgTo;
    }

    public void setNodeMsgTo(String nodeMsgTo) {
        this.nodeMsgTo = nodeMsgTo;
    }

    public String getFlownodeRefOwid() {
        return flownodeRefOwid;
    }

    public void setFlownodeRefOwid(String flownodeRefOwid) {
        this.flownodeRefOwid = flownodeRefOwid;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodePerCode() {
        return nodePerCode;
    }

    public void setNodePerCode(String nodePerCode) {
        this.nodePerCode = nodePerCode;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public void setNodeLabel(String nodeLabel) {
        this.nodeLabel = nodeLabel;
    }

    public String getNodeDes() {
        return nodeDes;
    }

    public void setNodeDes(String nodeDes) {
        this.nodeDes = nodeDes;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(Integer nodeStatus) {
        this.nodeStatus = nodeStatus;
    }
}
