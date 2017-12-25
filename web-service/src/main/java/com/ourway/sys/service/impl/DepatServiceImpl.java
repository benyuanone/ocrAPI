package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.BaseTree;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.SysDepatDao;
import com.ourway.sys.dao.SysDepatempDao;
import com.ourway.sys.model.OurwaySysDepat;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysMenus;
import com.ourway.sys.service.DepatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 DepatService.java : <p>
 * <p>说明：部门信息</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
@Service("depatService")
public class DepatServiceImpl implements DepatService {
    @Autowired
    SysDepatDao sysDepatDao;
    @Autowired
    SysDepatempDao sysDepatempDao;

    @Override
    public boolean doCheckUniqueLabel(OurwaySysDepat depat) {
        return sysDepatDao.doCheckUniqueLabelKey(depat);
//        return false;
    }

    @Override
    public void saveOrUpdateLanguage(OurwaySysDepat depat) {
        sysDepatDao.saveOrUpdate(depat);
    }

    @Override
    public PageInfo<OurwaySysDepat> listHqlForPage(List<FilterModel> models, int pageNo, int pageSize) {
        return sysDepatDao.listHqlForPage(models, pageNo, pageSize);
    }

    @Override
    public void removeententDicByIds(HqlStatement hql) {
        List<OurwaySysDepat> depatList = sysDepatDao.listAllByHql(hql.getHql(), hql.getParams());
        for (OurwaySysDepat depat : depatList) {
            sysDepatDao.removeEntity(depat);
        }
    }

    @Override
    public List<OurwaySysDepat> listDicByStatement(HqlStatement hql) {
        List<OurwaySysDepat> depList = sysDepatDao.listAllByHql(hql.getHql(), hql.getParams());
//        Map<String, Object> _params = new HashMap<String, Object>();
//        for (OurwaySysDepat dep : depList) {
//            _params.put("deptRefOwid", dep.getOwid());
//            dep.setDepatempValueList(sysDepatempDao.listAllByHql("from OurwaySysDepatemp", _params));
//        }
        return depList;
    }

    @Override
    public void saveOrUpdate(OurwaySysDepat depat) {
        sysDepatDao.saveOrUpdate(depat);
    }

    @Override
    public void removeDepart(Map<String, Object> map) {
        sysDepatDao.removeDepart(map);
    }


    /*depat*/
    @Override
    public OurwaySysDepat getDept(OurwaySysEmploys ourwaySysEmploys) {

        /* 先根据 用户owid 获得用户部门 owid*/
        OurwaySysDepatemp depatemp = sysDepatempDao.getOneDepatemp(ourwaySysEmploys.getOwid());

        return sysDepatDao.getDept(depatemp.getDeptRefOwid());
    }

    /*depat*/
    @Override
    public List<OurwaySysDepat> getDeptList(Integer depCode) {

        return sysDepatDao.getDeptList(depCode);
    }

    /*depat*/
    @Override
    public OurwaySysDepat getDeptById(Integer depCode) {
        return sysDepatDao.getDept(depCode);
    }

    /*depat*/
    @Override
    public List<OurwaySysDepat> listAllDepat(Integer depCode, String vcName, String nextDepName) {
        return sysDepatDao.listAllDepat(depCode, vcName, nextDepName);
    }

    /**/
    @Override
    public List<OurwaySysDepat> listDepat() {

        String hql = "from OurwaySysDepat where 1=1";

        return sysDepatDao.listAllByHql(hql, null);
    }

    /**/
    @Override
    public List<Map<String, Object>> listAllNextDepat() {

//        OurwaySysEmploys employs = CacheUtil.getVal("",OurwaySysEmploys.class);
        Integer depCode = 2; //employs.getdId();
//        String depName = "县级医院1"; //employs.getdName();
//        String depPath = "-1/2"; //employs.getdPath();

        List<OurwaySysDepat> list = sysDepatDao.listAllNextDepat(depCode);

        List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();

        if (null != list && list.size() > 0) {
            for (OurwaySysDepat enity : list) {
                Map<String, Object> param = new HashMap<String, Object>(1);

                param.put("label", enity.getDepName());
                param.put("value", enity.getOwid());

                mList.add(param);

            }
        }

        return mList;
    }

    @Override
    public List<BaseTree> listTree(List<FilterModel> models) {
        String name = "";
        List<OurwaySysDepat> deptList = sysDepatDao.listDeptByFilters(models);
        List<BaseTree> baseTreeList = new ArrayList<BaseTree>();
        if (null != deptList && deptList.size() > 0) {
            for (OurwaySysDepat depat : deptList) {
                name = "";
                BaseTree bt = new BaseTree();
                bt.setOwid(depat.getOwid());
                bt.setFid(depat.getFid());
                if (!TextUtils.isEmpty(depat.getDepNo()))
                    name += depat.getDepNo() + "-";
                if (!TextUtils.isEmpty(depat.getDepName()))
                    name += depat.getDepName();
                bt.setName(name);
                bt.setPath(depat.getPath());
                bt.setCc(depat.getCc());
                bt.setPx(depat.getPx());
                baseTreeList.add(bt);
            }
        }
        return baseTreeList;
    }

    @Override
    public void update(List<OurwaySysDepat> depats) {
        List<OurwaySysDepat> subDepats = null;
        List<OurwaySysDepat> updateDepats = null;
        for (OurwaySysDepat depat : depats) {
            //1.get the all subMenus
            OurwaySysDepat _depat = sysDepatDao.getOneById(depat.getOwid());
            //如果路径不一致，说明该国路径了，需要更新其下面的子节点
            if (!_depat.getPath().equalsIgnoreCase(depat.getPath())) {
                subDepats = sysDepatDao.listSubDepats(depat);
                if (null != subDepats && subDepats.size() > 0) {
                    updateDepats = new ArrayList<OurwaySysDepat>();
                    for (OurwaySysDepat subMenu : subDepats) {
                        subMenu.setPath(depat.getPath() + "/" + subMenu.getPath().split("/" + depat.getOwid() + "/")[1]);
                        updateDepats.add(subMenu);
                    }
                    sysDepatDao.saveOrUpdate(updateDepats);
                }
            }
            _depat.setFid(depat.getFid());
            _depat.setPx(depat.getPx());
            _depat.setPath(depat.getPath());
            _depat.setCc(depat.getCc());
            sysDepatDao.update(_depat);
        }
    }
}
