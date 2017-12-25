package com.ourway.base.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简单的Properties处理类
 * 只满足文件中有单行注释"#"、单行key=value形式，如#、key、value当中有换行数据均可能 出错
 */
public class Properties {
    private String COMMENT_CHAR = "#";
    private String SPILT_STR = " = ";

    private Map<String, String> propertiesMap = new LinkedHashMap<String, String>();
    private Log log = LogFactory.getLog(Properties.class);
    private String path;

    public Properties() {
    }

    public Properties(String path) {
        this.path = path;
    }

    public void load() {
        load(path);
    }

    /**
     * 初始化Properties实例，调用path指定的config properties文件，出错记录异常且调用Properties对象报错。
     *
     * @param path config properties文件路径
     */
    public void load(String path) {
        this.path = path;
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            load(fr);
        } catch (FileNotFoundException e) {
            log.error("无法读取properties文件!");
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }
    }


    public void load(InputStreamReader isr) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(isr);
            String line = null;
            while ((line = trimST(br.readLine())) != null) {
                /*
                 * 是注释
				 */
                if (line.startsWith(COMMENT_CHAR)) {
                    continue;
                }
                if (line.length() == 0) {
                    continue;
                }
                String[] kv = line.split("=");
                if (kv.length == 1) {
                    propertiesMap.put(trimST(kv[0]), "");
                    continue;
                }
                propertiesMap.put(trimST(kv[0]), trimST(kv[1]));

            }
        } catch (Exception e) {
            log.error("加载configs配置文件出错", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }

    }


    /**
     * 取得config.properties或config-xxx.properties文件中key对应的值
     *
     * @param <T> 目前只支持String、Integer、Double
     * @param key key properties中的key名称
     * @return 存在返回对应的值否则返回null
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String key) {
        String v = propertiesMap.get(key);
        if (!isNumber(v)) {
            return (T) v;
        }
        @SuppressWarnings("rawtypes")
        Class c = null;

        String v1 = v;
//		if(v1.startsWith("-"))
//		{
//			v1=v1.substring(1);
//		}
        if (v1.indexOf(".") > 0) {
            c = Double.class;
        } else {
            try {
                Long l = new Long(v);
                if (l <= Integer.MAX_VALUE || l >= Integer.MIN_VALUE) {
                    c = Integer.class;
                } else {
                    c = Long.class;
                }
            } catch (Exception e) {
                log.error("数据转换出错", e);
                return (T) v;
            }
//			if(v1.length()>(Integer.MAX_VALUE+"").length())
//			{
//				c=Long.class;
//			}
//			else if(v1.compareTo(Integer.MAX_VALUE+"")>0)
//			{
//				c=Long.class;
//			}
//			else
//			{
//				c=Integer.class;
//			}
        }
        return (T) parse(v, c);
    }

    @SuppressWarnings("unchecked")
    private <T> T parse(String numberString, Class<? extends Number> clz) {
        try {
            return (T) clz.getConstructor(String.class).newInstance(numberString);
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    /**
     * 保存key对应的value值到Properties对象中
     *
     * @param key   String
     * @param value Object value
     */
    public void setProperty(String key, String value) {
        propertiesMap.put(key, value);
    }

    /**
     * 保存key对应的value值到Properties对象中(可带该key的功能注释说明)
     *
     * @param key     String
     * @param value   Object value
     * @param comment 注释
     */
    public void setProperty(String key, String value, String comment) {
        propertiesMap.put(COMMENT_CHAR + comment, null);
        propertiesMap.put(key, value);
    }

    /**
     * 保存到文件中，注意如果调用该方法时，所有的key,value应为字串或数字，对于其它类型则调用其toString()的值或对象指针地址
     */
    public void store() {
        List<String> pList = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = trimST(br.readLine())) != null) {
                if (line.length() == 0) {
                    continue;
                }
                pList.add(line);
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(path));
            for (String key : propertiesMap.keySet()) {
                for (int i = 0; i < pList.size(); i++) {
                    String lineContent = pList.get(i);
                    /*
                     * 是注释则跳过
					 */
                    if (lineContent.startsWith(COMMENT_CHAR)) {
                        continue;
                    }
                    Pattern keyPattern = Pattern.compile("^" + key);
                    Matcher keyMatcher = keyPattern.matcher(lineContent);
                    Pattern contentPattern = Pattern.compile("=(\\s*)" + propertiesMap.get(key));
                    Matcher contentMatcher = contentPattern.matcher(lineContent);

					/*
                     * 修改不相同的内容
					 */
                    if (keyMatcher.find() && !contentMatcher.find()) {
                        lineContent = key + SPILT_STR + propertiesMap.get(key);
                        pList.set(i, lineContent);
                    }
                }
            }
            for (int i = 0; i < pList.size(); i++) {
                String l = pList.get(i);
                if (l.startsWith(COMMENT_CHAR)) {
                    bw.newLine();
                }
                bw.write(l);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            log.error(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    log.error(e);
                }
            }
        }

    }

    /**
     * 取Properties中所有key对应对象到map中，这些值可能含注释的值，如果以key第一个字母以”#“出现的则
     * 属于注释值。
     *
     * @return 存在返回不为null对象
     */
    public Map<String, String> getProperties() {
        return this.propertiesMap;
    }

    /**
     * 批量添加key=value值到Properties对象中
     *
     * @param properties Map对象
     */
    public void add(Map<String, String> properties) {
        propertiesMap.putAll(properties);
    }

    private String trimST(String str) {
        if (str == null) {
            return null;
        }
        String regStrBegin = "^[\\s\\t]*";
        String regStrEnd = "[\\s\\t]*$";
        Matcher m = Pattern.compile(regStrBegin).matcher(str);
        if (m.find()) {
            str = m.replaceFirst("");
        }

        m = Pattern.compile(regStrEnd).matcher(str);
        if (m.find()) {
            str = m.replaceFirst("");
        }
        return str;
    }

    private boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
		/*
		 * 判断是否为数字带小数点
		 */
        String regEx = "^(\\-?)\\d*(\\.?(\\d+))$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str.trim());
        return m.find();
    }
}
