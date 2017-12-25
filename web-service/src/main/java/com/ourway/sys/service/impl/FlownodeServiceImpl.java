package com.ourway.sys.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.*;
import com.ourway.sys.model.*;
import com.ourway.sys.service.BaseProcessService;
import com.ourway.sys.service.FlowService;
import com.ourway.sys.service.FlownodeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 FlowService.java : <p>
 * <p>说明：功能流</p>
 * <pre>
 * @author cc
 * @date 2017/4/12 14:36
 * </pre>
 */
@Service("flownodeService")
public class FlownodeServiceImpl implements FlownodeService {
    @Autowired
    SysFlowNodeDao sysFlowNodeDao;
    @Autowired
    SysFlowNodePerDao sysFlowNodePerDao;

    @Override
    public PageInfo<OurwaySysFlownode> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize) {
        return sysFlowNodeDao.listAllNodeByFlowPages(filters, pageNo, pageSize);
    }

    @Override
    public OurwaySysFlownode listOneNodeById(String id) {
        return sysFlowNodeDao.getOneById(id);
    }

    @Override
    public boolean saveOrUpdateNodePer(OurwaySysFlownode flownode, List<OurwaySysFlownodePer> pers) {
        sysFlowNodeDao.saveOrUpdate(flownode);
        if (null != pers && pers.size() > 0) {
            for (OurwaySysFlownodePer per : pers) {
                switch (per.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        per.setFlowperRefOwid(flownode.getOwid());
                        // 判断 owid
                        if (TextUtils.isEmpty(per.getOwid())) {
                            //为空 新增
                            sysFlowNodePerDao.save(per);
                        } else {
                            sysFlowNodePerDao.update(per);
                        }
                        break;
                    //删除
                    case 2:
                        sysFlowNodePerDao.removeByIds(per.getOwid());
                        break;
                }
            }
        }

        return true;
    }

    @Override
    public List<Map<String, Object>> removeNodePerByIds(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        Map<String, Object> params = new HashMap<String, Object>(1);
        for(String s:owids){
            if(TextUtils.isEmpty(s))
                continue;
            OurwaySysFlownode node = sysFlowNodeDao.getOneById(s);
            if(null==node)
                continue;
            params.put("flowperRefOwid",node.getOwid());
            sysFlowNodePerDao.removeByParams(params);
            sysFlowNodeDao.removeEntity(node);
            Map<String, Object> _params = new HashMap<String, Object>(1);
            _params.put("owid", node.getOwid());
            objs.add(_params);
        }

        return objs;
    }
}
