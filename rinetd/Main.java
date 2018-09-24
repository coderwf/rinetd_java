package rinetd;
import rinetd.pojo.TransferLink;
public class Main {
    public static void main(String []args) {
        TransferLink addrAndPort = new TransferLink();
        addrAndPort.setSoAddr("10.10.1.43");
        addrAndPort.setSoPort(6379);
        addrAndPort.setDeAddr("192.168.199.255");
        addrAndPort.setDePort(9999);
        System.out.println(addrAndPort);
    }//main
    
}//Main
