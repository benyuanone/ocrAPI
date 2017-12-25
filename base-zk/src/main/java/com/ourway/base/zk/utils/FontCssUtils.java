package com.ourway.base.zk.utils;


import com.ourway.base.utils.*;
import com.ourway.base.zk.models.FontCssVO;
import com.ourway.base.zk.models.PageLayoutControlVO;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FontCssUtils {

    /**
     * <p>方法:getFontCss 根据grid列表中字体样式的定义来返回符合条件的样式 </p>
     * <ul>
     * <li> @param vo 样式定义对象</li>
     * <li> @param v 具体对象的值</li>
     * <li>@return java.lang.String  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/16 1:00  </li>
     * </ul>
     */
    public static String getFontCss(PageLayoutControlVO vo, Object v) {
        Object obj = vo.getCompFontCss();
        if (TextUtils.isEmpty(obj) || null == obj)
            return "";
        List<Map<String,Object>> datas = (List<Map<String,Object>>)obj;
        for(Map<String,Object> data:datas){
            FontCssVO cssVO = BeanUtil.map2Obj(data,FontCssVO.class);
            if(cssVO.getMax()==cssVO.getMin())
                return cssVO.getCss();
            else{
                if(TextUtils.isEmpty(v))
                    return "";
                if(isNumeric(v.toString())){
                    double _d = new Double(v.toString());
                    if(_d>=cssVO.getMin()&&_d<=cssVO.getMax())
                        return cssVO.getCss();
                }else
                    return "";
            }
        }
        return "";
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        System.out.println(FontCssUtils.isNumeric("-1.2323"));
    }

}
