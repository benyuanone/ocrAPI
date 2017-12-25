package com.ourway.erpbasedata.api;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.erpbasedata.model.ErpEmployee;
import com.ourway.erpbasedata.service.ErpEmployeeService;
import com.ourway.sys.service.EmploysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**<p>方法 ErpEmployeeController : <p>
*<p>说明:TODO</p>
*<pre>
*@author zhou_xtian
*@date 2017-06-08 10:26
</pre>
*/
@Controller
@RequestMapping("erpEmployeeApi")
public class ErpEmployeeController {

    @Autowired
    ErpEmployeeService erpEmployeeService;

    /*<p>方法: 查询所有员工资料 </p>
    *<ul>
     *<li> @param null </li>
    *<li>@return   </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-07-26 10:26  </li>
    *</ul>
    */
    @RequestMapping(value = "listAllEmployee", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllEmployee(HttpServletRequest request, PublicDataVO dataVO){
        List<FilterModel> filterModels = new ArrayList<FilterModel>();
        if (!TextUtils.isEmpty(dataVO.getData()) && "[".equals(""+dataVO.getData().charAt(0))) {
            filterModels = JsonUtil.jsonToList(dataVO.getData(),FilterModel.class);
        } else {
            Map<String,Object> dataMap = JsonUtil.jsonToMap(dataVO.getData());
            FilterModel filterModel = new FilterModel();
            filterModel.setMap(dataMap);
            filterModels.add(filterModel);
        }
        return ResponseMessage.sendOK(erpEmployeeService.listAllObject(filterModels));
    }
}
