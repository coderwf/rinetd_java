package rinetd.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import rinetd.exceptions.ParseException;

//解析配置文件
//最大转发链路数
//超时
//转发链路
//忽略所有的#开头的注释
public class ConfFileParser {
    private static String[] processRow(String row) {
    	row = row.replaceAll("\\s*", "");
    	if(row.charAt(0) == '#')
    		return null;  //注释
    	//分离出等号前后的值
    	String datas[] = row.split("=");
    	if(datas.length != 2) {
    		throw new ParseException(row,"more or less = ,please keep a = b");
    	}//
    	return datas;
    }//
    
    private static Map<String, String> processConfFile(String path){
    	try {
    		Map<String, String> confMap = new HashMap<>();
        	File file = new File(path);
        	InputStream fileInputStream = new FileInputStream(file);
        	InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        	BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        	String row = null;
        	String datas [];
        	while((row = bufferedReader.readLine()) != null) {
        		datas = processRow(row);
        		if(datas != null) {
        			confMap.put(datas[0], datas[1]);
         		}//if
        	}//while
        	return confMap;
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}//catch
    }//Map
    public static void main(String []args) {
    	String row = "       max-transfer = 600 ";
    	String path  = "C:\\Users\\Dongwei\\Desktop\\java\\RinetdWthJava\\src\\rinetd\\util\\rinet.conf";
    	System.out.println(processConfFile(path));
    }//main
}
