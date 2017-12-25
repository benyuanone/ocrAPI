package com.ourway.base.socketServer;

import org.apache.log4j.Logger;
import org.quickserver.net.server.ClientCommandHandler;
import org.quickserver.net.server.ClientHandler;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class EchoCommandHandler implements ClientCommandHandler {
	private static int totalCount = 0;
	private static Logger logger = Logger.getLogger(EchoCommandHandler.class);

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

	public void handleCommand(ClientHandler handler, String command) throws SocketTimeoutException, IOException {
		try {
			// System.out.println("Handler Name : " + handler.getName());
			Quicker.isBeginDeal = true;
			String deviceNo = "";// 设备编号
			String seq = "";// 设备顺序
			String commandType = "";// 包类型
			int type = 0;
			System.out.println("====" + command);
			handler.sendClientMsg(command);
			if (command.startsWith("5446") && command.length() > 16) {
				deviceNo = command.substring(18, 34);// 获取设备编号
				seq = command.substring(34, 36);// 包序号
				commandType = command.substring(16, 18);// 包序号
				type = Integer.parseInt(commandType, 16);
				// 更新客户端socket连接的信息
				Quicker.clients.put(deviceNo, handler);
				Quicker.socketDeviceNoMap.put(handler.getName(), deviceNo);
				Quicker.ipClients.put(handler.getSocket().getInetAddress().getHostAddress(), handler);
				switch (type) {
				case 1:
					// 心跳
//					handler.sendClientMsg(RuleDeal.genBackHeart(deviceNo, seq));// 心跳返回
					break;
				default:
//					handler.sendClientMsg(RuleDeal.genBackOthers(deviceNo, seq));// 其他心跳返回
				}
//				logger.info(command);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println("=====" + totalCount);
	}

}
