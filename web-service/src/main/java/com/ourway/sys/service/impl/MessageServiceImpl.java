package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.utils.ShiroUtils;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.WebSocketUtils;
import com.ourway.base.websocket.BaseSocketMess;
import com.ourway.sys.LanguageConstance;
import com.ourway.sys.dao.SysEmploysDao;
import com.ourway.sys.dao.SysFlowNodePerDao;
import com.ourway.sys.dao.SysMessageDao;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysFlownode;
import com.ourway.sys.model.OurwaySysMessage;
import com.ourway.sys.model.OurwaySysWorkflow;
import com.ourway.sys.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>接口 MessageService.java : <p>
 * <p>说明：消息中心</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:37
 * </pre>
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    SysFlowNodePerDao sysFlowNodePerDao;
    @Autowired
    SysEmploysDao sysEmploysDao;
    @Autowired
    SysMessageDao sysMessageDao;

    @Override
    public boolean updateWorkFlowMsgs(OurwaySysWorkflow workflow, String pageCa) {
        String link = "";
        BaseSocketMess mess = null;
        if (!TextUtils.isEmpty(pageCa))
            link = pageCa + "?owid=" + workflow.getBusinessId();
        else {
            if (!TextUtils.isEmpty(workflow.getTaskFromKey()))
                link = workflow.getTaskFromKey() + "?owid=" + workflow.getBusinessId();
        }
        //流程结束发布消息
        if (workflow.getTaskCatagory().equalsIgnoreCase("99")) {
            //表示流程结束，值发给这个业务的发起人。
            OurwaySysEmploys employs = sysEmploysDao.getOneById(workflow.getFlowOwner());
            mess = BaseSocketMess.txtInstance(employs.getEmpId(), workflow.getFlowName(), "", LanguageConstance.WORKFLOW_FINISH, workflow.getBusinessId(), link);
            updateSendMessageToEmploy(mess, employs);
        } else
            mess = BaseSocketMess.txtInstance("", workflow.getFlowName(), "", LanguageConstance.WORKFLOW_WAIT, workflow.getBusinessId(), link);
        //若taskkey为空，用end代替
        if (TextUtils.isEmpty(workflow.getTaskKey()))
            workflow.setTaskKey("end");
        OurwaySysFlownode node = sysFlowNodePerDao.listOneFlowNode(workflow.getFlowDeploymentId(), workflow.getTaskKey());
        if (!TextUtils.isEmpty(node.getNodeMsgTo())) {
            //表示有其它组要推送
            String[] teams = node.getNodeMsgTo().split("\\,");
            for (String team : teams) {
                if (!TextUtils.isEmpty(team)) {
                    String[] _teams = team.split("\\=");
                    if (_teams.length == 2)
                        updateSendOtherTeam(_teams[0], _teams[1], mess);
                }
            }
        }
        //推送本组的人员
        updateSendOtherTeam(workflow.getFlowDeploymentId(), workflow.getTaskKey(), mess);
        return true;
    }


    //发送node下面所涉及到的所有人
    private void updateSendOtherTeam(String flowCode, String nodeCode, BaseSocketMess mess) {
        List<OurwaySysEmploys> employsList = sysFlowNodePerDao.listAllEmploys(flowCode, nodeCode);
        if (null != employsList && employsList.size() > 0)
            for (OurwaySysEmploys employs : employsList) {
                if (TextUtils.isEmpty(mess.getEmpId()))
                    mess.setEmpId(employs.getEmpId());
                updateSendMessageToEmploy(mess, employs);
            }
    }

    //发送单一的人员
    private void updateSendMessageToEmploy(BaseSocketMess mess, OurwaySysEmploys employs) {
        OurwaySysMessage message = new OurwaySysMessage();
        message.setMessPerId(employs.getEmpId());
        message.setMessPerName(employs.getEmpName());
        message.setMessType(1);
        message.setMessTime(new Date());
        message.setMessAlert((byte) 0);
        message.setMessTitle(mess.getMsgTitle());
        message.setMessContent(mess.getMsgBody());
        message.setState(0);//表示發送
        OurwaySysEmploys sendEmploy = (OurwaySysEmploys) ShiroUtils.getSubject().getPrincipal();
        message.setMessSendId(sendEmploy.getOwid());
        message.setMessSendName(sendEmploy.getEmpName());
        message.setMessBizUrl(mess.getMsgUrl());
        message.setMessBizId(mess.getMsgBizId());
        if (WebSocketUtils.sendMess(employs.getEmpId(), mess)) {
            message.setState(1);//表示發送成功
        }
        sysMessageDao.save(message);
    }


    @Override
    public boolean updateWorkFlowMsgs(OurwaySysWorkflow workflow) {
        return updateWorkFlowMsgs(workflow,"");
    }

    @Override
    public boolean updateWorkFlowMsgs(String empId, String message, String title) {
        BaseSocketMess mess = BaseSocketMess.txtInstance(empId, message, "", title, "", "");
        return WebSocketUtils.sendMess(empId, mess);
    }

    @Override
    public List<OurwaySysMessage> listAllMessage(PublicDataVO dataVO) {
        return sysMessageDao.listAllMessageByEmpId(dataVO.getOpenId());
    }

    @Override
    public void updateReadMessage(String owid) {
        OurwaySysMessage message = sysMessageDao.getOneById(owid);
        message.setState(2);
        sysMessageDao.update(message);
    }
    //    public void updateWorkFlowMsgs(String flowKey, String taskKey) {
//        System.out.println(flowKey+"=="+taskKey);
////        List<String> empList = sysFlowNodePerDao.listAllEmploys(flowKey,taskKey);
////        if(null!=empList&&empList.size()>0){
////            for(String s:empList){
////                BaseSocketMess mess = new BaseSocketMess();
////                mess.setEmpId(s);
////                mess.setDataType(0);
////                mess.setMsgBody("");
////                WebSocketUtils.sendMess(s,);
////            }
////        }
//    }


    @Override
    public PageInfo<OurwaySysMessage> listMessForPage(List<FilterModel> models, Integer pageNo, Integer pageSize, String sortStr) {
        return sysMessageDao.listMessagePageByEmpId(models,pageNo,pageSize,sortStr);
    }

    @Override
    public void removeMessage(List<String> owids) {
           sysMessageDao.removeByIds(owids.toArray());
    }
}
