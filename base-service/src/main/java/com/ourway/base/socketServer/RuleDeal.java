package com.ourway.base.socketServer;

import java.io.ByteArrayOutputStream;

/**
 * Created by D.chen.g on 2017/3/14.
 */
public class RuleDeal {
    /**
     * 16进制数字字符集
     */
    private static String hexString="0123456789ABCDEF";

    /**
     * <p>
     * 字符串转成16进制
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: RuleDeal.java,v 0.1 2015-12-17 上午11:01:46 Jack Exp $
     */
    public static String convertStringToHex(String str) {
        char[] chars = str.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }



    /**
     * <p>
     * 把16进制数字转成字母
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: RuleDeal.java,v 0.1 2016-1-7 下午8:37:58 Jack Exp $
     */
    public static String convertHexToChar(String str) {
        int len = str.length() / 2;
        if (len % 2 != 0)
            return "";
        String back = "";
        for (int i = 0; i < len; i++) {
            back += (char) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
        }
        return back;
    }

    /**
     * <p>
     * 16进制转成时间 YYYY-MM-DD HH：MM：SS
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: RuleDeal.java,v 0.1 2016-2-26 上午12:02:24 Jack Exp $
     */
    public static String convertHex2Date(String str) {
        String sj = "";
        // long l = Long.parseLong(str, 16);
        // return transferLongToDate("yyyy-MM-dd HH:mm:ss", l);
        int i = 0;
        sj = 2000 + Integer.parseInt(str.substring(0, 2), 16) + "-";
        i=Integer.parseInt(str.substring(2, 4), 16);
        if(i<10)
            sj +="0"+i+"-";
        else
            sj +=""+i+"-";
        i=Integer.parseInt(str.substring(4, 6), 16);
        if(i<10)
            sj +="0"+i;
        else
            sj +=""+i;

        i=Integer.parseInt(str.substring(6, 8), 16);
        if(i<10)
            sj +=" 0"+i+":";
        else
            sj +=" "+i+":";
        i=Integer.parseInt(str.substring(8, 10), 16);
        if(i<10)
            sj +="0"+i+":";
        else
            sj +=""+i+":";
        i=Integer.parseInt(str.substring(10, 12), 16);
        if(i<10)
            sj +="0"+i+"";
        else
            sj +=""+i+"";
        return sj;

    }
    /**
    *<p>方法:toStringHex TODO 转化十六进制编码为字符串</p>
    *<ul>
     *<li> @param s TODO</li>
    *<li>@return java.lang.String  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/3/15 14:44  </li>
    *</ul>
    */
    public static String toStringHex(String s){
        byte[] baKeyword = new byte[s.length()/2];
        for(int i = 0; i < baKeyword.length; i++){
            try{
                baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        try{
            s = new String(baKeyword, "utf-8");//UTF-16le:Not
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 将16进制数字解码成字符串,适用于所有字符（包括中文）
     */
    public static String decode(String bytes)
    {
        ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
        //将每2位16进制整数组装成一个字节
        for(int i=0;i<bytes.length();i+=2)
            baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
        return new String(baos.toByteArray());
    }


    public static void main(String[] args) {
//        System.out.println(toStringHex("5a54"));
//        System.out.println(convertStringToHex("DB00008201302021111011.0200,2.0224"));
      //  System.out.println(new Double("2.0224").doubleValue());
        Double d = new Double("4.014");

     /* returns a hexadecimal string representation of the
     double argument */
//        String str = d.toHexString(1.0);
//        System.out.println("Hex String = " + str);
//
//        str = d.toHexString(3.0);
//        System.out.println("Hex String = " + str);
//
//        str = d.toHexString(0.25);
//        System.out.println("Hex String = " + str);
//
//        str = d.toHexString(Double.MAX_VALUE);
//        System.out.println("Hex String = " + str);
        double f = 120.25;
        System.out.println(Double.toHexString(f));
//        System.out.println(Float.intBitsToFloat(Integer.parseInt("42f08000", 16)));
//        Double.parseDouble(Double.);
    }
}
