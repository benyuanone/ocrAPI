package com.ourway.base.log;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Administrator on 2017/3/10.
 */
public class LogWriter extends Writer {

    private LogProcesser lp;
    private LogWriterAppender swa;

    public LogWriter(LogWriterAppender swa) {
        this.swa = swa;
    }

    private void init() {
        if (swa == null) {
            return;
        }
        if (lp != null) {
            return;
        }
        String logProcesserClass = swa.getLogProcesserClass();
        if (logProcesserClass == null || logProcesserClass.trim().length() == 0) {
            return;
        }

        try {
            @SuppressWarnings("unchecked")
            Class<LogProcesser> lpc = (Class<LogProcesser>) Class.forName(logProcesserClass);
            this.lp = (LogProcesser) lpc.newInstance();
        } catch (Exception e) {
        }
    }

    @Override
    public void write(char[] msg, int offset, int len) throws IOException {
        init();
        if (lp == null) {
            return;
        }
        lp.receiveLog4jMsg(new String(msg, offset, len));
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }


}
