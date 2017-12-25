package com.ourway.base.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringUtil {



    /**
     * The separator for split and join.
     */
    public static final char SEPARATOR = '|';

    public static final String UTF_8="UTF-8";
    /**
     * Returns a blank string while given string is null.
     *
     * @param value
     *            string value to be avoid.
     * @return blank string while null, other original string value.
     */
    public static String avoidNull(String value) {
        return (value == null) ? "" : value;
    }

    /**
     * Parse exception's stack trace into a String.
     *
     * @param e
     *            the exception
     * @return Stack trace in a String.
     */
    public static String exceptionStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String returnStr = "";
        if (null != e) {
            e.printStackTrace(pw);
            pw.flush();
            pw.close();
            returnStr = sw.toString();
        }
        return returnStr;
    }

    /**
     * Returns the byte length in given charset of the String.
     *
     * @param value
     * @param charset
     *            Charset under which to measure.
     * @return Returns 0 if String is null, or the charset is not supported.
     * @see
     */
    public static int getByteLength(String value, String charset) {
        if (value == null) {
            return 0;
        } else {
            try {
                return value.getBytes(charset).length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return 0;
            }
        }

    }

    /**
     * Returns object's toString() result if it's not null. Return an empty
     * String if it's null.
     *
     * @param obj
     *            object.
     * @return string
     */
    public static String getObjString(Object obj) {
        if (null == obj)
            return "";
        else
            return obj.toString();
    }

    /**
     * Returns the byte length in UTF8 of the String.<br/>
     * Generally, Chinese characters are three bytes each, ASCII characters are
     * one byte each.
     *
     * @param value
     * @return Returns 0 if String is null.
     */
    public static int getUTF8Length(String value) {

        return getByteLength(value, UTF_8);

    }

    /**
     * Check whether given string value is null or a blank string (trimmed).
     *
     * @param value
     *            string value to be checked.
     * @return true if string value is null or blank, otherwise false.
     */
    public static boolean isNullOrBlank(String value) {
        return value == null || "".equals(value.trim());
    }

    /**
     * Join collection's toString() into one String split with "\|". <br/>
     * A null element of the collection will leave a empty string in the result. <br/>
     * Process has been taken to be compatible to separator included String: If
     * there is \ in the String, it'll be replaced with double \.
     *
     * @param objects
     * @return If collection has no elements return null. The separator should
     *         appear the times same to collection's count.
     */
    public static String join(Object... objects) {

        if (objects == null || objects.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer();
        for (Object object : objects) {
            if (object != null) {

                // 棣栧厛瀵规墍鏈塡杩涜瑙勯伩澶勭悊锛屽嵆淇敼涓轰袱涓猏
                String objString = object.toString();
                if (objString != null) {
                    objString = objString.replaceAll("\\\\", // 鍗砛鍙�
                            Matcher.quoteReplacement("\\\\"));
                    objString = objString.replaceAll("\\|", Matcher.quoteReplacement("\\|"));
                    sb.append(objString);
                }
            }

            // 涔嬪悗鐢╘鍜屽垎闅旂浣滀负鍒嗗壊
            sb.append(SEPARATOR);
        }

        // 鍘婚櫎鏈�悗涓�釜澶氫綑鐨勫垎闅旂锛屼笉鐢ㄨ�铏戠┖鐨勬儏鍐�
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * Left the string by given byte-length in specified charset, the
     * byte-length of result string should not exceed parameter
     * <code>bytelen</code> and meanwhile should be closest to it.<br/>
     * For example, generally Chinese character in UTF-8 occupies 3 bytes, so <br/>
     * <br/>
     * <code>
     * leftByCharsetByte("涓枃","UTF-8",3) = "涓� <br/>
     * leftByCharsetByte("涓枃","UTF-8",4) = "涓� <br/>
     * leftByCharsetByte("涓枃","UTF-8",6) = "涓枃"<br/>
     * </code><br/>
     * If not even a character can be returned, the result will be "" as <br/>
     * <br/>
     * <code>
     * leftByCharsetByte("涓枃","UTF-8",1) = ""
     * </code>
     *
     * @param value
     *            original String to left
     * @param charset
     *            Charset Name
     * @param bytelen
     *            the max length of byte to return in specified charset
     * @return If value is null, return null, otherwise return not null String.
     */
    public static String leftByCharsetByte(String value, String charset,
                                           int bytelen) {

        // 濡傛灉涓篘ULL锛屽垯鐩存帴杩斿洖
        if (null == value) {
            return null;
        }

        try {

            Charset cs = Charset.forName(charset);
            if (cs.encode(value).limit() <= bytelen) { // 鐩存帴杩斿洖
                return value;
            }

            int byteCount = 0, stringIndex = 0;
            while (true) { // 涓嶇敤鑰冭檻value鎬婚暱搴�
                int currentByteLen = cs.encode(
                        value.substring(stringIndex, stringIndex + 1)).limit();
                if ((byteCount + currentByteLen) > bytelen) {
                    break; // 杈惧埌鏈�ぇ瀛楄妭鏁帮紝涓柇

                } else {
                    byteCount += currentByteLen; // 缁х画
                    stringIndex++;

                }
            }

            return value.substring(0, stringIndex);

        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * A convenience method for UTF8 charset. See reference in leftByCharsetByte
     * method.
     *
     * @param value
     *            original String to left
     * @param bytelen
     *            the max length of byte to return in UTF8
     * @return If value is null, return null, otherwise return not null String.
     */
    public static String leftByUTF8Byte(String value, int bytelen) {
        return leftByCharsetByte(value, UTF_8, bytelen);
    }

    private static final Pattern pattern = Pattern.compile("(?<!\\\\)(\\\\\\\\)*\\Q"
            + SEPARATOR + "\\E"); // 鍖归厤鍓嶆柟鏈夊伓鏁颁釜鎴�涓猏鐨勫垎闅旂

    /**
     * Split a joined String which separated by specified separator back to a
     * String array. Should be used with StringUitl.join because there is some
     * mechanism to avoid special character.
     *
     * @param ori
     * @return
     */
    public static String[] split(String ori) {
        if (ori == null) {
            return null;
        } else {

            List<String> strings = new ArrayList<String>();
            // 鑷繁鍐欒В鏋愬櫒

            Matcher matcher = pattern.matcher(ori);
            int currentStart = 0;
            while (matcher.find()) {
                // 鍖归厤杩斿洖鐨別nd鍗冲垎鍓茬鍚庝綅缃�
                int pos = matcher.end();
                strings.add(ori.substring(currentStart, pos - 1)); // 鎴彇鏈寮�鍒板垎闅旂鍓嶄竴涓綅缃�鍘绘帀\鍙�
                currentStart = pos;
            }
            // 鏈�悗蹇呯劧杩樻湁鍓╀綑锛屽氨绠楁渶鍚庝竴涓瓧绗︽槸鍒嗛殧绗�
            strings.add(ori.substring(currentStart, ori.length()));

            String[] strs = strings.toArray(new String[0]);
            // 渚濇灏嗚閬跨殑鏅�瀛楃鎭㈠
            for (int i = 0; i < strs.length; i++) {
                strs[i] = strs[i].replaceAll("\\Q\\\\\\E", Matcher
                        .quoteReplacement("\\"));
                strs[i] = strs[i].replaceAll("\\Q\\|\\E", Matcher
                        .quoteReplacement("|"));
            }
            return strs;
        }
    }

    /**
     * 灏嗗瓧绗︿覆鏇挎崲涓篐TML鍏煎鐨勫瓧涓诧紝骞朵笖灏嗘崲琛屾浛鎹负<br/>
     * @param ori
     * @return
     */
    public static String escapeHtmlAndBreak(String ori){
        if(ori==null){
            return null;
        }else{
            return StringEscapeUtils.escapeHtml(ori).replaceAll("\n", "<br/>");
        }
    }

    /**
     * 鏍煎紡鍖栧瓧涓�
     * 濡傛灉str闀垮害澶т簬limitLen鍒欐埅鍙栬秴闀跨殑锛�
     * 濡傛灉limitLen闀垮害灏忎簬str闀垮害鍒欑敤repStr鍦╯tr灏鹃儴鍔犲埌str涓巐imitLen闀垮害涓�嚧
     * @param str 琚牸寮忓寲鐨勫瓧涓�
     * @param limitLen str闄愬埗鐨勯暱搴︼紝
     * @param repStr
     * @return
     */
    public static String stringRepeatformat(String str,int limitLen, boolean isFront, String repStr)
    {
        if(str.length()>limitLen)
        {
            return str.substring(0,limitLen);
        }
        if(isFront)
        {
            str= StringUtils.repeat(repStr, limitLen-str.length())+str;
        }
        else
        {
            str= str+StringUtils.repeat(repStr, limitLen-str.length());
        }
        return str.substring(0,limitLen);
    }


    /**
     * 鍔犲瘑MD5涓�2浣�
     * @param str 瑕佸姞瀵嗙殑瀛椾覆
     * @return 杩斿洖32浣嶉暱鐨�2杩涘埗瀛楃
     */
    public static String md5By32(String str)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++)
            {
                i = b[offset];
                if (i < 0)
                {
                    i += 256;
                }
                if (i < 16)
                {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString().toLowerCase();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 鍔犲瘑MD5涓�6浣�
     * @param str 瑕佸姞瀵嗙殑瀛椾覆
     * @return銆�繑鍥�6浣嶉暱鐨�6杩涘埗瀛楃
     */
    public static String md5By16(String str)
    {
        return md5By32(str).substring(8, 24);
    }

    /**
     * 姝ｅ父鏂囧瓧杞负UTF8鏍煎紡瀛椾覆
     * @param str 姝ｅ父鏂囧瓧
     * @return 杩斿洖UTF8鏍煎紡瀛椾覆
     */
    public static String character2UTF8(String str)
    {
        String tmp;
        StringBuffer sb = new StringBuffer(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++)
        {
            c = str.charAt(i);
            if (c > 255)
            {
                sb.append("\\u");
                j = (c >>> 8);
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1)
                    sb.append("0");
                sb.append(tmp);
                j = (c & 0xFF);
                tmp = Integer.toHexString(j);
                if (tmp.length() == 1)
                    sb.append("0");
                sb.append(tmp);
            } else
            {
                sb.append(c);
            }
        }
        return (new String(sb));
    }

    /**
     * UTF8鏍煎紡瀛椾覆杞负鍙湅寰楁噦鐨勬甯告枃瀛�
     * @param s UTF8鏍煎紡鐨勫瓧涓�
     * @return 杩斿洖鍙湅寰楁噦鐨勬甯告枃瀛�
     */
    public static String UTF82Character(String s)
    {
        if (s == null)
        {
            return null;
        }
        StringBuffer result = new StringBuffer();
        int savedI, i, ch;
        for (i = 0; i < s.length(); i++)
        {
            if ((ch = s.charAt(i)) == '\\')
            {
                if (s.length() > i + 1 && s.charAt(i + 1) == 'u')
                {
                    savedI = i;
                    i += 2;
                    while (s.length() > i && s.charAt(i) == 'u')
                    {
                        i++;
                    }
                    if (s.length() >= i + 4)
                    {
                        ch = Integer.parseInt(s.substring(i, i + 4), 16);
                        i += 3;
                    }
                    else
                    {
                        i = savedI;
                    }
                }
            }
            result.append((char) ch);
        }
        return result.toString();
    }

    /**
     * trim space chars and tab chars
     * @param str
     * @return
     */
    public static String trimST(String str)
    {
        if(str==null)
        {
            return null;
        }
        String regStrBegin="^[\\s\\t]*";
        String regStrEnd="[\\s\\t]*$";
        Matcher m=Pattern.compile(regStrBegin).matcher(str);
        if(m.find())
        {
            str= m.replaceFirst("");
        }

        m=Pattern.compile(regStrEnd).matcher(str);
        if(m.find())
        {
            str= m.replaceFirst("");
        }
        return str;
    }

    public static String trimStr(String srcStr,String trimStr)
    {
        String s=trimLeft(srcStr, trimStr);
        s=trimRight(srcStr, s);
        return s;
    }

    public static String trimLeft(String srcStr,String trimStr)
    {
        if(srcStr==null)
        {
            return null;
        }
        String regStrBegin="^"+trimStr;
        Matcher m=Pattern.compile(regStrBegin).matcher(srcStr);
        if(m.find())
        {
            srcStr= m.replaceFirst("");
        }
        return srcStr;
    }

    public static String trimRight(String srcStr,String trimStr)
    {
        if(srcStr==null)
        {
            return null;
        }
        String regStrBegin=trimStr+"$";
        Matcher m=Pattern.compile(regStrBegin).matcher(srcStr);
        if(m.find())
        {
            srcStr= m.replaceFirst("");
        }
        return srcStr;
    }

    public static int countStr(String source,String cntStr)
    {
        if(source==null || cntStr==null)
        {
            return 0;
        }
        if(source.indexOf(cntStr)<0)
        {
            return 0;
        }
        cntStr=cntStr.replace(".", "\\.");
        Pattern p=Pattern.compile(cntStr);
        Matcher m=p.matcher(source);
        int cnt=0;
        while(m.find())
        {
            cnt++;
        }
        return cnt;
    }

    /**
     * 鍒ゆ柇瀛楃涓叉槸鍚︽槸鏁存暟
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String s="abc  .abc  .abc";
        System.out.println(countStr(s," ")+"====");
    }

}
