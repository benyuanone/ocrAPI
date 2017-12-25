package com.ourway.sys.api.sql;

import com.ourway.base.CommonConstants;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateMsg;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.Constants;
import com.ourway.sys.model.OurwaySysRoles;
import com.ourway.sys.service.DicService;
import com.ourway.sys.service.PrivsallocateService;
import com.ourway.sys.service.PrivsuserService;
import com.ourway.sys.service.RolesService;
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
import java.util.List;
import java.util.Map;

/**
 * <p>接口 RolesController.java : <p>
 * <p>说明：角色</p>
 * <pre>
 * @author cc
 * @date 2017/4/25 13:02
 * </pre>
 */
@Controller
@RequestMapping("sysSqlApi")
public class GenerateSQLController {


    @Autowired
    DicService dicService;


    /**
     * <p>接口 RolesController.java : <p>
     * <p>说明：保存</p>
     * <pre>
     * @author cc
     * @date 2017/4/25 13:07
     * </pre>
     */
    @RequestMapping(value = "listAllTables", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listAllTables(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        //判断必填项目
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "tableName", "filePath");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //
        dicService.doGenerateSQL(dataMap.get("tableName").toString(), dataMap.get("filePath").toString());

        return ResponseMessage.sendOK("");
    }


}
