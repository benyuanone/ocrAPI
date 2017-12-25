package com.ourway.base.report;

/**<p>接口 ReportTemplateSer.java : <p>
*<p>说明：报表模板管理，包括模板另存为，模板生成为jasper等操作</p>
*<pre>
*@author JackZhou
*@date 2017/3/29 1:02
</pre>
*/
public interface ReportTemplateSer {

    /**
     *<p>方法:doCreateJasper 根据报表编号生成对应的jasper文件 </p>
     *<ul>
     *<li> @param pageCa 报表ca</li>
     *<li> @param reportCode 报表编号</li>
     *<li>@return java.lang.String  返回jasper文件路径</li>
     *<li>@author JackZhou </li>
     *<li>@date 2017/3/29 0:59  </li>
     *</ul>
     */
    public abstract String doCreateJasper(String pageCa,String reportCode);

    /**
    *<p>方法:doCreateJrxml 生成jrxml文件 </p>
    *<ul>
     *<li> @param pageCa 报表ca</li>
     *<li> @param reportCode 报表编号</li>
    *<li>@return java.lang.String  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/3/29 1:04  </li>
    *</ul>
    */
    public abstract String doCreateJrxml(String pageCa,String reportCode);


//    public abstract String doCopy(String pageCa,String sourceCode,String desCode);
}
