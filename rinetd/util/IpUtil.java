package rinetd.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import rinetd.pojo.TransferLink;

public class IpUtil {
    public static List<String> getIpList(){
    	List<String> ipList = new ArrayList<>();
    	Enumeration<NetworkInterface> networkInterfaces = null;
		try {
			String ip = null;
			Enumeration<InetAddress> inetAddresses = null;
			NetworkInterface networkInterface = null;
			networkInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress inetAddress = null;
			while(networkInterfaces.hasMoreElements()) {
				networkInterface = networkInterfaces.nextElement();
				inetAddresses = networkInterface.getInetAddresses();
				while(inetAddresses.hasMoreElements()) {
					inetAddress = inetAddresses.nextElement();
					if(inetAddress != null && inetAddress instanceof Inet4Address) {
						ip = inetAddress.getHostAddress();
						ipList.add(ip);
					}//
				}//while
			}//while
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	return ipList;
    }//getIpList
    
    //从[host:port,host:port]解析出host和端口
    public static TransferLink parseAddrAndPort(String row) {
    	row = row.substring(1, row.length() - 1);
    	String [] datas = row.split(",|:");
    	System.out.println(datas.length);
    	return null;
    }//
    
    public static void main(String []args) {
    	parseAddrAndPort("[ 0.0.0.0 : 8888 ,  1.1.1.1:9999   ]");
    	
    }//main
}//class IpUtil
