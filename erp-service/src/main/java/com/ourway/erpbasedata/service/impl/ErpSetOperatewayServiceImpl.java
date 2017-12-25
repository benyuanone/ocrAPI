package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.erpbasedata.dao.ErpSetOperatewayDao;
import com.ourway.erpbasedata.model.ErpSetOperateway;
import com.ourway.erpbasedata.service.ErpSetOperatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*<p>方法 ErpSetOperatewayServiceImpl : <p>
*<p>说明:基础资料-作业方式service实现类</p>
*<pre>
*@author zhou_xtian
*@date 2017-09-22 10:23
</pre>
*/
@Service("ErpSetOperatewayService")
public class ErpSetOperatewayServiceImpl implements ErpSetOperatewayService{

    @Autowired
    private ErpSetOperatewayDao erpSetOperatewayDao;

    @Override
    public PageInfo<ErpSetOperateway> listErpSetOperateway(List<FilterModel> filterModels, int pageNo, int pageSize, String sortStr) {
        return erpSetOperatewayDao.listErpSetOperatewayForPage(filterModels,pageNo,pageSize,sortStr);
    }

    @Override
    public ErpSetOperateway detailErpSetOperateway(Map<String, Object> dataMap) {
        return erpSetOperatewayDao.detailErpSetOperateway(dataMap);
    }

    @Override
    public String checkUnique(Map dataMap,PublicDataVO data) {
        if (2 == dataMap.get("updateFlag")) {
            //删除不做唯一性校验
            return null;
        } else {
            return erpSetOperatewayDao.checkUnique(dataMap,data);
        }
    }

    @Override
    public void saveErpSetOperateway(List<ErpSetOperateway> erpSetOperatewayList) {
        //需要删除的数据
        List<ErpSetOperateway> removeList = new ArrayList<ErpSetOperateway>();
        //新增或修改的数据
        List<ErpSetOperateway> saveOrUpdateList = new ArrayList<ErpSetOperateway>();
        for (ErpSetOperateway erpSetOperateway:erpSetOperatewayList) {
            if (2 == erpSetOperateway.getUpdateFlag()) {
                removeList.add(erpSetOperateway);
                continue;
            }
            saveOrUpdateList.add(erpSetOperateway);
        }
        for (ErpSetOperateway erpSetOperateway:removeList) {
            erpSetOperatewayDao.removeEntity(erpSetOperateway);
        }
        erpSetOperatewayDao.saveOrUpdate(saveOrUpdateList);
    }

    @Override
    public void removeErpSetOperateway(ErpSetOperateway erpSetOperateway) {

    }
}
