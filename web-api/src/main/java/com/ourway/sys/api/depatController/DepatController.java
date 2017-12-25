package com.ourway.sys.api.depatController;

import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.sys.model.OurwaySysDepat;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysMenus;
import com.ourway.sys.service.DepatService;
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
 * <p>接口 DepartController.java : <p>
 * <p>说明：部门管理</p>
 * <pre>
 * @author cc
 * @date 2017/4/21 9:37
 * </pre>
 */
@Controller
@RequestMapping("sysDepartApi")
public class DepatController {
    @Autowired
    DepatService depatService;

    @RequestMapping(value = "saveDepart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveDepart(HttpServletRequest request, PublicDataVO dataVo) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVo.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "depName", "fid", "path","px","cc");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysDepat depat = new OurwaySysDepat();
        depat = BeanUtil.map2Obj(dataMap,OurwaySysDepat.class);
//        depat.setFid(Integer.parseInt(dataMap.get("fid").toString()));
//        depat.setDepName(dataMap.get("depName").toString());
//        depat.setPath(dataMap.get("path").toString());
//        if (!TextUtils.isEmpty(dataMap.get("px")))
//            depat.setPx(Double.parseDouble(dataMap.get("px").toString()));
//        if (!TextUtils.isEmpty(dataMap.get("cc")))
//            depat.setCc(Integer.parseInt(dataMap.get("cc").toString()));

        depatService.saveOrUpdate(depat);
        depat.setPath(depat.getPath() + depat.getOwid());
        depatService.saveOrUpdate(depat);
        BaseTree tree = new BaseTree();
        tree.setOwid(depat.getOwid());
        tree.setId(depat.getOwid());
        tree.setFid(depat.getFid());
        tree.setName(depat.getDepName());
        tree.setPath(depat.getPath());
        tree.setPx(depat.getPx());
        tree.setCc(depat.getCc());
        return ResponseMessage.sendOK(tree);
    }

    @RequestMapping(value = "updateDepart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateDepart(HttpServletRequest request, PublicDataVO dataVo) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVo.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "depNo", "depType", "depName");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysDepat depat = JsonUtil.map2Bean(dataMap, OurwaySysDepat.class);
        if (!TextUtils.isEmpty(dataMap.get("depLogo"))) {
            if (dataMap.get("depLogo") instanceof Map) {
                Map<String, Object> picMap = (Map<String, Object>) dataMap.get("depLogo");
                if (null != picMap && !TextUtils.isEmpty(picMap.get("filePath")))
                    depat.setDepLogo(picMap.get("filePath").toString());
            }
        }
        depatService.saveOrUpdate(depat);
        BaseTree tree = new BaseTree();
        tree.setOwid(depat.getOwid());
        tree.setId(depat.getOwid());
        tree.setFid(depat.getFid());
        tree.setName(depat.getDepNo() + "-" + depat.getDepName());
        tree.setPath(depat.getPath());
        return ResponseMessage.sendOK(tree);
    }


    //部门属性管理
    @RequestMapping(value = "listTreeDepart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listTreeDepart(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(depatService.listTree(filters));
    }

    /**
     * <p>接口 removeDepat.java : <p>
     * <p>说明：删除<s/p>
     * <pre>
     * @author cc
     * @date 2017/4/25 9:20
     * </pre>
     */
    @RequestMapping(value = "removeDepat", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removeDepat(HttpServletRequest request, PublicDataVO dataVo) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(dataVo.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        //转成的具体的entity
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("owid", dataMap.get("owid").toString());
        depatService.removeDepart(map);
        return ResponseMessage.sendOK(map);
    }

    /**
     * <p>接口 detailDepat.java : <p>
     * <p>说明：部门信息和用户部门</p>
     * <pre>
     * @author cc
     * @date 2017/4/25 9:20
     * </pre>
     */
    @RequestMapping(value = "detailDepat", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage detailDepat(HttpServletRequest request, PublicDataVO data) {
        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ValidateMsg msg = ValidateUtils.isEmpty(dataMap, "owid");
        if (!msg.getSuccess()) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, msg.toString());
        }
        OurwaySysDepat depat = depatService.getDeptById(Integer.parseInt(dataMap.get("owid").toString()));
        return ResponseMessage.sendOK(depat);
    }


    /**
     * <p>方法:listBandboxTree 树形的bandbox部门列表 </p>
     * <ul>
     * <li> @param request TODO</li>
     * <li> @param data TODO</li>
     * <li>@return com.ourway.base.model.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/10/4 15:31  </li>
     * </ul>
     */
    @RequestMapping(value = "listBandboxTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listBandboxTree(HttpServletRequest request, PublicDataVO data) {
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        return ResponseMessage.sendOK(depatService.listTree(filters));
    }

    //用于节点移动等操作
    @RequestMapping(value = "updateMoveDepart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateMoveMenu(HttpServletRequest request, PublicDataVO data) {
        System.out.println(data.getData());
        List<Map> datas = JsonUtil.jsonToList(data.getData(), Map.class);

        List<OurwaySysDepat> ourwaySysDepats = new ArrayList<OurwaySysDepat>(datas.size());
        for (Map menuData : datas) {
            OurwaySysDepat depat = JsonUtil.map2Bean(menuData, OurwaySysDepat.class);
            ourwaySysDepats.add(depat);
        }
        if (null == ourwaySysDepats || ourwaySysDepats.size() <= 0) {
            return ResponseMessage.sendError(ResponseMessage.FAIL, "");
        }
        depatService.update(ourwaySysDepats);
        return ResponseMessage.sendOK(null);
    }

}
