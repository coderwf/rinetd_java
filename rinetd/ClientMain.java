package rinetd;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
    public static void main(String args[]) throws UnknownHostException, IOException {
    	Socket socket = new Socket("10.11.170.136", 1234);
    	System.out.println(socket.getRemoteSocketAddress());
    	System.out.println(socket.getLocalSocketAddress());
    	System.out.println(socket.getLocalAddress());
    }//main
}//ClientMain
