package com.ourway.base.utils;

/**
 * <p>方法 ValidateMsg : <p>
 * <p>说明:校验成功的类，跟validateUtils进行配合使用</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/24 23:50
 * </pre>
 */
public class ValidateMsg implements java.io.Serializable {
    public static final int EMPTY_FAIL = 0;
    public static final int DATE_FAIL = 1;
    public static final int NUMBER_FAIL = 2;

    /*是否校验成功*/
    private Boolean isSuccess;
    /*校验失败的项目*/
    private StringBuilder keys = new StringBuilder();
    private Integer checkType;

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public StringBuilder getKeys() {
        return keys;
    }

    public void setKeys(StringBuilder keys) {
        this.keys = keys;
    }

    /**
     * <p>方法:instance 实例化 </p>
     * <ul>
     * <li> @param result 结果</li>
     * <li>@return com.ourway.base.utils.ValidateMsg  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/4/24 23:56  </li>
     * </ul>
     */
    public static ValidateMsg instance(int checkType) {
        ValidateMsg validateMsg = new ValidateMsg();
        validateMsg.setCheckType(checkType);
        return validateMsg;
    }

    public String toString() {
        switch (this.checkType) {
            case 0:
                return "必填项:" + keys.toString();
            case 1:
                return "时间格式错误：" + keys.toString();
            case 2:
                return "数字格式：" + keys.toString();
        }
        return "";
    }
}
