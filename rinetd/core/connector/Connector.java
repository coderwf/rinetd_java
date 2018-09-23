package rinetd.core.connector;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

//维持到目的地址的连接池
//如果长时间不连接尝试回收该连接
//只有第一个请求到达时就维持连接
public class Connector {
    //ip + port 作为 map的key
	private final Map<String,Socket> connPool = new HashMap<>();
	public 
}
