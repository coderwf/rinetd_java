package rinetd.core.connector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import rinetd.pojo.AddrAndPort;
import rinetd.pojo.TransferLink;

//维持到目的地址的连接池
//如果长时间不连接尝试回收该连接
//只有第一个请求到达时就维持连接
//为每个连接维持一个锁防止并发连接
//只能用心跳包来试探对方是否关闭了连接
//每一次转发更新时间,两次时间超过间隔就将socket关闭移除
//如果对方关闭了连接,这边如何判断

public class Connector {
	private final Map<String, Lock>     locks = new HashMap<>();
	private final Map<String, Socket>   sockets = new HashMap<>();
	public Connector(List<AddrAndPort> addrAndPortList) {
		initLocksAndsocket(addrAndPortList);  // 初始化锁
	}//
	
	private void initLocksAndsocket(List<AddrAndPort> addrAndPortList) {
		for(AddrAndPort ap:addrAndPortList) {
			String key = ap.getIp() + ap.getPort();
			locks.put(key, new ReentrantLock());
		}//for
	}//
	
	public String send(String msg,AddrAndPort addrAndPort) {
		//拿到socket
		Socket socket = getSocket(addrAndPort);
		if(socket == null) {
			return null;
		}//if
		try {
		    OutputStream outputStream = socket.getOutputStream();
		    Writer writer = new OutputStreamWriter(outputStream);
		    writer.write(msg);
		    InputStream inputStream = socket.getInputStream();
		    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		    String res = bufferedReader.readLine();
		    return res;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}//
	}//send
	
	private Socket getSocket(AddrAndPort addrAndPort) {
		String addr = addrAndPort.getIp() + addrAndPort.getPort();
		Socket socket = sockets.get(addr);
		try {
			locks.get(addr).lock();
			if(socket == null) {
			socket = new Socket(addrAndPort.getIp(),addrAndPort.getPort());
			sockets.put(addr, socket);
			}//if
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			locks.get(addr).unlock();
		}//finally
	    return socket;
    }//getSocket
}//class
