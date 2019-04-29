package me.zhongmin.zero.sockets.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int port = 8081;
		if (args != null && args.length > 0) {

			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				// 采用默认值
			}

		}
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			System.out.println("The time 服务已启动  in port : " + port);
			Socket socket = null;
			while (true) {
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();
			}
		} finally {
			if (server != null) {
				System.out.println("The time server close");
				server.close();
				server = null;
			}
		}
	}
}
