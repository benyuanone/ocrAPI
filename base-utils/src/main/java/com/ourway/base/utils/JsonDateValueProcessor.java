package com.ourway.base.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**<p>方法 JsonDateValueProcessor : <p>
*<p>说明:JSON日期格式化处理</p>
*<pre>
*@author CuiLiang
*@date 2017-07-14 11:12
</pre>
*/
public class JsonDateValueProcessor  implements JsonValueProcessor {
    private String format ="yyyy-MM-dd HH:mm:ss";

    public JsonDateValueProcessor() {
        super();
    }

    public JsonDateValueProcessor(String format) {
        super();
        if (!TextUtils.isEmpty(format))
            this.format = format;

    }
    @Override
    public Object processArrayValue(Object paramObject,
                                    JsonConfig paramJsonConfig) {
        return process(paramObject);
    }

    @Override
    public Object processObjectValue(String paramString, Object paramObject,
                                     JsonConfig paramJsonConfig) {
        return process(paramObject);
    }
    private Object process(Object value){
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }
}
