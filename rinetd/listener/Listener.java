package rinetd.listener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;

import rinetd.core.connector.Connector;
import rinetd.pojo.AddrAndPort;

//������Ҫת����ip��port
//�����߳�,�����˿�Ȼ���ٴο����̴߳�������ת��
public class Listener {
	public Listener(List<AddrAndPort> addrAndPorts) {
		initListenerPortAndAddr(addrAndPorts);
	}//
	private void initListenerPortAndAddr(List<AddrAndPort> addrAndPorts) {
		
	}//
    public void start() {
    	
    }//start
}//Listener

class ListenerThread extends Thread{
	private String ip;
	private Integer port;
	public ListenerThread(String ip,Integer port) {
		this.ip    = ip;
		this.port  = port;
	}//ListenerThread
	
	public void run() {
	   try {
		   SocketAddress sAddr = new InetSocketAddress(this.ip,this.port);
		    ServerSocket sSocket = new ServerSocket();
		    sSocket.bind(sAddr);
			while(true) {
				Socket client = sSocket.accept(); // �ȴ�Դ��ַ����������
				//����һ���̴߳���������ӵ�ת������
				new TransferHandler(client,null).start();
			}//while
	    }catch (Exception e) {
		    e.printStackTrace();
	    }//catch
    }//run
}//class

//������ϢȻ�����connector�õ��ظ�,�ٽ��ظ�����Դ��ַ
class TransferHandler extends Thread{
	private Socket client;
	private Connector connector = new Connector(null);
	private AddrAndPort addrAndPort;//ת���������ַ���õ��ظ�
	public TransferHandler(Socket client,AddrAndPort addrAndPort) {
		this.client      = client;
		this.addrAndPort = addrAndPort;
	}//
	
	public void run() {
		try {
			OutputStream outputStream = client.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"utf-8");
			InputStream inputStream  = client.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while(true) {
				String msg = bufferedReader.readLine();
				String res = connector.send(msg, null);
				outputStreamWriter.write(res);
				outputStreamWriter.flush();
			}//while
		}catch(Exception e){
			e.printStackTrace();
			
		}//catch
	}//run
}//