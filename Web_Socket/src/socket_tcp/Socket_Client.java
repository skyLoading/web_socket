package socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Socket_Client {
	public static void main(String[] args) {
		try {
			//1创建客户端Socket，指定服务器地址和端口
			Socket socket = new Socket("localhost",8888);
			//2获取输出流，向服务器端发送信息
			OutputStream os =socket.getOutputStream();//字节输出流
			PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
			pw.write("用户名：cc；密码：123");
			pw.flush();
			socket.shutdownOutput();//关闭输出流
			//3获取输入流
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info=null;
			while((info=br.readLine())!=null){//循环读取客户端信息
				System.out.println("我是客户端，服务器说"+info);
				
			}
			//4关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
