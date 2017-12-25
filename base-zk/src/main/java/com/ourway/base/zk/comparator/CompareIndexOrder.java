package com.ourway.base.zk.comparator;

import com.ourway.base.zk.utils.GridUtils;
import com.ourway.base.zk.utils.TextUtils;

import java.util.Comparator;
import java.util.Map;

/**<p>方法 CompareIndexOrder : <p>
*<p>说明:用于对子表的内容按照index字段来排序</p>
*<pre>
*@author JackZhou
*@date 2017/5/26 15:09
</pre>
*/
public class CompareIndexOrder implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        try {
            String rowIndex1 = "0";
            String rowIndex2 = "0";
            Map<String, Object> map1 = (Map<String, Object>) o1;
            Map<String, Object> map2 = (Map<String, Object>) o2;
            if(!TextUtils.isEmpty(map1.get(GridUtils.ROWINDEXNO)))
                rowIndex1 = map1.get(GridUtils.ROWINDEXNO).toString();
            if(TextUtils.isEmpty(map2.get(GridUtils.ROWINDEXNO)))
                rowIndex2 = map2.get(GridUtils.ROWINDEXNO).toString();
            int order1 = Integer.parseInt(rowIndex1);
            int order2 = Integer.parseInt(rowIndex2);
            return order1 - order2;
        }catch(Exception e){
            return 0;
        }
    }
}
