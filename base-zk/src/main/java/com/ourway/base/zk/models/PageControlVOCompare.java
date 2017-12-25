package com.ourway.base.zk.models;

import java.util.Comparator;
import java.util.Map;

/**<p>方法 PageControlVOCompare : <p>
*<p>说明:对同一行的控件进行排序</p>
*<pre>
*@author JackZhou
*@date 2017/5/15 12:48
</pre>
*/
public class PageControlVOCompare implements Comparator<PageControlVO> {
    @Override
    public int compare(PageControlVO o1, PageControlVO o2) {
        return o1.getLayoutComponent().getCompOrder() - o2.getLayoutComponent().getCompOrder();
    }
}
