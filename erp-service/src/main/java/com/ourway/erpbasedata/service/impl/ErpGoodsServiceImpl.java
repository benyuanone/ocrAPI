package com.ourway.erpbasedata.service.impl;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.erpbasedata.dao.ErpGoodsDao;
import com.ourway.erpbasedata.dao.ErpGoodsListDao;
import com.ourway.erpbasedata.model.ErpGoods;
import com.ourway.erpbasedata.model.ErpGoodsList;
import com.ourway.erpbasedata.service.ErpGoodsService;
import com.ourway.sys.utils.I18nUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***<p>方法 ErpGoodsServiceImpl : <p>
 *<p>说明:TODO</p>
 *<pre>
 *@author CuiLiang
 *@date 2017-05-09 17:01
</pre>
 */
@Service("erpGoodsService")
public class ErpGoodsServiceImpl implements ErpGoodsService {

    @Autowired
    ErpGoodsDao erpGoodsDao;

    @Autowired
    ErpGoodsListDao erpGoodsListDao;

    @Override
    public boolean doCheckUniqueByGoodsId(ErpGoods erpGoods) {
        return erpGoodsDao.doCheckUniqueByGoodsId(erpGoods);
    }

    @Override
    public void saveOrUpdate(ErpGoods erpGoods) {
        erpGoodsDao.saveOrUpdate(erpGoods);
        List<ErpGoodsList> erpGoodsLists = erpGoods.getDataList();
        if (null != erpGoodsLists) {
            for (ErpGoodsList erpGoodsList : erpGoodsLists){
                if (null == erpGoodsList.getErpgoodsRefOwid())
                    erpGoodsList.setErpgoodsRefOwid(erpGoods.getOwid());
//                if (null != erpGoodsList.getErpGuest().getOwid())
//                    erpGoodsList.setErpguestRefOwid(erpGoodsList.getErpGuest().getOwid());
            }
            erpGoodsListDao.saveOrUpdateAll(erpGoodsLists);
        }
    }

    @Override
    public void removeByFilters(List<FilterModel> filterModels) {
        List<ErpGoods> erpGoodss = erpGoodsDao.listAllByParams(filterModels);
        if (null != erpGoodss) {
            for (ErpGoods erpGoods : erpGoodss) {
                erpGoodsDao.removeEntity(erpGoods);
                erpGoodsListDao.removeEntitys(erpGoods.getErpGoodsList());
            }
        }
    }

    @Override
    public void removeByIds(Serializable... ids) {
        erpGoodsDao.removeByIds(ErpGoods.class,ids);
        erpGoodsListDao.removeByRefIds(ids);
    }

    @Override
    public List<Map<String, Object>> removeErpGoodsByIds(List<String> list) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();

        for (String owid : list) {
            Map<String, Object> params = new HashMap<String, Object>(1);
            //先根据 owid 查询 记录
            params.put("owid", owid);
            ErpGoods erpGoods = erpGoodsDao.getOneByParams(params, null);
            if (null != erpGoods) {
                //先删子表信息
                erpGoodsListDao.removeByRefIds(erpGoods.getErpgoodstypeRefOwid());
                erpGoodsDao.removeEntity(erpGoods);
                objs.add(params);
            }
        }
        return objs;
    }

    @Override
    public boolean doCheckRemove(List<FilterModel> filterModels) {
        return erpGoodsDao.doCheckRemove(filterModels);
    }

    @Override
    public PageInfo<ErpGoods> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr) {
        return erpGoodsDao.listHqlForPage(filters,pageNo,pageSize,sortStr);
    }

    @Override
    public ErpGoods getOneById(String id) {
        return erpGoodsDao.getOneById(id);
    }

    @Override
    public List<ErpGoods> listPopGoodsByParams(List<FilterModel> filters) {
        return erpGoodsDao.listPopByParams(filters);
    }

    @Override
    public String getDbMsg(String msg,PublicDataVO dataVO) {
        switch (msg) {
            //属性处理
            case "goodsId":
                return I18nUtils.getLanguageContent("UI.CLN.ErpGoods.goodsId.01",dataVO.getCurrLanguage());
            case "name":
                return I18nUtils.getLanguageContent("UI.CLN.ErpGoods.name.01",dataVO.getCurrLanguage());
            case "erpgoodstypeRefOwid":
                return I18nUtils.getLanguageContent("UI.CLN.ErpGoods.erpgoodstypeRefOwid.01",dataVO.getCurrLanguage());

            //消息提示
            case "0001"://字段不能为空
                return I18nUtils.getLanguageContent("MB.ErpGoods.BaseData.0001",dataVO.getCurrLanguage());
            case "0002"://goodsid重复
                return I18nUtils.getLanguageContent("MB.ErpGoods.BaseData.0002",dataVO.getCurrLanguage());
            case "0003"://因存在生效数据删除失败
                return I18nUtils.getLanguageContent("MB.ErpGoods.BaseData.0003",dataVO.getCurrLanguage());
            case "0004"://返回list查询结果为空
                return I18nUtils.getLanguageContent("MB.ErpGoods.BaseData.0004",dataVO.getCurrLanguage());

            //字典


            default:
                return "";
        }
    }

    @Override
    public ValidateMsg Validate(PublicDataVO dataVO,Map<String, Object> map, String... keys) {
        //提示性校验合法性
        ValidateMsg msg = ValidateUtils.isEmpty(map, keys);
        for(String key:keys){
            msg = ValidateUtils.isEmpty(map, key);
            if (!msg.getSuccess()) {
                msg.setKeys(new StringBuilder(getDbMsg(key,dataVO) + getDbMsg("0001",dataVO)));
                break;
            }
        }
        return msg;
    }


    @Override
    public ErpGoods detailErpGoods(String owid) {
        ErpGoods erpGoods =erpGoodsDao.detailErpGoods(owid);
        if (null == erpGoods) {
            return null;
        }
//        List<ErpGoodsList>  erpGoodsLists = erpGoodsListDao.listAllByRefId(owid);
//        erpGoods.setDataList(erpGoodsLists);
        return erpGoods;
    }

    @Override
    public List<ErpGoods> listPopGoodsByParams(String key) {
        return erpGoodsDao.listAllByParams(key);
    }

    @Override
    public List<ErpGoods> listErpGoodsByFuzzyQuery(String key) {
        return erpGoodsDao.listErpGoodsByFuzzyQuery(key);
    }
}
