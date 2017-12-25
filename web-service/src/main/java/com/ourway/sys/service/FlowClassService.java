package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysFlowClass;

import java.util.List;

/**<p>接口 FlowClassService.java : <p>
 *<p>说明：业务类工作流配置</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 
</pre>
 */
public interface FlowClassService {

    List<OurwaySysFlowClass> listAllFlowClass(String owid);
}
