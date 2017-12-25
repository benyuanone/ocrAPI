package com.ourway.sys.service;

import com.ourway.base.dataobject.PageInfo;
import com.ourway.base.model.FilterModel;
import com.ourway.base.utils.HqlStatement;
import com.ourway.sys.model.OurwaySysDepatemp;
import com.ourway.sys.model.OurwaySysDfilter;
import com.ourway.sys.model.OurwaySysEmploys;
import com.ourway.sys.model.OurwaySysPrivsuser;

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
public interface EmploysService {
    /**
     * <p>接口 EmploysService.java : 判断关键字唯一性<p>
     * <p>说明：</p>
     * <pre>
     * @author cc
     * @date 2017/4/18 9:35
     * </pre>
     */
    Boolean doCheckUniqueLabel(OurwaySysEmploys employ);

    /**
     * <p>接口 listEmploysByPage.java : <p>
     * <p>说明：用户列表</p>
     * <pre>
     * @author cc
     * @date 2017/4/18 9:53
     * </pre>
     */
    PageInfo<OurwaySysEmploys> listEmploysByPage(HqlStatement hqlStatement, int pageNo, int pageSize);

    /**
     * <p>接口 EmploysService.java : <p>
     * <p>说明：删除对象</p>
     * <pre>
     * @author cc
     * @date 2017/4/18 9:59
     * </pre>
     */
    void removeByIds(HqlStatement hql);

    /**
     * <p>方法:queryOneByParams 根据参数查询唯一 </p>
     * <ul>
     * <li> @param params 参数</li>
     * <li>@return com.ourway.sys.model.OugwaySysEmploys  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/25 0:46  </li>
     * </ul>
     */
    OurwaySysEmploys queryOneByParams(Map<String, Object> params);

    /**
     * <p>接口 EmploysService.java : <p>
     * <p>说明：用户信息和用户列表</p>
     * <pre>
     * @author cc
     * @date 2017/4/21 12:45
     * </pre>
     */
    List<OurwaySysEmploys> listDicByStatementDep(HqlStatement hql);


    PageInfo<OurwaySysEmploys> listHqlForPage(List<FilterModel> filters, Integer pageNo, Integer pageSize, String s);

    /**
     * 删除
     *
     * @param owids
     * @return
     */
    List<Map<String, Object>> removeItems(List<String> owids);

    /**
    *<p>方法:updateState 修改员工状态 </p>
    *<ul>
     *<li> @param objs TODO</li>
     *<li> @param state TODO</li>
    *<li>@return java.util.List<java.lang.Object>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/12/23 15:15  </li>
    *</ul>
    */
    List<Object> updateState(List<Object> objs, Integer state);

    List<Object> updateResetPsw(List<Object> objs);

    /**
     * <p>接口 EmploysService.java : <p>
     * <p>说明：用户详细</p>
     * <pre>
     * @author cc
     * @date 2017/5/31 14:23
     * </pre>
     */
    OurwaySysEmploys detailEmploy(String owid);

    /**
     * <p>接口 EmploysService.java : <p>
     * <p>说明：保存</p>
     * <pre>
     * @author cc
     * @date 2017/5/31 18:37
     * </pre>
     */
    OurwaySysEmploys saveAllEmploye(OurwaySysEmploys ourwaySysEmploys, List<OurwaySysPrivsuser> privsuserList, List<OurwaySysDfilter> userFilters, List<OurwaySysDepatemp> userEmplist);


    /**
     * <p>方法:listPositionDepart TODO </p>
     * <ul>
     * <li> @param employs TODO</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/7/21 0:18  </li>
     * </ul>
     */
    String listPositionDepart(OurwaySysEmploys employs);

    /**
     * <p>方法:saveOtherEmploy  第三方途径保存用户信息 </p>
     * <ul>
     * <li> @param employ 用户信息</li>
     * <li>@return int  0：保存成功  -1：员工不存在 -2：未传入客户类型 -3 登录号重复</li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/12/21 17:44  </li>
     * </ul>
     */
    int saveOtherEmploy(OurwaySysEmploys employ);
}
