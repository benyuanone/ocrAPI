package com.ourway.erpbasedata.api;

import com.ourway.ERPTipsConstants;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.model.PublicDataVO;
import com.ourway.base.model.ResponseMessage;
import com.ourway.base.utils.*;
import com.ourway.erpbasedata.model.ErpCarsite;
import com.ourway.erpbasedata.model.ErpEmployee;
import com.ourway.erpbasedata.model.ErpPipe;
import com.ourway.erpbasedata.service.ErpCarsiteService;
import com.ourway.erpbasedata.service.ErpPipeService;
import com.ourway.sys.Constants;
import com.ourway.sys.service.FilesService;
import com.ourway.sys.utils.I18nUtils;
import net.sf.json.JSON;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin on 2017-09-11.
 */

/*<p>方法 ErpPipeController : <p>
*<p>说明:TODO</p>
*<pre>
*@author Kevin
*@date 2017-09-11 11:34
</pre>
*/
@Controller
@RequestMapping("pipeApi")
public class ErpPipeController {
    @Autowired
    ErpPipeService erpPipeService;

    /*<p>方法  : 保存<p>
    *<p>说明:TODO</p>
    *<pre>
    *@author Kevin
    *@date 2017-09-11 13:31
    </pre>
    */
    @RequestMapping(value = "savePipe",method = RequestMethod.POST)
    @ResponseBody
    public  ResponseMessage saveErpPipe(HttpServletRequest request,PublicDataVO data){
        Map<String,Object> dataMap = JsonUtil.jsonToMap(data.getData());
        ErpPipe temp = JsonUtil.map2Bean(dataMap,ErpPipe.class);

        List<ErpPipe> dicValue = new ArrayList<>();
        if (null != dataMap.get("dataList")) {
            List<Map<String,Object>> component = (List<Map<String,Object>>) dataMap.get("dataList");
            for(Map<String,Object> map:component){
                ErpPipe pipe =   JsonUtil.map2Bean(map,ErpPipe.class);
               if(!TextUtils.isEmpty(map.get("tree"))){
                   Map<String,Object> treeMap = (Map<String,Object>)map.get("tree");
                   pipe.setType(Integer.parseInt(treeMap.get("owid").toString()));
               }
            }
//            dicValue = JsonUtil.map2List(component,ErpPipe.class);

            if(!erpPipeService.doCheckUniqueUrl(temp)){
                return  ResponseMessage.sendError(ResponseMessage.FAIL,I18nUtils.getLanguageContent(ERPTipsConstants.CURRENCYTYPEID_UNIQUE,data.getCurrLanguage()));
            }
        }
        erpPipeService.saveOrUpdatePipe(dicValue);
        return ResponseMessage.sendOK(dicValue);
    }
//待删除
    @RequestMapping(value = "listPipe1",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listPipe1(HttpServletRequest request, HttpServletResponse response,PublicDataVO data){
        Map<String,Object>dataMap = JsonUtil.jsonToMap(data.getData());
        return ResponseMessage.sendOK(erpPipeService.listPipe(dataMap));
    }
    /*<p>方法 ErpPipeController : 列表加载<p>
    *<p>说明:TODO</p>
    *<pre>
    *@author Kevin
    *@date 2017-09-12 13:40
    </pre>
    */
    @RequestMapping(value = "listPipe",method = RequestMethod.POST)
    @ResponseBody
    public  ResponseMessage listPipe(HttpServletRequest request,HttpServletResponse response,PublicDataVO data){
        List<FilterModel> filters = JsonUtil.jsonToList(data.getData(), FilterModel.class);
        List<FilterModel> useFilters = new ArrayList<FilterModel>();
        for(FilterModel filter:filters){
             if(filter.getKey().equalsIgnoreCase("type")){
                 List<Object> _objs = new ArrayList<Object>(1);
                 _objs.add(Integer.parseInt(filter.getDatas().get(0).toString()));
                 filter.setDatas(_objs);
                 useFilters.add(filter);
             }else{
                 useFilters.add(filter);
             }
        }
        return ResponseMessage.sendOK(erpPipeService.listHqlForPage(useFilters, data.getPageNo(), data.getPageSize(),""));
    }
    /*<p>方法  : 删除 <p>
    *<p>说明:TODO</p>
    *<pre>
    *@author Kevin
    *@date 2017-09-12 13:40
    </pre>
    */
    @RequestMapping(value = "removePipe", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage removePipe(HttpServletRequest request, HttpServletResponse response,
                                        PublicDataVO data) {
        JSONArray jsonArray = JSONArray.fromObject(data.getData());
        List<Object> list = JsonUtil.jsonToList(jsonArray);
        List<String> owids = new ArrayList<String>(list.size());
        for (Object obj : list) {
            owids.add(((Map<String, Object>) obj).get("owid").toString());
        }
//      List<Map<String, Object>> datas = erpPipeService.removeItems(owids);
        return ResponseMessage.sendOK(erpPipeService.removePipe(owids));
    }

    @RequestMapping(value = "treePipe", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage treePipe(HttpServletRequest request, PublicDataVO data) {
        List<ErpPipe> erpCarsiteList = erpPipeService.listAllPipeType();
        List<BaseTree> baseTrees = new ArrayList<BaseTree>(erpCarsiteList.size());
        int index = 0;
        for (ErpPipe erpCarsite : erpCarsiteList) {
            erpCarsite.setTreeId1(erpCarsite.getTreeId1()+index);
            BaseTree _baseTree = TreeUtils.convert(erpCarsite, new String[]{"treeId1-owid", "treeId2-fid", "pipeTo-name", "pipeTo-path"});
            //添加不重复树数据
            List<String> pathList = new ArrayList<String>();
            for (BaseTree baseTree : baseTrees) {
                pathList.add(baseTree.getPath());
            }
            if (pathList.size() == 0 || !pathList.contains(_baseTree.getPath())) {
                baseTrees.add(_baseTree);
            }
            index++;
        }
        return ResponseMessage.sendOK(baseTrees);
    }

    @RequestMapping(value = "listPipeTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage listPipeTree( HttpServletRequest request, PublicDataVO data) {

        Map<String, Object> dataMap = JsonUtil.jsonToMap(data.getData());
        String orderColumn = "";
//        if (!TextUtils.isEmpty(dataMap.get("param")))
//            orderColumn = dataMap.get("param").toString();
//        暂时只是中文树，sql写在了daoImp里。
        List<Map<String, Object>> datas = erpPipeService.listDicByType(1012, orderColumn);
        List<BaseTree> treeList = new ArrayList<BaseTree>();
        int index = 1;
        for (Map<String, Object> map : datas) {
            BaseTree bt = new BaseTree();
            bt.setOwid(index);
            bt.setFid(-1);
            bt.setName(map.get("dicVal1").toString());
//            bt.setId(Integer.parseInt(map.get("DicRefOwid").toString()));
            bt.setId(Integer.parseInt((new Integer(index)).toString()));
            bt.setPath(map.get("dicVal1").toString());
            treeList.add(bt);
            index++;
        }
        return ResponseMessage.sendOK(treeList);
    }
}
