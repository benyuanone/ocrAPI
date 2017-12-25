package com.ourway.base.socketServer;

import org.apache.log4j.Logger;
import org.quickserver.net.server.ClientBinaryHandler;
import org.quickserver.net.server.ClientEventHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;


public class EchoBinaryHandler implements ClientEventHandler, ClientBinaryHandler {
	private static int totalCount = 0;
	private static Logger logger = Logger.getLogger(EchoBinaryHandler.class);

	public void gotConnected(ClientHandler handler) throws SocketTimeoutException, IOException {
		Quicker.ipClients.put(handler.getSocket().getInetAddress().getHostAddress(), handler);
		totalCount++;
		System.out.println("========================" + totalCount);
	}

	public void lostConnection(ClientHandler handler) throws IOException {
		Quicker.ipClients.put(handler.getSocket().getInetAddress().getHostAddress(), null);
		totalCount--;
	}

	public void closingConnection(ClientHandler handler) throws IOException {
		Quicker.ipClients.put(handler.getSocket().getInetAddress().getHostAddress(), null);
		totalCount--;
	}

	public void handleBinary(ClientHandler handler, byte command[]) throws SocketTimeoutException, IOException {
		try {
			handler.getSocket().setTcpNoDelay(true);
			
			System.out.println("==========+++++++=========" + command.length);
			// Quicker.isBeginDeal = true;
			String deviceNo = "";// 设备编号
			int commandType = 0;// 包类型
			String commandTime = "";//包时间,yyyy-mm-dd hh:mm:ss
			String mess = "";
			int _b = 0;
			
			if (null != command && command.length > 20) {
				for (int i = 0; i < command.length; i++) {
					// System.out.println(command[i]);
					if (command[i] < 0)
						_b = 256 + command[i];
					else
						_b = command[i];
					System.out.println("_b"+_b);
					mess += byte2HexString(_b, 2);
				}
				if (mess.startsWith("5a54")) {

//					deviceNo = RuleDeal.convertHexToChar(mess.substring(0, 28));// 获取设备编号
//					seq = Integer.parseInt(mess.substring(28, 36),16);// 包序号
//					commandTime=RuleDeal.convertHex2Date(mess.substring(36,48));//时间
//					commandPdu = Integer.parseInt(mess.substring(48, 52),16);
//					//表示无效数据，不记录
//					if(commandPdu<=0)
//						return;
//					commandType = Integer.parseInt(mess.substring(52, 54),16);// 包类型
					Quicker.clients.put(deviceNo, handler);
					Quicker.socketDeviceNoMap.put(handler.getName(), deviceNo);
					Quicker.ipClients.put(handler.getSocket().getInetAddress().getHostAddress(), handler);
					Quicker.clientsHearts.put(deviceNo, new Date());
//					//不做批量处理，接受到一条就处理一条
////					logger.info(mess);
//					handler.sendClientBinary(RuleDeal.genBackHeart(deviceNo, seq, commandType));// 数据返回
//
//					//直接处理数据
//					HandleDataUtils.handle(mess);
				}
				System.out.println(mess);
				System.out.println(RuleDeal.convertHexToChar(mess));
			}
			handler.sendClientBinary(command);// 数据返回
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String byte2HexString(int b, int len) {
		String mess = Integer.toHexString(b);
		String back = "";
		if (mess.length() < len) {
			for (int i = 0; i < (len - mess.length()); i++) {
				back += "0";
			}
		}
		back += mess;
		return back;
	}

}
