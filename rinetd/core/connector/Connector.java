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

//ά�ֵ�Ŀ�ĵ�ַ�����ӳ�
//�����ʱ�䲻���ӳ��Ի��ո�����
//ֻ�е�һ�����󵽴�ʱ��ά������
//Ϊÿ������ά��һ������ֹ��������
//ֻ��������������̽�Է��Ƿ�ر�������
//ÿһ��ת������ʱ��,����ʱ�䳬������ͽ�socket�ر��Ƴ�
//����Է��ر�������,�������ж�

public class Connector {
	private final Map<String, Lock>     locks = new HashMap<>();
	private final Map<String, Socket>   sockets = new HashMap<>();
	public Connector(List<AddrAndPort> addrAndPortList) {
		initLocksAndsocket(addrAndPortList);  // ��ʼ����
	}//
	
	private void initLocksAndsocket(List<AddrAndPort> addrAndPortList) {
		for(AddrAndPort ap:addrAndPortList) {
			String key = ap.getIp() + ap.getPort();
			locks.put(key, new ReentrantLock());
		}//for
	}//
	
	public String send(String msg,AddrAndPort addrAndPort) {
		//�õ�socket
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
