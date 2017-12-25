package com.ourway.init;

import com.ourway.base.service.BaseInitService;
import com.ourway.base.socketServer.Quicker;
import org.quickserver.net.server.QuickServer;

/**
 * Created by Administrator on 2017/9/23.
 */
public class SocketInit implements BaseInitService {
    @Override
    public void init() {
        if (Quicker.myServer.isClosed()) {
            Quicker.initServer(8006);
            Quicker.start();
        }
    }
}
