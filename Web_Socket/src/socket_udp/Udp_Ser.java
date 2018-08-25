package socket_udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Udp_Ser {
public static void main(String[] args) throws IOException {
	//1创建服务器端DatagramSocket 指定端口
	DatagramSocket socket = new DatagramSocket(8800);
	//2创建数据报 用于接收客户端发送的数据
	byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
	DatagramPacket packet = new DatagramPacket(data,data.length);
	//3接收客户端发送的数据
	System.out.println("服务器已经启动给");
	socket.receive(packet);//此方法在接收到数据报之前会一直阻塞
	//4读取数据
	String info = new String(data,0,packet.getLength());
	System.out.println("我是服务器，客户端说"+info);

	
	//想客户端响应数据
	//1定义客户端的地址/端口号/数据
	InetAddress address = packet.getAddress();
	int port = packet.getPort();
	byte[] data2 = "欢迎您".getBytes();
	//2创建数据报，包含响应的数据信息
	DatagramPacket packet2 = new DatagramPacket(data2,data2.length,address,port);
	//3响应客户端
	socket.send(packet2);
	//4关闭资源
	socket.close();
}
}
