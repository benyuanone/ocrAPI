package com.ourway.sys.api.attachfile;

import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.*;
import com.ourway.sys.service.*;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
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
@RequestMapping("sysAttachFileApi")
public class AttachFileController {
    @Autowired
    FilesService filesService;


    @RequestMapping(value = "saveFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveFile(HttpServletRequest request, HttpServletResponse response,
                                    PublicDataVO data) {
        // json 转map
        Map<String, Object> mapData = JsonUtil.jsonToMap(data.getData());
        //判断必填字段
        ValidateMsg msg = ValidateUtils.isEmpty(mapData, "fileClassId", "fileName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //map to enity
        OurwaySysFiles ourwaySysFiles = JsonUtil.map2Bean(mapData, OurwaySysFiles.class);
        filesService.saveOrUpdate(ourwaySysFiles);
        return ResponseMessage.sendOK(ourwaySysFiles.getOwid());
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage list(HttpServletRequest request, HttpServletResponse response,
                                PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(filesService.listAllFiles(filters, "fileName"));
    }


    @RequestMapping(value = "remove", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage remove(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> owids = JsonUtil.jsonToList(jsonArray);
//        List<String> owids = new ArrayList<String>(list.size());
//        for (Object obj : list) {
//            owids.add(((Map<String, Object>) obj).get("owid").toString());
//        }
        List<Map<String, Object>> datas = filesService.removeFile(owids);
        return ResponseMessage.sendOK(datas);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage update(HttpServletRequest request, HttpServletResponse response,
                                       PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<OurwaySysFiles> owids = JsonUtil.jsonToList(jsonArray,OurwaySysFiles.class);
        filesService.updateFile(owids);
        return ResponseMessage.sendOK("OK");
    }


    @RequestMapping(value = "pageFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listFilesByPage(HttpServletRequest request, HttpServletResponse response,
                                PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(filesService.listAllFilesByPages(filters, "fileName",data.getPageSize(),data.getPageNo()));
    }

    @RequestMapping(value = "saveAllFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveAllFiles(HttpServletRequest request, HttpServletResponse response,
                                           PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        if (null==dataMap||null==dataMap.get("dataList")) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        }
        List<OurwaySysFiles> svFiles = new ArrayList<OurwaySysFiles>();
        if (null != dataMap.get("dataList")) {
            List<Map<String, Object>> components = (List<Map<String, Object>>) dataMap.get("dataList");
            for(Map<String,Object> component:components){
               if(null!=component.get("filePath")){
                   Map<String,Object> map = (Map<String,Object>)component.get("filePath");
                   component.put("filePath",map.get("filePath").toString());
                   component.put("fileSize",map.get("fileSize").toString());
                   component.put("fileExtion",map.get("fileExtion").toString());
                   component.put("fileMd5",map.get("fileMd5").toString());
                   component.put("fileRandon",map.get("fileRandon").toString());
                   component.put("fileDown",map.get("fileDown").toString());
               }
            }
            svFiles = JsonUtil.map2List(components, OurwaySysFiles.class);
        }
        filesService.saveOrUpdateAll(svFiles);
        return ResponseMessage.sendOK("");
    }

    @RequestMapping(value = "removeFiles", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeFiles(HttpServletRequest request, HttpServletResponse response,
                                     PublicDataVO data) {
        if (TextUtils.isEmpty(data.getData()))
            return ResponseMessage.sendError(ResponseMessage.FAIL, I18nUtils.getLanguageContent(Constants.PARAM_LACK,data.getCurrLanguage()));
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<Object> owids = new ArrayList<Object>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid"));
        }
        List<Map<String, Object>> datas = filesService.removeFile(owids);
        return ResponseMessage.sendOK(datas);
    }

}
