package socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Socket_ser_F {
	public static void main(String[] args) {
		
		try {
			//1.创建一个服务器端Socket，即ServerSocket，制定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(8888);
			
			System.out.println("***服务器即将启动，等待客户端的连接***");
			Socket socket = null;
			//记录客户端数量
			int count = 0;
			//循环监听等待客户端的连接
			while(true){
				//2.调用accept()方法开始监听，等待客户端的连接
				socket = serverSocket.accept();
				//创建一个新的线程
				ServerThread serverThread = new ServerThread(socket);
				//启动线程
				serverThread.start();
				count++;//统计客户端数量
				System.out.println("客户端数量"+count);
				InetAddress address = socket.getInetAddress();
				System.out.println("当前客户端ip地址"+address.getHostAddress());
			}
			//serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
