package com.ourway.sys.api.billid;

import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysBillid;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.service.BillIdService;
import com.ourway.sys.service.DicService;
import com.ourway.sys.service.impl.BillIdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("sysBillIdApi")
public class BillIdsController {
    @Autowired
    BillIdService billIdService;
    @Autowired
    DicService dicService;

    @RequestMapping(value = "getBillId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getBillId(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "key");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //去字典表取值，获取配置信息
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("dicVal1", dataMap.get("key").toString());
        Object[] objects = dicService.getSingleDicByType(Constants.DicType.TYPE_7, params);
        if (null == objects)
            return ResponseMessage.sendError(ResponseMessage.FAIL, "不存在当前id的配置信息");
        OurwaySysDicValue dicValue = (OurwaySysDicValue) objects[1];
        String ymd = "";
        String pre = "";
        int len = 0;
        if (!TextUtils.isEmpty(dicValue.getDicVal3()))
            pre = dicValue.getDicVal3();
        if (!TextUtils.isEmpty(dicValue.getDicVal4()))
            ymd = dicValue.getDicVal4();
        if (!TextUtils.isEmpty(dicValue.getDicVal5()))
            len = Integer.parseInt(dicValue.getDicVal5());

        OurwaySysBillid billid = getIdFromRedis(dicValue.getDicVal1(), pre, ymd, len);
        if (null == billid)
            return ResponseMessage.sendError(ResponseMessage.FAIL, "获取不到id信息");
        return ResponseMessage.sendOK(billid);
    }

    //每次在缓存中取一次。
    public synchronized OurwaySysBillid getIdFromRedis(String key, String pre, String ymd, int len) {
        OurwaySysBillid currId = null;
        String _ymd = "";
        if (!TextUtils.isEmpty(ymd))
            _ymd = DateUtil.getDateStr(ymd);
        List<OurwaySysBillid> ids = CacheUtil.getVals(BillIdServiceImpl.BILLID + key + pre + _ymd, OurwaySysBillid.class);
        if (null == ids || ids.size() <= 0) {
            billIdService.saveNewIds(key, pre, ymd, 10, len);
            billIdService.updateListId2Redis(key, pre, ymd, 10);
            ids = CacheUtil.getVals(BillIdServiceImpl.BILLID + key + pre + _ymd, OurwaySysBillid.class);
        }
        List<OurwaySysBillid> removes = new ArrayList<OurwaySysBillid>();
        for (Iterator<OurwaySysBillid> iterable = ids.iterator(); iterable.hasNext(); ) {
            OurwaySysBillid billid = iterable.next();
            if (billid.getBillStatus() == 2) {
                removes.add(billid);
            } else if (billid.getBillStatus() == 1) {
                currId = billid;
                billid.setBillStatus((byte) 2);
                break;
            }
        }
        for (OurwaySysBillid remove : removes) {
            ids.remove(remove);
        }
        if (ids.size() <= 3) {
            billIdService.saveNewIds(key, pre, ymd, 10, len);
            billIdService.updateListId2Redis(key, pre, ymd, 10);
        } else
            CacheUtil.setVal(BillIdServiceImpl.BILLID + key + pre + _ymd, ids);

        return currId;
    }


}
