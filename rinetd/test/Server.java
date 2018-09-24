package rinetd.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//1.测试在一方关闭连接另一方继续发送时另一方的反应
//如果一方关闭了连接那么这边继续发送数据不会有任何异常
//isConnected()之类函数只能判断本地状态
//
//服务器在10s后关闭连接,客户端在15s后继续发送数据
//2.监听端口和通信端口是一个端口么
//自定义心跳包,需要和服务器协商好
//3.怎么发送心跳包 类似于ping
//4.只关闭输入流或者输出流端口


public class Server {
	private static String host   = "127.0.0.1";
	private static Integer port  = 9999;
    public static void main(String args[]) {
    	try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }//main
    
    public static void start() throws IOException, InterruptedException {
    	ServerSocket sSocket = new ServerSocket(port);
    	Socket client = sSocket.accept();
    	System.out.println(client.getKeepAlive());
    	System.out.println(client.getPort());
    	while(true) {
    		Thread.sleep(10000);
    		break;
    	}//while
    	client.shutdownInput();
    }//
}//Server
