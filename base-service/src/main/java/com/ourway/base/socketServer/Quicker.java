package com.ourway.base.socketServer;

import org.quickserver.net.server.ClientHandler;
import org.quickserver.net.server.QuickServer;
import org.quickserver.util.xmlreader.DefaultDataMode;

import java.io.IOException;
import java.util.*;

/**
 * Created by D.chen.g on 2017/3/14.
 */
public class Quicker {
    public static QuickServer myServer = new QuickServer();// socket服务
    public static Map<String, ClientHandler> clients = new HashMap<String, ClientHandler>();// 所有当前的终端
    public static Map<String, Date> clientsHearts = new HashMap<String, Date>();// 最后一次心跳的时间
    public static Map<String, ClientHandler> ipClients = new HashMap<String, ClientHandler>();// ip地址对应的客户端
    public static Map<String, Thread> threadMap = new HashMap<String, Thread>();// 当前的线程名称和对象
    public static Map<String, Integer> threadMapStatus = new HashMap<String, Integer>();// 当前线程的状态
    public static boolean isBeginDeal = false;// 是否开始数据处理
    public static Map<String, String> socketDeviceNoMap = new HashMap<String, String>();// socket编号和设备编号对应关系

    public static void initServer(int port) {
        System.out.println("starting TCP PORT -----" + port + "------");
        myServer.setPort(port);
//         myServer.setClientCommandHandler("com.ourway.base.socketServer.EchoCommandHandler");
        myServer.setClientBinaryHandler("com.ourway.base.socketServer.EchoBinaryHandler");
        myServer.setClientEventHandler("com.ourway.base.socketServer.EchoBinaryHandler");
        myServer.setMaxConnection(50000);
//		myServer.set`
        DefaultDataMode dfmode = new DefaultDataMode();
        dfmode.setDataModeIn("Binary");
//        dfmode.setDataModeIn("STRING");
        // dfmode.setDataModeOut("Byte");
        dfmode.setDataModeOut("Binary");
//        dfmode.setDataModeOut("STRING");
        try {
            myServer.setDefaultDataMode(dfmode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        myServer.setTimeout(60*60*1000);
        myServer.setName("监听服务器");
    }

    public static String getStatus() {
        String back = "";
        back += "服务器名称：[" + myServer.getName() + "],当前端口为：[" + myServer.getPort() + "],当前状态：[" + (myServer.getServiceState() <= 0 ? "关闭状态" : "启用状态") + "]，最大连接数：[" + myServer.getMaxConnection()
                + "]，当前连接数:[" + myServer.getClientCount() + "]";
        return back;
    }

    public static void start() {
        try {
            Quicker.isBeginDeal = true;
            myServer.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void suspand() {
        try {
            myServer.suspendService();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void stop() {
        try {
            Quicker.isBeginDeal = false;
            myServer.stopServer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // QuickServer server = new QuickServer("com.test.EchoCommandHandler");
        // server.setPort(4123);
        // server.setName("testing");
        // try {
        // server.startServer();
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        String s = "D0000000";
        System.out.println(s.substring(0,8-2)+"22");

    }
}
