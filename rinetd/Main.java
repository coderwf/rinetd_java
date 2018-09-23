package rinetd;

import rinetd.core.Listener;
import rinetd.pojo.AddrAndPort;

public class Main {
    public static void main(String []args) {
        AddrAndPort addrAndPort = new AddrAndPort();
        addrAndPort.setSoAddr("10.10.1.43");
        addrAndPort.setSoPort(6379);
        addrAndPort.setDeAddr("192.168.199.255");
        addrAndPort.setDePort(9999);
        System.out.println(addrAndPort);
    }//main
    
}//Main
