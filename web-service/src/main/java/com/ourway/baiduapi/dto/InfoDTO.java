package com.ourway.baiduapi.dto;

/**
 * Created by D.chen.g on 2018/1/15.
 */
public class InfoDTO {
    private int code;
    private Object value;
    private String errorMess;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static InfoDTO success(Object object){
        InfoDTO res=new InfoDTO();
        res.code=1;
        res.value=object;
        return res;
    }
    public static InfoDTO error(String errorMess){
        InfoDTO res=new InfoDTO();
        res.code=0;
        res.errorMess=errorMess;
        return res;
    }

    public String getErrorMess() {
        return errorMess;
    }

    public void setErrorMess(String errorMess) {
        this.errorMess = errorMess;
    }
}
