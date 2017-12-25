package com.ourway.base.report;

import java.util.List;
import java.util.Map;

/**<p>接口 BaseReport.java : <p>
*<p>说明：Jasper Report 公共接口，在该接口下可以通过不同的实现类来实现不同的数据采集</p>
*<pre>
*@author JackZhou
*@date 2017/3/29 0:13
</pre>
*/
public interface BaseDataReportSer {

     /**
     *<p>方法:listReportParamter 根据页面的ca和报表的编号来获取报表相关的参数 </p>
     *<ul>
      *<li> @param pageCA 页面CA，必须要传递</li>
      *<li> @param reportCode 报表code，可以为空，为空表示默认报表</li>
     *<li>@return java.util.Map<java.lang.String,java.lang.Object> 返回基于Map的参数 </li>
     *<li>@author JackZhou </li>
     *<li>@date 2017/3/29 0:27  </li>
     *</ul>
     */
    public abstract Map<String,Object> listReportParamter(String pageCA,String reportCode);


    /**
    *<p>方法:listReportDatas 获取报表数据列表 </p>
    *<ul>
     *<li> @param pageCa TODO</li>
     *<li> @param reportCode TODO</li>
    *<li>@return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/3/29 0:30  </li>
    *</ul>
    */
    public abstract List<Map<String,Object>> listReportDatas(String pageCa,String reportCode);




}
