package com.ourway.sys.service.impl;


import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.base.utils.JsonUtil;
import com.ourway.base.utils.TextUtils;
import com.ourway.sys.dao.*;
import com.ourway.sys.model.*;
import com.ourway.sys.service.EmploysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>接口 EmploysService.java : <p>
 * <p>说明：</p>
 * <pre>
 * @author cc
 * @date 14:36 2017/4/12
 * </pre>
 */
@Service("employsSer")
public class EmploysServiceImpl implements EmploysService {

    @Autowired
    SysEmploysDao sysEmploysDao;

    @Autowired
    SysDepatempDao sysDepatempDao;

    @Autowired
    SysPrivsuserDao sysPrivsuserDao;

    @Autowired
    SysQuickMenuDao sysQuickMenuDao;

    @Autowired
    SysDfilterDao sysDfilterDao;

    @Autowired
    SysDicValueDao sysDicValueDao;
    @Autowired
    SysRolesDao sysRolesDao;

    @Override
    public Boolean doCheckUniqueLabel(OurwaySysEmploys employ) {
        return sysEmploysDao.doCheckUniqueLabelKey(employ);
    }

    @Override
    public PageInfo<OurwaySysEmploys> listEmploysByPage(HqlStatement hqlStatement, int pageNo, int pageSize) {
        return sysEmploysDao.listHqlForPage(hqlStatement.getHql(), hqlStatement.getCountHql(), hqlStatement.getParams(), pageNo, pageSize);
    }

    @Override
    public void removeByIds(HqlStatement hql) {
        List<OurwaySysEmploys> employList = sysEmploysDao.listAllByHql(hql.getHql(), hql.getParams());
        for (OurwaySysEmploys employs : employList) {
            sysEmploysDao.removeEntity(employs);
        }
    }


    @Override
    public OurwaySysEmploys queryOneByParams(Map<String, Object> params) {
        return sysEmploysDao.getOneByParams(params, "");
    }

    @Override
    public List<OurwaySysEmploys> listDicByStatementDep(HqlStatement hql) {
        List<OurwaySysEmploys> empList = sysEmploysDao.listAllByHql(hql.getHql(), hql.getParams());
        Map<String, Object> _params = new HashMap<String, Object>();
        for (OurwaySysEmploys emp : empList) {
            _params.put("userRefOwid", emp.getOwid());
//            emp.setDataList(sysDepatempDao.listAllByHql("from OurwaySysDepatemp", _params));//用户部门
            emp.setPriUserList(sysPrivsuserDao.listAllByHql("from OurwaySysPrivsuser", _params));//角色用户权限
            emp.setFilterList(sysDfilterDao.listAllByHql("from OurwaySysDfilter", _params));//数据过滤
            emp.setQuickMenuList(sysQuickMenuDao.listAllByHql("from OurwaySysQuickmenu", _params));//快捷菜单
        }
        return empList;

    }

    @Override
    public PageInfo<OurwaySysEmploys> listHqlForPage(List<FilterModel> filters, Integer pageNo, Integer pageSize, String sortStr) {
        return sysEmploysDao.listHqlForPage(filters, pageNo, pageSize, sortStr);
    }

    @Override
    public List<Map<String, Object>> removeItems(List<String> owids) {
        List<Map<String, Object>> objs = new ArrayList<Map<String, Object>>();
        for (String owid : owids) {
            //删除过滤数据
            sysDfilterDao.removeFilterByUserOwid(owid);
            //删除快捷菜单
            sysQuickMenuDao.removeByUserOwid(owid);
            //删除角色
            sysPrivsuserDao.removeByUserOwid(owid);
            //删除部门
            sysDepatempDao.removeByUserOwid(owid);
            Map<String, Object> params = new HashMap<String, Object>(1);
            params.put("owid", owid);
            sysEmploysDao.removeByParams(params);
            objs.add(params);
        }
        return objs;
    }

    @Override
    public List<Object> updateState(List<Object> objs, Integer state) {
        for (Object obj : objs) {
            Map<String, Object> _obj = (Map<String, Object>)JsonUtil.removeNullAttribute(obj);
            if (null == _obj || TextUtils.isEmpty(_obj.get("owid")))
                continue;
            OurwaySysEmploys employs = sysEmploysDao.getOneById(_obj.get("owid").toString());
            employs.setState(state);
            employs.setEmpStatue(state);
            sysEmploysDao.update(employs);
            _obj.put("state", employs.getState());
            _obj.put("empStatue", employs.getEmpStatue());
        }
        return objs;
    }

    @Override
    public List<Object> updateResetPsw(List<Object> objs) {
        List<Object> returnDatas = new ArrayList<Object>();
        for (Object obj : objs) {
            Map<String, Object> _obj = (Map<String, Object>)JsonUtil.removeNullAttribute(obj);
            if (null == _obj || TextUtils.isEmpty(_obj.get("owid")))
                continue;
            OurwaySysEmploys employs = sysEmploysDao.getOneById(_obj.get("owid").toString());
            employs.setEmpPsw(TextUtils.MD5("123456").toUpperCase());
            sysEmploysDao.update(employs);
            returnDatas.add(_obj);
        }
        return returnDatas;
    }

    @Override
    public OurwaySysEmploys detailEmploy(String owid) {
        OurwaySysEmploys employs = sysEmploysDao.getOneById(owid);
        return employs;
    }

    @Override
    public OurwaySysEmploys saveAllEmploye(OurwaySysEmploys ourwaySysEmploys, List<OurwaySysPrivsuser> privsuserList, List<OurwaySysDfilter> userFilters, List<OurwaySysDepatemp> userEmplist) {
        //保存主表信息
        String logGroup = TextUtils.getUUID();
        ourwaySysEmploys.setLogGroupName(logGroup);
        sysEmploysDao.saveOrUpdate(ourwaySysEmploys);
        if (null != privsuserList && privsuserList.size() > 0) {
            for (OurwaySysPrivsuser privsuser : privsuserList) {
                privsuser.setUserRefOwid(ourwaySysEmploys.getOwid());
                privsuser.setLogGroupName(logGroup);//日志可以分组
                switch (privsuser.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        break;  //什么也不做
                    //修改
                    case 1:
                        // 判断 owid
                        if (TextUtils.isEmpty(privsuser.getOwid())) {
                            //为空 新增
                            sysPrivsuserDao.save(privsuser);
                        } else {
                            sysPrivsuserDao.update(privsuser);
                        }
//                        ourwaySysEmploys.getDataList().add(depatemp);
                        break;
                    //删除
                    case 2:
                        sysPrivsuserDao.removeByIds(privsuser.getOwid());
                        break;
                }
            }
        }
        if (null != userFilters && userFilters.size() > 0) {
            for (OurwaySysDfilter userfilter : userFilters) {
                userfilter.setUserRefOwid(ourwaySysEmploys.getOwid());
                userfilter.setLogGroupName(logGroup);
                switch (userfilter.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        break;  //什么也不做
                    //修改
                    case 1:
                        // 判断 owid
                        if (TextUtils.isEmpty(userfilter.getOwid())) {
                            //为空 新增
                            sysDfilterDao.save(userfilter);
                        } else {
                            sysDfilterDao.update(userfilter);
                        }
//                        ourwaySysEmploys.getDataList().add(depatemp);
                        break;
                    //删除
                    case 2:
                        sysDfilterDao.removeByIds(userfilter.getOwid());
                        break;
                }
            }
        }
        if (userEmplist != null && userEmplist.size() > 0) {
            for (OurwaySysDepatemp depatemp : userEmplist) {
                if (null == depatemp.getUpdateFlag()) {
                    depatemp.setUpdateFlag(0);
                }
                depatemp.setLogGroupName(logGroup);
                switch (depatemp.getUpdateFlag()) {
                    //0 新增
                    case 0:
                        break;  //什么也不做
                    //修改
                    case 1:
                        depatemp.setUserRefOwId(ourwaySysEmploys.getOwid());
                        // 判断 owid
                        if (TextUtils.isEmpty(depatemp.getOwid())) {
                            //为空 新增
                            sysDepatempDao.save(depatemp);
                        } else {
                            sysDepatempDao.update(depatemp);
                        }
//                        ourwaySysEmploys.getDataList().add(depatemp);
                        break;
                    //删除
                    case 2:
                        sysDepatempDao.removeByIds(depatemp.getOwid());
                        break;
                }
            }
        }
        //获取最近的默认的部门
        OurwaySysDepatemp defaultDepart = sysDepatempDao.listDefaultDepart(ourwaySysEmploys);
        if (null != defaultDepart) {
            ourwaySysEmploys.setEmpDefaultDept(defaultDepart.getDeptRefOwid());
            ourwaySysEmploys.setEmpDefaultPosition(defaultDepart.getEmpPositionId());
            sysEmploysDao.update(ourwaySysEmploys);
        }

        return ourwaySysEmploys;
    }


    @Override
    public String listPositionDepart(OurwaySysEmploys employs) {

        return sysEmploysDao.listPositionDepart(employs);
    }

    @Override
    public int saveOtherEmploy(OurwaySysEmploys employ) {
        if (null == employ)
            return -1;
        if (TextUtils.isEmpty(employ.getEmpId()))
            return -2;//员工号为空
        if (TextUtils.isEmpty(employ.getEmpType()))
            return -3;//员工号类型为空
        //判断登录名是否重复
        Map<String, Object> params = new HashMap<String, Object>(1);
        params.put("empId", employ.getEmpId());
        List<OurwaySysEmploys> employsList = sysEmploysDao.listAllByParam(params, "");
        if (null != employsList && employsList.size() > 0)
            return -4; //重复用户登录号
        OurwaySysDicValue dicValue = sysDicValueDao.listOneDic(4, employ.getEmpType());
        if (null != dicValue && !TextUtils.isEmpty(dicValue.getDicVal5())) {
            employ.setEmpIndex(dicValue.getDicVal5());
        }
        saveNoAdminEmploy(employ, dicValue);
//        OurwaySysDic
        //获取指定的类型
        return 0;
    }

    private void saveNoAdminEmploy(OurwaySysEmploys employs, OurwaySysDicValue dicValue) {
        sysEmploysDao.save(employs);
        if (!TextUtils.isEmpty(dicValue.getDicVal4())) {
            String[] _roles = dicValue.getDicVal4().split("\\,");
            Map<String, Object> map = new HashMap<String, Object>(1);
            for (String role : _roles) {
                if (!TextUtils.isEmpty(role)) {
                    map.clear();
                    map.put("language", role);
                    OurwaySysRoles _role = sysRolesDao.getOneByParams(map, "");
                    if (null != _role) {
                        OurwaySysPrivsuser puser = new OurwaySysPrivsuser();
                        puser.setRoleRefOwid(_role.getOwid());
                        puser.setUserRefOwid(employs.getOwid());
                        sysPrivsuserDao.save(puser);
                    }
                }
            }

        }
    }
}
