package com.ourway.sys.service;

import com.ourway.sys.model.OurwaySysFlownode;
import com.ourway.sys.model.OurwaySysFlownodePer;

import java.util.List;

/**<p>接口 FlowService.java : <p>
 *<p>说明：功能流</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 14:36
</pre>
 */
public interface FlownodePerService {

    List<OurwaySysFlownodePer> listAllPerByNode(OurwaySysFlownode flownode);
}
