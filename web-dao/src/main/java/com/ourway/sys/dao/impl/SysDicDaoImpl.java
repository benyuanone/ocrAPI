package com.ourway.sys.dao.impl;

import com.ourway.base.CommonConstants;
import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseEntity;
import com.ourway.base.model.FilterModel;
import com.ourway.base.service.impl.BaseServiceImpl;
import com.ourway.base.utils.BeanUtil;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.base.utils.ValidateUtils;
import com.ourway.sys.dao.SysDicDao;
import com.ourway.sys.model.OurwaySysDic;
import com.ourway.sys.model.OurwaySysDicValue;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**<p>接口 SysDicDaoImpl.java : <p>
 *<p>说明：通用字典表</p>
 *<pre>
 *@author cc
 *@date  2017/4/12 15:42
</pre>
 */
@Repository("sysDicDao")
public class SysDicDaoImpl  extends BaseServiceImpl<OurwaySysDic> implements SysDicDao {

    @Override
    public void removeSysDic(OurwaySysDic dic) {
        String hql = " delete from OurwaySysDicValue where dicRefOwid=:dicRefOwid";
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("dicRefOwid",dic.getOwid());
        updateBulk(hql,params);
        removeEntity(dic);
    }



    @Override
    public OurwaySysDic getSingleDicByType(int type) {
        String hql =  "from OurwaySysDic where type = ?";
        return getOneByHql(hql,type);
    }

    @Override
    public String getOneRecoredName(Integer roleType) {
        String hql =  " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type="+ CommonConstants.ROLES_TYPE+" and a.owid=:owid ";
        Map<String,Object> param=new HashMap<String,Object> ();
        param.put("owid",roleType);
        List<Object[]> objs = listObjsAllByHql(hql, param);
        if(null!=objs && objs.size()==1){
            Object[] objects=objs.get(0);
            OurwaySysDic dic = (OurwaySysDic) objects[0];
            OurwaySysDicValue val = (OurwaySysDicValue) objects[1];
            return val.getDicVal1();
        }
        return "";
    }

    public List<Map<String, Object>> listAllDataByType(Integer type,String orderBy) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=:type";
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("type", type);
        if(!TextUtils.isEmpty(orderBy))
            hql +=" order by "+orderBy;
        List<Object[]> objs = listObjsAllByHql(hql, params);
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(objs.size());
        for (Object[] objects : objs) {
            OurwaySysDic dic = (OurwaySysDic) objects[0];
            OurwaySysDicValue val = (OurwaySysDicValue) objects[1];
            Map<String, Object> dataMap = new HashMap<String, Object>();
            BeanUtil.obj2Map(val, dataMap);
            BeanUtil.obj2Map(dic, dataMap);
            datas.add(dataMap);
        }
        return datas;
    }

    @Override
    public List<Map<String, Object>> listAllLanguageDataByType(Integer type,String language, String orderBy) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=:type and b.language=:language";
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("type", type);
        params.put("language", language);
        if(!TextUtils.isEmpty(orderBy))
            hql +=" order by "+orderBy;
        List<Object[]> objs = listObjsAllByHql(hql, params);
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(objs.size());
        for (Object[] objects : objs) {
            OurwaySysDic dic = (OurwaySysDic) objects[0];
            OurwaySysDicValue val = (OurwaySysDicValue) objects[1];
            Map<String, Object> dataMap = new HashMap<String, Object>();
            BeanUtil.obj2Map(val, dataMap);
            BeanUtil.obj2Map(dic, dataMap);
            datas.add(dataMap);
        }
        return datas;
    }

    @Override
    public Object[] getSingleDicByType(Integer type,Map<String, Object> params) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type="+type;
        Set<String> set = params.keySet();
        for(String  s:set){
            if(s.equalsIgnoreCase(BaseEntity.PO_ID)||s.equalsIgnoreCase("code"))
                hql +=" and a."+s+"=:"+s;
            else
                hql +=" and b."+s+"=:"+s;
        }
        List<Object[]> objs = listObjsAllByHql(hql,params);
        if(null!=objs&&objs.size()>0)
            return objs.get(0);
        else
            return null;
    }


    @Override
    public List<OurwaySysDic> listAllOwidByPath(String path) {
        String hql = " from OurwaySysDic where path like :path";
        Map<String,Object> params = new HashMap<String,Object>(1);
        params.put("path",path);
        List<OurwaySysDic> dics = listAllByHql(hql,params);
        return dics;
    }

    @Override
    public PageInfo<Object[]> listAllDicForPage(List<FilterModel> models, Integer type, int pageNo, int pageSize,String sortStr) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type="+type;
        HqlStatement hqlStatement = new HqlStatement(hql,models,sortStr);
        return listObjectHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
    }

    @Override
    public PageInfo<Object[]> listAllLikeDicForPage(String key, Integer type, int pageNo, int pageSize, String sortStr) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type="+type;
        Map<String,Object> params = new HashMap<String,Object>();
        if(!TextUtils.isEmpty(key)) {
            hql += " and (b.dicVal1 like :dicVal1 or b.dicVal2 like :dicVal2)";
            params.put("dicVal1","%"+key+"%");
            params.put("dicVal2","%"+key+"%");
        }
        String countHql = "select count(*) "+hql;
        if(!TextUtils.isEmpty(sortStr))
            hql +=" order by "+sortStr;
        return listObjectHqlForPage(hql,"select count(*) "+hql,params,pageNo,pageSize);
    }

    @Override
    public PageInfo<Object[]> listBusinessDic(List<FilterModel> models, int pageNo, int pageSize) {
        String hql = " FROM OurwaySysDic o1 WHERE NOT EXISTS " +
                "(SELECT dic FROM OurwaySysDic dic WHERE type=o1.type AND owid>o1.owid) AND type>1000 ";
        HqlStatement hqlStatement = new HqlStatement(hql,models);
        return listObjectHqlForPage(hqlStatement.getHql(),hqlStatement.getCountHql(),hqlStatement.getParams(),pageNo,pageSize);
    }

    @Override
    public OurwaySysDic detailBusinessDic(Map<String, Object> dataMap) {
        //根据owid获取type
        HqlStatement hqlStatement = new HqlStatement(OurwaySysDic.class,dataMap);
        List<Object> ourwaySysDicList = listObjAllByHql(hqlStatement.getHql(),hqlStatement.getParams());
        OurwaySysDic ourwaySysDic = (OurwaySysDic) ourwaySysDicList.get(0);
        //根据type查询divValue表信息
        PageInfo<Object[]> pageInfo = listAllLikeDicForPage(null,ourwaySysDic.getType(),1,10000,"");
        OurwaySysDic newOurwaySysDic = (OurwaySysDic) pageInfo.getRecords().get(0)[0];
        List<OurwaySysDicValue> newOurwaySysDicValueList = new ArrayList<OurwaySysDicValue>();
        for (Object[] obj : pageInfo.getRecords()) {
            newOurwaySysDicValueList.add((OurwaySysDicValue)obj[1]);
        }
//        newOurwaySysDic.setDataList(newOurwaySysDicValueList);

        return newOurwaySysDic;
    }

    /*<p>方法: type重复性校验
    *<ul>
    *<li> @param ourwaySysDic 过滤条件</li>
    *<li>@return type是否唯一 </li>
    *<li>@author zhou_xtian </li>
    *<li>@date 2017-08-15 11:06  </li>
    *</ul>
    */
    @Override
    public boolean doUniqueCheckType(OurwaySysDic ourwaySysDic) {
        String hql = " from OurwaySysDic where type=:type";
        if (!ValidateUtils.isEmpty(ourwaySysDic.getOwid()))
            hql = " from OurwaySysDic where type=:type and owid<>:owid";
        Map<String, Object> _params = new HashMap<String, Object>();
        _params.put("type", ourwaySysDic.getType());
        _params.put("owid", ourwaySysDic.getOwid());
        List<OurwaySysDic> result = listAllByHql(hql, _params);
        return !(null != result && result.size() > 0);
    }

    @Override
    public PageInfo<OurwaySysDic> listAllDicByPages(List<FilterModel> filters, Integer pageNo, Integer pageSize) {
        HqlStatement hql = new HqlStatement("from OurwaySysDic ", filters);
        return listHqlForPage(hql.getHql(), hql.getCountHql(), hql.getParams(), pageNo, pageSize);
    }

    @Override
    public List<Map<String, Object>> listLanguageDicByDicList(List<String> dicList,Integer type,String language, String orderBy) {
        String hql = " from OurwaySysDic a,OurwaySysDicValue b where a.owid=b.dicRefOwid and a.type=:type and b.language=:language and a.code in :code";
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("type", type);
        params.put("language", language);
        params.put("code", dicList);
        if(!TextUtils.isEmpty(orderBy))
            hql +=" order by "+orderBy;
        List<Object[]> objs = listObjsAllByHql(hql, params);
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>(objs.size());
        for (Object[] objects : objs) {
            OurwaySysDic dic = (OurwaySysDic) objects[0];
            OurwaySysDicValue val = (OurwaySysDicValue) objects[1];
            Map<String, Object> dataMap = new HashMap<String, Object>();
            BeanUtil.obj2Map(val, dataMap);
            BeanUtil.obj2Map(dic, dataMap);
            datas.add(dataMap);
        }
        return datas;
    }

    @Override
    public void doGenerateSQL(String tables,String filePath) {
        String[] _tables = tables.split("\\,");
        String sql = "";
        StringBuilder sb1 = null;
        StringBuilder sb2 = null;
        List<String> allDatas = new ArrayList<String>();
        for (String table:_tables) {
            if(TextUtils.isEmpty(table))
                continue;
            sql = " SELECT * from "+table ;
            List<Map<String,Object>> datas = listBySql(sql);
            for (Map<String, Object> map :  datas) {
                sb1 = new StringBuilder();
                sb2 = new StringBuilder();
                allDatas.add("DELETE FROM "+table+" WHERE OWID='"+map.get("OWID")+"';\n" );
                Set<String> set = map.keySet();
                for (String s:set ) {
                    sb1.append(s+",");
                    if(TextUtils.isEmpty(map.get(s))){
                        sb2.append("NULL,");
                    }else {
                        if (map.get(s) instanceof String || map.get(s) instanceof Date)
                            sb2.append("'" + map.get(s) + "',");
                        else
                            sb2.append(map.get(s) + ",");
                    }
                }
//               allDatas.add("INSERT INTO "+table+" ("+sb1.toString().substring(0,sb1.toString().length()-1)+") values("+sb2.toString().substring(0,sb2.toString().length()-1)+");\n");
            }
        }
        File myFilePath = new File(filePath);
        try {
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileOutputStream writerStream = new FileOutputStream(myFilePath);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
            for (String s:allDatas ) {
                writer.write(s);
            }
            writer.close();
            writerStream.flush();
            writerStream.close();
        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();
        }

    }
}
