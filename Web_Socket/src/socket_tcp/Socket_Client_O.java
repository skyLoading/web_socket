package socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Socket_Client_O {
	public static void main(String[] args) {
		try {
			//1创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost",8888);
			//2获取输出流，向服务器端发送信息
			OutputStream os =socket.getOutputStream();//字节输出流
			//使用ObjectOutputStream对象序列化流，传递对象
			ObjectOutputStream oos = new ObjectOutputStream(os);
			User user = new User("admin","123");
			oos.writeObject(user);
			socket.shutdownOutput();//关闭输出流
			//3获取输入流
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			try {
				User use = (User) ois.readObject();
				System.out.println("我是客户端，服务器说"+use);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//4关闭资源
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
