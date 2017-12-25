package com.ourway.base.socketServer;

import java.util.Set;

/**
 * Created by D.chen.g on 2017/3/20.
 */
public class ThreadUtils {


    public static void startThread(String threadName) {
        if (null == Quicker.threadMap.get(threadName)) {
//            DataThread deal = new DataThread(threadName);
//            Thread t = new Thread(deal);
//            t.start();
//            Quicker.threadMap.put(threadName, deal);
            Quicker.threadMapStatus.put(threadName, 1);// 进入运行池
        } else {
            Thread t = Quicker.threadMap.get(threadName);
            if (t.isAlive())
                t.start();
            Quicker.threadMapStatus.put(threadName, 1);// 进入运行池
        }
    }

    public static void closeThread() {
        Set set = Quicker.threadMap.keySet();
        for (Object object : set) {
            Thread t = Quicker.threadMap.get(object.toString());
//            DataThread _data = (DataThread) t;
//            _data.setFlag(false);
            Quicker.threadMapStatus.put(object.toString(), -1);// 进入运行池
        }
        Quicker.threadMap.clear();
    }

    /**
     * <p>
     *
     * </p>
     *
     * @author Jack Zhou
     * @version $Id: ThreadUtils.java,v 0.1 2015-11-13 上午11:20:13 Jack Exp $
     */
    public static void main(String[] args) {

    }


}
