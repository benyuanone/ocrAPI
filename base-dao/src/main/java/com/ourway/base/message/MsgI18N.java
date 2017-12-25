package com.ourway.base.message;

/**<p>接口 MsgI18N.java : <p>
*<p>说明：国际化支持接口</p>
*<pre>
*@author JackZhou
*@date 2017/3/11 16:06
</pre>
*/
public interface MsgI18N {
    /**
    *<p>方法:getMsg 传入信息类别和语言，翻译成适合该语言的内容 </p>
    *<ul>
     *<li> @param msg 原始的msg内容或key,多个标签之间用+进行分割</li>
     *<li> @param language 语言类型,如果为null，表示不转换</li>
    *<li>@return java.lang.String  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/3/11 16:07  </li>
    *</ul>
    */
    String getMsg(String msg, String language);

    /**
    *<p>方法:getMsg 直接输入message，不经过国际化处理 </p>
    *<ul>
     *<li> @param msg 消息内容</li>
    *<li>@return java.lang.String  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/3/12 0:15  </li>
    *</ul>
    */
    String getMsg(String msg);
}
