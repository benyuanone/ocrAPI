package com.ourway.base.log;

import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;

/**
 * Created by Administrator on 2017/3/10.
 */
public class LogWriterAppender extends WriterAppender {
    private String logProcesserClass;
    private LogWriter logw;

    public LogWriterAppender() {
        super();
        logw = new LogWriter(this);
        setWriter(logw);
    }

    public LogWriterAppender(Layout layout) {
        this();
        super.layout = layout;
    }

    public String getLogProcesserClass() {
        return logProcesserClass;
    }

    public void setLogProcesserClass(String logProcesserClass) {
        this.logProcesserClass = logProcesserClass;
    }


}
