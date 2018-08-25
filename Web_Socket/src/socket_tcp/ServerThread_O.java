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

public class ServerThread_O extends Thread{
	//和本线程相关的Socket
	Socket socket = null;
	
	public ServerThread_O(Socket socket) {
		this.socket = socket;
	}
	
	//线程执行的操作，响应客户端的请求
	public void run(){
		
		InputStream is=null;
		
		OutputStream os=null;
		try {
			
			//3.获取输入流，并读取客户端信息
			is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			//isr = new InputStreamReader(is);
			//br = new BufferedReader(isr);
			//String info=null;
			/*while((info=br.readLine())!=null){//循环读取客户端信息
				System.out.println("我是服务器，客户端说"+info);
				
			}*/
			try {
				System.out.println(ois.readObject());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			socket.shutdownInput();//关闭输入流
			//4.获取输出流，响应客户端请求
			os = socket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(new User("123","123"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//5.关闭资源
			try {
				
				if(socket!=null)
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
