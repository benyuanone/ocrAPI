package com.ourway.erpbasedata.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.erpbasedata.model.ErpPipe;

import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-09-11.
 */
public interface ErpPipeService {
    public Boolean doCheckUniqueUrl(ErpPipe erpPipe);

    void  saveOrUpdatePipe (List<ErpPipe> erpPipe);

    List<ErpPipe> listPipe(Map<String,Object> params);

    List<Map<String,Object>> removePipeByIds(List<String>list);//not used

    List<Map<String,Object>> removePipe(List<String> owids);

    List<ErpPipe> listAllPipeType();

    List<Map<String, Object>> listDicByType(Integer type, String orderBy);

    PageInfo<ErpPipe> listHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

    List<ErpPipe> NewlistHqlForPage(List<FilterModel> filters, int pageNo, int pageSize, String sortStr);

}
