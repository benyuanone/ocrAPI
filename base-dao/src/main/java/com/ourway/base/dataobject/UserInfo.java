package com.ourway.base.dataobject;

import org.apache.commons.collections.map.HashedMap;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;


/**
 * <p>方法 UserInfo  </p>
 * <p>说明:用户底层对象，解决一些常规的用户登录问题，对于扩展属性转成Map放到ext属性中</p>
 * <pre>
 * @author JackZhou
 * @date 2017/3/11 15:37
 * </pre>
 */
public class UserInfo implements Serializable {
    /*用户登陆用id*/
    private String userId;
    /*用户主键*/
    private String id;
    /*真实姓名*/
    private String realName;
    /*昵称*/
    private String nickName;
    /*密码*/
    private String password;
    /*头像*/
    private String headIcon;
    /*session*/
    private String sessionId;
    /*用户类型*/
    private String userType;
    /*登陆设备类型*/
    private String deviceId;
    /*登陆ip地址*/
    private String remoteIp;
    /*用户状态*/
    private Short status;
    /*登陆时间*/
    private Long accessedTime;
    /*登陆平台*/
    private String platform;
    /*扩展属性*/
    private Map<String, Object> extInfo = new HashedMap();
    /*个人可进入的链接*/
    private static final Set<String> WebHomeUrls = new HashSet<String>();


    public UserInfo() {
    }


    public UserInfo(String userId, String id, String realName, String password) {
        super();
        this.userId = userId;
        this.id = id;
        this.realName = realName;
        this.password = password;
    }

    public Long getAccessedTime() {
        return accessedTime;
    }

    public void setAccessedTime(Long accessedTime) {
        this.accessedTime = accessedTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }


    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }


    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }


    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, Object> extInfo) {
        this.extInfo = extInfo;
    }

    public static Set<String> getWebHomeUrls() {
        return WebHomeUrls;
    }


    public String getRemoteIp() {
        if (remoteIp != null) {
            return remoteIp;
        }
////        if (request == null) {
////            try {
////                request = GetRequestUtil.getReq();
////                if (request == null) {
////                    return null;
////                }
////            } catch (Exception e) {
////                Log.debug("获取request对象出错", e);
////                return null;
////            }
////        }
//        Enumeration<String> hns = request.getHeaderNames();
//        String ip = null;
//        while (hns.hasMoreElements()) {
//            String name = hns.nextElement();
//            if ("x-real-ip".indexOf(name.toLowerCase()) >= 0) {
//                ip = request.getHeader(name);
//                if (ip == null || ip.length() == 0 || ip.toLowerCase().indexOf("unknown") >= 0) {
//                    continue;
//                }
//                break;
//            }
//            if ("remote-host".indexOf(name.toLowerCase()) >= 0) {
//                ip = request.getHeader(name);
//                if (ip == null || ip.length() == 0 || ip.toLowerCase().indexOf("unknown") >= 0) {
//                    continue;
//                }
//                break;
//            }
//
//            if ("x-forwarded-for".indexOf(name.toLowerCase()) >= 0) {
//                ip = request.getHeader(name);
//                if (ip == null || ip.length() == 0 || ip.toLowerCase().indexOf("unknown") >= 0) {
//                    continue;
//                }
//                break;
//            }
//        }
//        if (ip == null || ip.length() == 0 || ip.toLowerCase().indexOf("unknown") >= 0) {
//            ip = request.getRemoteAddr();
//        }
//        remoteIp = ip;
        return remoteIp;
    }


    /**
     * <p>方法:addUserExtInfo 添加扩展属性 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li> @param value 对应结果</li>
     * <li>@return void  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 15:42  </li>
     * </ul>
     */
    public void addUserExtInfo(String key, Object value) {
        if (extInfo == null) {
            extInfo = new HashMap<String, Object>();
        }
        extInfo.put(key, value);
    }


    /**
     * <p>方法:getUserExtInfo 获取用户对象 </p>
     * <ul>
     * <li> @param key 关键字</li>
     * <li>@return java.lang.Object  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/3/11 15:43  </li>
     * </ul>
     */
    public Object getUserExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }


}
