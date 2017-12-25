package com.ourway.sys.service.impl;

import com.ourway.sys.dao.SysFlowClassDao;
import com.ourway.sys.model.OurwaySysFlowClass;
import com.ourway.sys.service.FlowClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 FlowClassService.java : <p>
 * <p>说明：业务类工作流配置</p>
 * <pre>
 * @author cc
 * @date 2017/4/12
 * </pre>
 */
@Service("flowClassService")
public class FlowClassServiceImpl implements FlowClassService {
    @Autowired
    SysFlowClassDao sysFlowClassDao;

    @Override
    public List<OurwaySysFlowClass> listAllFlowClass(String owid) {
        return sysFlowClassDao.listFowClassByOwid(owid);
    }
}
