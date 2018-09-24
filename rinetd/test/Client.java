package rinetd.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
public class Client {
	
	private static Integer port = 9999;
    public static void main(String args[]) {
    	try {
			try {
				start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }//main
    
    public static void start() throws UnknownHostException, IOException, InterruptedException {
    	Socket socket = new Socket("127.0.0.1", port);
    	System.out.println(socket.isConnected());
    	System.out.println(socket.isClosed());
    	Thread.sleep(20000);
    	OutputStream os = socket.getOutputStream();
    	PrintWriter pw = new PrintWriter(os);
    	pw.write("i love you ");
    	pw.flush();
    	socket.sendUrgentData(0xff);
    }//start
}//Client
