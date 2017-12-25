package com.ourway.base.message;

import java.io.Serializable;

/**
 * 处理出错或提示用的消息类
 */
public class MessageInfo implements Serializable {


    private static final long serialVersionUID = 2472361763381710021L;
    public final static int MESSAGE_LEVEL_FRAMEWORK_SEVERE = 0;
    public final static int MESSAGE_LEVEL_BIZ_SEVERE = 1;
    public final static int MESSAGE_LEVEL_WARNING = 2;
    public final static int MESSAGE_LEVEL_NOTICE = 3;

    private String message;
    //	private Object[] params;
    private int level;

    public MessageInfo() {
    }

    /**
     * @param message
     * @param params
     * @param level
     */
    public MessageInfo(int level, String message, Object[] params) {
        this.message = message;
//		this.params = params;
        this.level = level;
    }

    /**
     * @param msgType 消息类型。一般自定义提示用
     * @param level
     * @param message
     * @param params
     */
    public MessageInfo(String msgType, int level, String message, Object[] params) {
//		this.msgType=msgType;
        this.message = message;
//		this.params = params;
        this.level = level;
    }

    public String getMessage() {
        if (message == null || message.trim().length() == 0) {
            return message;
        }
//		message=MessageFormat.format(message,params);
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //	public Object[] getParams()
//	{
//		return params;
//	}
//	public void setParams(Object... params)
//	{
//		this.params = params;
//	}
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }
}
