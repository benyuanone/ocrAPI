package com.ourway.sys.api.messagecontroller;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysMessage;
import com.ourway.sys.service.MessageService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统中的业务类
 * Created by jackson on 17-4-27.
 */
@Controller
@RequestMapping("sysMessageApi")
public class MessageController {

    @Autowired
    MessageService messageService;

    /**
     * <p>方法:listAll 显示所有的类别，树形显示 </p>
     * <ul>
     * <li> @param type TODO</li>
     * <li> @param request TODO</li>
     * <li> @param data TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/29 9:37  </li>
     * </ul>
     */
    @RequestMapping(value = "listAllTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllTask(HttpServletRequest request, PublicDataVO data) {
        if (TextUtils.isEmpty(data.getOpenId())) {
            return ResponseMessage.sendError(-1, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        }
        List<Object> datas = new ArrayList<Object>(1);
        datas.add(data.getOpenId());
        FilterModel model = FilterModel.instance("messPerId", FilterModel.EQUALS, datas);
        datas = new ArrayList<Object>(2);
        datas.add(0);
        datas.add(1);
        FilterModel modelState = FilterModel.instance("state", FilterModel.AREA, datas);
        List<FilterModel> models = new ArrayList<FilterModel>(1);
        models.add(model);
        models.add(modelState);
        PageInfo<OurwaySysMessage> messagePageInfo = messageService.listMessForPage(models, data.getPageNo(), data.getPageSize(), " messTime desc");
        return ResponseMessage.sendOK(messagePageInfo);
    }

    @RequestMapping(value = "readMessage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage readMessage(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (ValidateUtils.isEmpty(dataMap.get("owid"))) {
            return ResponseMessage.sendError(-1, I18nUtils.getLanguageContent(Constants.PARAM_LACK, data.getCurrLanguage()));
        }
        messageService.updateReadMessage(dataMap.get("owid").toString());
        return ResponseMessage.sendOK("");
    }

    @RequestMapping(value = "listAllTasks", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllTasks(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);

        List<Object> datas = new ArrayList<Object>(1);
        datas.add(data.getOpenId());
        FilterModel model = FilterModel.instance("messPerId", FilterModel.EQUALS, datas);
        filters.add(model);
        PageInfo<OurwaySysMessage> messagePageInfo = messageService.listMessForPage(filters, data.getPageNo(), data.getPageSize(), " messTime desc");
        return ResponseMessage.sendOK(messagePageInfo);
    }

    @RequestMapping(value = "removeTask", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeTask(HttpServletRequest request, PublicDataVO data) {
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        String owid = "";
        for (Object obj : list) {
            owid = ((Map<String, Object>) obj).get("owid").toString();
            owids.add(owid);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("owid", owid);
            result.add(map);
        }
        messageService.removeMessage(owids);
        return ResponseMessage.sendOK(result);
    }


}
