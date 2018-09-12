package rinetd_java;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/*监听线程负责监听客户端的消息
 *
 */
public class ListenThread extends Thread{
    private String fromIp;
    private String fromPort;
    private String toIp;
    private String toPort ;
    ServerSocket serverSocket = null;
    public ListenThread(TransItem item) {
		this.fromIp    = item.getFromIp();
		this.fromPort  = item.getFromPort();
		this.toIp      =item.getToIp();
		this.toPort    = item.getToPort();
	}
    
    public void run(){
    	InetSocketAddress bindAddr = new InetSocketAddress(this.fromIp, Integer.valueOf(this.fromPort));
    	try {
			serverSocket = new ServerSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			serverSocket.bind(bindAddr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	while(true) {//当得到一个连接时将请求转发到目的服务器然后将结果返回给客户端
    		try {
				serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}//while
    }//run
}
