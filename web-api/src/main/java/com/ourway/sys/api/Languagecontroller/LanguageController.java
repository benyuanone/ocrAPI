package com.ourway.sys.api.Languagecontroller;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysLanguage;
import com.ourway.sys.model.OurwaySysLanguageMult;
import com.ourway.sys.service.LanguageSer;
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
 * <p>方法 UserController : <p>
 * <p>说明:用户端的管理接口</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/12 2:39
 * </pre>
 */
@Controller
@RequestMapping("sysLanguageApi")
public class LanguageController {
    @Autowired
    LanguageSer languageSer;

    @RequestMapping(value = "saveLanguge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveLanguage(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        //判断必填项目
        if (ValidateUtils.isEmpty(dataMap.get("labelKey"))) {
            return ResponseMessage.sendError(-1, "关键字必须输入");
        }
        //转成的具体的entity
        //组装template及其字表
        OurwaySysLanguage language = JsonUtil.map2Bean(dataMap, OurwaySysLanguage.class);
        List<OurwaySysLanguageMult> languageMults = null;
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            languageMults = JsonUtil.map2List(components, OurwaySysLanguageMult.class);
        }
        language.setDataList(languageMults);
        if (!languageSer.doCheckUniqueLabel(language)) {
            return ResponseMessage.sendError(-1, "关键字必须唯一");
        }

        languageSer.saveOrUpdateLanguage(language);
        return ResponseMessage.sendOK(language);
    }

    @RequestMapping(value = "listLanguge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listLanguage(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(languageSer.listLanguageByPage(filters, data.getPageNo(), data.getPageSize()));
    }


    @RequestMapping(value = "removeLanguge", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeLanguage(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
        List<Map<String, Object>> datas = languageSer.removeItems(owids);
        return ResponseMessage.sendOK(datas);
    }


    /**
     * <p>方法:detailLanguage 根据code获取国际化支持 </p>
     * <ul>
     * <li> @param request 请求</li>
     * <li> @param data 传递的数据</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/24 22:16  </li>
     * </ul>
     */
    @RequestMapping(value = "detailLanguage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailLanguage(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        return ResponseMessage.sendOK(languageSer.detailLanguage(mapData.get("owid").toString()));
    }


    /**
     * <p>方法:realLanguage 制定的code获取对应的多语言 </p>
     * <ul>
     * <p>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/2 15:39  </li>
     * </ul>
     */
    @RequestMapping(value = "realLan", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage realLanguage(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "labelKey");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysLanguageMult lm = I18nUtils.getLanguage(dataMap.get("labelKey").toString().trim(), data.getCurrLanguage());
        if (null == lm)
            return ResponseMessage.sendError(ResponseMessage.FAIL, "");
        //转成的具体的entity
        return ResponseMessage.sendOK(lm);
    }

    @RequestMapping(value = "clearLanguage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage clearLanguage(HttpServletRequest request, PublicDataVO data) {
        I18nUtils.clearLanguage();
        return ResponseMessage.sendOK("");
    }


}
