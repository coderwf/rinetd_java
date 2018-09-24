package rinetd.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//1.������һ���ر�������һ����������ʱ��һ���ķ�Ӧ
//���һ���ر���������ô��߼����������ݲ������κ��쳣
//isConnected()֮�ຯ��ֻ���жϱ���״̬
//
//��������10s��ر�����,�ͻ�����15s�������������
//2.�����˿ں�ͨ�Ŷ˿���һ���˿�ô
//�Զ���������,��Ҫ�ͷ�����Э�̺�
//3.��ô���������� ������ping
//4.ֻ�ر�����������������˿�


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
