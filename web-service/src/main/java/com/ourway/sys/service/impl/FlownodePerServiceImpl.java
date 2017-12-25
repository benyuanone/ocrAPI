package com.ourway.sys.service.impl;

import com.ourway.sys.dao.SysFlowNodePerDao;
import com.ourway.sys.model.OurwaySysFlownode;
import com.ourway.sys.model.OurwaySysFlownodePer;
import com.ourway.sys.service.FlownodePerService;
import com.ourway.sys.service.FlownodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>接口 FlowService.java : <p>
 * <p>说明：功能流</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:36
 * </pre>
 */
@Service("flownodePerService")
public class FlownodePerServiceImpl implements FlownodePerService {

    @Autowired
    SysFlowNodePerDao sysFlowNodePerDao;
    @Override
    public List<OurwaySysFlownodePer> listAllPerByNode(OurwaySysFlownode flownode) {
        return sysFlowNodePerDao.listAllPerByNode(flownode);
    }
}
