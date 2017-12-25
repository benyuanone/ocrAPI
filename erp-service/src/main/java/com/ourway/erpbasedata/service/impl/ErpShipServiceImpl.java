package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.dao.ErpShipDao;
import com.ourway.erpbasedata.dao.ErpShipListDao;
import com.ourway.erpbasedata.model.ErpShip;
import com.ourway.erpbasedata.model.ErpShipList;
import com.ourway.erpbasedata.service.ErpShipService;
import com.ourway.sys.dao.SysFlowDao;
import com.ourway.sys.dao.SysWorkflowDao;
import com.ourway.sys.model.OurwaySysForm;
import com.ourway.sys.model.OurwaySysWorkflow;
import com.ourway.sys.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/***
*<p>方法: TODO </p>
*<ul>
 *<li> @param null TODO</li>
*<li>@return   </li>
*<li>@author zhangxinyi </li>
*<li>@date 2017-09-08 9:36  </li>
*</ul>
*/
@Service("erpShipService")
public class ErpShipServiceImpl implements ErpShipService {

    @Autowired
    ErpShipDao erpShipDao;

    @Autowired
    ErpShipListDao erpShipListDao;
    @Autowired
    BaseProcessService baseProcessService;
    @Autowired
    SysFlowDao sysFlowDao;
    @Autowired
    SysWorkflowDao sysWorkflowDao;

    @Override
    public Boolean doCheckUniqueUrl(ErpShip erpShip) {
        return erpShipDao.doCheckUniqueUrl(erpShip);
    }

    @Override
    public ErpShip saveOrUpdateErpShip(ErpShip erpShip, List<ErpShipList> erpShipListList) {
        erpShipDao.saveOrUpdate(erpShip);
        if (null != erpShipListList && erpShipListList.size() > 0)
            for (ErpShipList erpShipLists : erpShipListList) {
                if (null == erpShipLists.getUpdateFlag()) {
                    erpShipLists.setUpdateFlag(0);
                }
                switch (erpShipLists.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        //什么也不做
                        break;
                    //修改
                    case 1:
                        erpShipLists.setErpshipRefOwid(erpShip.getOwid());
                        // 判断 owid
                        if (TextUtils.isEmpty(erpShipLists.getOwid())) {
                            //为空 新增
                            erpShipListDao.save(erpShipLists);
                        } else {
                            erpShipListDao.update(erpShipLists);
                        }
                        break;
                    //删除
                    case 2:
                        erpShipListDao.removeByIds(erpShipLists.getOwid());
                        break;
                }
            }
        return erpShip;
    }

    @Override
    public List<Map<String, Object>> removeErpShipByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpShip oapi = erpShipDao.getOneByParams(params, null);
            if (null != oapi) {
                //先删子表信息
                erpShipListDao.removeErpShipList(owid);
                erpShipDao.removeEntity(oapi);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public PageInfo<ErpShip> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return erpShipDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public ErpShip detailErpShip(String owid) {
        ErpShip oapi = erpShipDao.getOneById(owid);
        return oapi;
    }

    @Override
    public OurwaySysWorkflow updateStartFlow(String owid) {
        ErpShip form = erpShipDao.getOneById(owid);
        if (null != form.getState() && form.getState() > 0)
            return null;
        form.setState(1);
        erpShipDao.saveOrUpdate(form);
        //启动工作流
        OurwaySysWorkflow workflow = new OurwaySysWorkflow();
        workflow.setBusinessId(form.getOwid());
        workflow.setClassName("com.ourway.erpbasedata.model.ErpShip");
        workflow.setFlowName(form.getChnShipName());
        workflow.setFlowOwner(form.getCreator());
        workflow.setFlowType("测试");
        baseProcessService.startWorkFlow(form, workflow);
        return workflow;
    }

    @Override
    public List<ErpShip> listErpShipByFuzzyQuery(String key) {
        return erpShipDao.listErpShipByFuzzyQuery(key);
    }
}
