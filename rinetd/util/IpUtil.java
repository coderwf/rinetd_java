package rinetd.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import rinetd.exceptions.ParseException;
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
    public static TransferLink parseTransferLink(String row) {
    	//去空格
    	row = row.replaceAll("\\s+","");
    	//去括号
    	row = row.substring(1, row.length() - 1);
    	//去分隔符
    	String [] datas = row.split(",|:");
    	if(datas.length != 4)
    		throw new ParseException(row, "incorrect form ");
    	TransferLink tLink = new TransferLink();
    	tLink.setSoAddr(datas[0]);
    	tLink.setSoPort(Integer.valueOf(datas[1]));
    	tLink.setDeAddr(datas[2]);
    	tLink.setDePort(Integer.valueOf(datas[3]));
    	return tLink;
    }//
    
    public static void main(String []args) {
    	String row = "  [ -2.0.0.0 : 44444 ,  1.1.1.1:9999   ] ";
    	System.out.println(parseTransferLink(row));
    }//main
}//class IpUtil
