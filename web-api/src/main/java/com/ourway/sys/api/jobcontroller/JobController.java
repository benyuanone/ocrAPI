package com.ourway.sys.api.jobcontroller;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysApi;
import com.ourway.sys.model.OurwaySysApiDetail;
import com.ourway.sys.model.OurwaySysDicValue;
import com.ourway.sys.model.OurwaySysJob;
import com.ourway.sys.service.ApiDetailService;
import com.ourway.sys.service.ApiService;
import com.ourway.sys.service.JobService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <dl>
 * <dt>ApiController 系统接Api controller</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 2017/4/24 0024</dd>
 * </dl>
 *
 * @author xby
 */

@Controller
@RequestMapping("sysJobApi")
public class JobController {

    @Autowired
    JobService jobService;


    /*
    *<p>功能描述：saveOrUpdateApi 保存系统api接口信息主表</p >
    *<ul>
    *<li>@param []</li>
    *<li>@return com.ourway.base.model.ResponseMessage</li>
    *<li>@throws </li>
    *<li>@author xuby</li>
    *<li>@date 2017/4/24 0024 下午 1:14</li>
    *</ul>
    */

    @RequestMapping(value = "saveJob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveJob(HttpServletRequest request, HttpServletResponse response,
                                   PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());

        List<OurwaySysJob> dicValues = new ArrayList<OurwaySysJob>();
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            dicValues = JsonUtil.map2List(components, OurwaySysJob.class);
        }
        if (dicValues.size() > 0)
            jobService.saveOrUpdateAll(dicValues);
        return ResponseMessage.sendOK(null);
    }

    @RequestMapping(value = "listJobs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listJobs(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        String orderColumn = "";
        if (!TextUtils.isEmpty(dataMap.get("param")))
            orderColumn = dataMap.get("param").toString();
        return ResponseMessage.sendOK(jobService.listJobs(orderColumn));
    }

}
