package rinetd_java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//解析配置文件
//0.0.0.0 8000 127.0.0.0 4000
public class FileParser {
    public static TransItem[] parseFile(String path) throws IOException {
    	List<TransItem> itemList = new ArrayList<>();
    	FileInputStream fileInputStream       = null;
    	InputStreamReader inputStreamReader   = null;
    	BufferedReader bufferedReader         = null;
    	int line = 0;
    	try {
    		fileInputStream     = new FileInputStream(path);
    		inputStreamReader   = new InputStreamReader(fileInputStream);
    		bufferedReader      = new BufferedReader(inputStreamReader);
    		String string;
    		while((string = bufferedReader.readLine()) != null ) {
    			if(string.length() == 0) {
    				line ++ ;
    				continue;
    			}
    			TransItem item = parseItem(string,line);
    			System.out.println(item);
    			itemList.add(item);
    			line ++ ;
    		}
    	}finally {
			try {
				fileInputStream.close();
				inputStreamReader.close();
				bufferedReader.close();
			}catch (Exception e) {
				e.printStackTrace();
			}//try-catch
		}//try-finally
    	return null;
    }//
    
    private static TransItem parseItem(String sitem,int line) {
    	String datas[] = sitem.split("\\s+");
    	if(datas.length != 4)
    		throw new RuntimeException("conf error at line "+ line );
    	String fromIp    = datas[0];
    	String fromPort  = datas[1];
    	checkPortAndIp(fromPort, fromIp);
    	String toIp      = datas[2];
    	String toPort    = datas[3];
    	checkPortAndIp(toPort, toIp);
    	TransItem item = new TransItem();
    	item.setFromIp(fromIp);
    	item.setFromPort(fromPort);
    	item.setToIp(toIp);
    	item.setToPort(toPort);
    	return item;
    }
    private static void checkPortAndIp(String port,String ip) {
    	if(Integer.valueOf(port) <=0 || Integer.valueOf(port)> 65535)
    		throw new RuntimeException("un supported port "+port);
    	String ips[] = ip.split("\\.");
    	//System.out.println(ips.length);
    	if(ips.length != 4)
    		throw new RuntimeException("un supported ip "+ip);
    	for(int i=0;i<4;i++) {
    		if(Integer.valueOf(ips[i]) < 0 || Integer.valueOf(ips[i]) >= 255)
    			throw new RuntimeException("un supported ip "+ip);
    	}//for
    }
    public static void main(String []args) throws IOException {
    	parseFile("./bin/rinetd_java/rinetd.conf");
    }//main
}
