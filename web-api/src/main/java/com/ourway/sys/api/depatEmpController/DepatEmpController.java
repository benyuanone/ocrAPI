package com.ourway.sys.api.depatEmpController;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.service.DepatempService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 DepatEmpController.java : <p>
 * <p>说明：用户部门</p>
 * <pre>
 * @author cc
 * @date 2017/4/25 9:53
 * </pre>
 */
@Controller
@RequestMapping("depatEmpApi")
public class DepatEmpController {

    @Autowired
    private DepatempService depatEmpService;

    /**
     * <p>接口 saveDepatEmp.java : <p>
     * <p>说明：新增<p>
     * <pre>
     * @author cc
     * @date 2017/4/25 10:10
     * </pre>
     */
    @RequestMapping(value = "saveDepatEmp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveDepatEmp(HttpServletRequest request, PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        //不确定哪几个字段需要判断  先判断 ERROR_CHN_NAME
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap,"empPositionId");

        if(!msg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
        }

        OurwaySysDepatemp depatEmp = JsonUtil.map2Bean
                (dataMap,OurwaySysDepatemp.class);
        //生成code
        depatEmp.setOwid(TextUtils.getUUID());
        //判断 errorcode是否唯一
//        if (!depatEmpService.doCheckUniqueLabel(depatEmp)) {
//            return ResponseMessage.sendError(-1, "empPositionId必须唯一");
//        }

        //存入数据库
        depatEmpService.saveOrUpdateDepemp(depatEmp);

        //返回操作成功信息
        return  ResponseMessage.sendOK(depatEmp);
    }


    @RequestMapping(value = "listDepatEmp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listDepatEmp(HttpServletRequest request, PublicDataVO data) {

        List<FilterModel> modelList = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(depatEmpService.listHqlForPage(modelList, data.getPageNo(),data.getPageSize(),"empPositionId"));
    }

    @RequestMapping(value = "detailDepatEmp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailDepatEmp(HttpServletRequest request, PublicDataVO data) {
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap,"owid");
        if(!msg.getSuccess()){
            return ResponseMessage.sendError(ResponseMessage.FAIL,msg.toString());
        }
        return ResponseMessage.sendOK(depatEmpService.detailOneEmp(dataMap.get("owid").toString()));
    }


    @RequestMapping(value = "removeDepatEmp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeDepatEmp(HttpServletRequest request, PublicDataVO data) {
        //判断data 是否为空
        if(TextUtils.isEmpty(data.getData())){
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }

        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }

        List<Map<String,Object>> datas = depatEmpService.removeItems(owids);
        return ResponseMessage.sendOK(datas);
    }
}
