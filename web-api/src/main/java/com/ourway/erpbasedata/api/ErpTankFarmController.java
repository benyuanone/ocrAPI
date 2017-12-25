package com.ourway.erpbasedata.api;

import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.erpbasedata.model.ErpPipe;
import com.ourway.erpbasedata.model.ErpTankFarm;
import com.ourway.erpbasedata.service.ErpTankFarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/*<p>方法 ErpTankFarmController : <p>
*<p>说明:TODO</p>
*<pre>
*@author Kevin
*@date 2017-09-27 14:04
</pre>
*/

@Controller
@RequestMapping("tankFarmApi")
public class ErpTankFarmController {

    @Autowired
    private ErpTankFarmService erpTankFarmService;

    @RequestMapping(value = "detailTankFarm", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailTankFarm(HttpServletRequest request, PublicDataVO data) {
        //json转map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //获取对象
        Map<String, Object> _map = new HashMap<String, Object>();
//        _map.put("tankfarmId","1");
        ErpTankFarm erpCarsite = erpTankFarmService.detailErpCarsite(_map);
        return ResponseMessage.sendOK(erpCarsite);
    }

    @RequestMapping(value = "saveTankFarm",method = RequestMethod.POST)
    @ResponseBody
    public  ResponseMessage saveTankFarm(HttpServletRequest request,PublicDataVO data){
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        List<Map <String,Object>> temp =  new ArrayList<Map<String,Object>>();
        temp.add(dataMap);
        ErpTankFarm tankFarm = JsonUtil.map2List(temp, ErpTankFarm.class).get(0);
        erpTankFarmService.saveOrUpdate(tankFarm);
        return ResponseMessage.sendOK(null);
    }
}
