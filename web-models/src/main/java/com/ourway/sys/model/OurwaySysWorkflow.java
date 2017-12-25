package com.ourway.sys.model;

import com.ourway.base.model.UUidEntity;
import org.activiti.engine.task.Task;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by CC on 2017/4/1.
 */
@Entity
@Table(name = "OURWAY_SYS_WORKFLOW")
public class OurwaySysWorkflow extends UUidEntity {

    @Column(name = "CLASS_NAME", nullable = true, length = 160)
    private String className;
    @Column(name = "BUSINESS_ID", nullable = true, length = 160)
    private String businessId;
    @Column(name = "FLOW_INSTANCE_ID", nullable = true, length = 160)
    private String flowInstanceId;
    @Column(name = "FLOW_DEPLOYMENT_ID", nullable = true, length = 160)
    private String flowDeploymentId;
    @Column(name = "FLOW_NAME", nullable = true, length = 160)
    private String flowName;
    @Column(name = "FLOW_TYPE", nullable = true, length = 160)
    private String flowType;
    @Column(name = "FLOW_OWNER", nullable = true, length = 160)
    private String flowOwner;
    @Column(name = "TASK_ID", nullable = true, length = 160)
    private String taskId;
    @Column(name = "TASK_NAME", nullable = true, length = 160)
    private String taskName;
    @Column(name = "TASK_PARENT_ID", nullable = true, length = 160)
    private String taskParentId;
    @Column(name = "TASK_ASSIGN", nullable = true, length = 160)
    private String taskAssign;
    @Column(name = "TASK_CATAGORY", nullable = true, length = 160)
    private String taskCatagory;
    @Column(name = "TASK_PER_ID", nullable = true, length = 160)
    private String taskPerId;
    @Column(name = "TASK_PER_NAME", nullable = true, length = 160)
    private String taskPerName;
    @Column(name = "TASK_RESULT", nullable = true, length = 160)
    private String taskResult;
    @Column(name = "TASK_PROCESS_ID", nullable = true, length = 160)
    private String taskProcessId;
    @Column(name = "TASK_MEMO", nullable = true, length = 160)
    private String taskMemo;
    @Column(name = "TASK_FROM_KEY", nullable = true, length = 160)
    private String taskFromKey;
    @Column(name = "TASK_KEY", nullable = true, length = 160)
    private String taskKey;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TASK_TIME", updatable = false)
    private java.util.Date taskTime;
    @Transient
    private Integer audit;
    @Transient
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getTaskProcessId() {
        return taskProcessId;
    }

    public void setTaskProcessId(String taskProcessId) {
        this.taskProcessId = taskProcessId;
    }

    public Integer getAudit() {
        return audit;
    }

    public void setAudit(Integer audit) {
        this.audit = audit;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskParentId() {
        return taskParentId;
    }

    public void setTaskParentId(String taskParentId) {
        this.taskParentId = taskParentId;
    }

    public String getTaskAssign() {
        return taskAssign;
    }

    public void setTaskAssign(String taskAssign) {
        this.taskAssign = taskAssign;
    }

    public String getTaskCatagory() {
        return taskCatagory;
    }

    public void setTaskCatagory(String taskCatagory) {
        this.taskCatagory = taskCatagory;
    }

    public String getTaskPerId() {
        return taskPerId;
    }

    public void setTaskPerId(String taskPerId) {
        this.taskPerId = taskPerId;
    }

    public String getTaskPerName() {
        return taskPerName;
    }

    public void setTaskPerName(String taskPerName) {
        this.taskPerName = taskPerName;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    public String getTaskMemo() {
        return taskMemo;
    }

    public void setTaskMemo(String taskMemo) {
        this.taskMemo = taskMemo;
    }

    public String getTaskFromKey() {
        return taskFromKey;
    }

    public void setTaskFromKey(String taskFromKey) {
        this.taskFromKey = taskFromKey;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getFlowInstanceId() {
        return flowInstanceId;
    }

    public void setFlowInstanceId(String flowInstanceId) {
        this.flowInstanceId = flowInstanceId;
    }

    public String getFlowDeploymentId() {
        return flowDeploymentId;
    }

    public void setFlowDeploymentId(String flowDeploymentId) {
        this.flowDeploymentId = flowDeploymentId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getFlowOwner() {
        return flowOwner;
    }

    public void setFlowOwner(String flowOwner) {
        this.flowOwner = flowOwner;
    }
}
