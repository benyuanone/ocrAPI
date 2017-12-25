package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.sys.model.OurwaySysMessage;
import com.ourway.sys.model.OurwaySysWorkflow;

import java.util.List;

/**<p>接口 MessageService.java : <p>
 *<p>说明：消息中心</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:37
</pre>
 */
public interface MessageService {

    //更具流程和任务的key发送消息给指定的审批人
    /**
    *<p>方法:updateNodeMsgs  </p>
    *<ul>
     *<li> @param flowKey 工作流key</li>
     *<li> @param taskKey 任务Key</li>
    *<li>@return void  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/9/27 0:23  </li>
    *</ul>
    */
    boolean updateWorkFlowMsgs(OurwaySysWorkflow workflow,String pageCa);

    boolean updateWorkFlowMsgs(OurwaySysWorkflow workflow);

    boolean updateWorkFlowMsgs(String empId,String message,String title);

    List<OurwaySysMessage>  listAllMessage(PublicDataVO dataVO);

    void updateReadMessage(String owid);

    PageInfo<OurwaySysMessage> listMessForPage(List<FilterModel> models,Integer pageNo, Integer pageSize,String sortStr);

    void removeMessage(List<String> owids);

}
