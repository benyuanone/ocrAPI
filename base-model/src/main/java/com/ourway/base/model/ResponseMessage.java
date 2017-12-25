package com.ourway.base.model;

import java.io.Serializable;

/**
 * <p>方法 ResponseMessage : <p>
 * <p>说明:Json返回对象</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/12 15:12
 * </pre>
 */
public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = -3542357415146381433L;
    public static final Integer OK = 0;
    public static final Integer FAIL = -1;
    public static final Integer NOITEM = -2;
    public static final Integer RELOGIN = -3;//必须重新登录
    private String errorMess;
    private int backCode;
    private Object bean;

    public String getErrorMess() {
        return errorMess;
    }

    public void setErrorMess(String errorMess) {
        this.errorMess = errorMess;
    }

    public int getBackCode() {
        return backCode;
    }

    public void setBackCode(int backCode) {
        this.backCode = backCode;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
    *<p>方法:sendError 错误信息 </p>
    *<ul>
     *<li> @param errorCode 错误码</li>
     *<li> @param errorMess 错误信息代码或者内容</li>
    *<li>@return com.ourway.base.model.ResponseMessage  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/12 21:09  </li>
    *</ul>
    */
    public static ResponseMessage sendError(int errorCode,String errorMess){
        ResponseMessage message = new ResponseMessage();
        message.setBackCode(errorCode);
        message.setErrorMess(errorMess);
        message.setBean(null);
        return message;
    }

    /**
    *<p>方法:sendOK 执行成功的提示 </p>
    *<ul>
     *<li> @param bean 成功后的对象</li>
    *<li>@return com.ourway.base.model.ResponseMessage  </li>
    *<li>@author JackZhou </li>
    *<li>@date 2017/4/12 21:11  </li>
    *</ul>
    */
    public static ResponseMessage sendOK(Object bean){
        ResponseMessage message = new ResponseMessage();
        message.setBackCode(ResponseMessage.OK);
        message.setErrorMess("");
        message.setBean(bean);
        return message;
    }
}
