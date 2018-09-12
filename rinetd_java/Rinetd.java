package rinetd_java;

import java.io.IOException;

/*
 * 1.解析文件
 * 2.根据返回值开启线程监听ip:port
 * 3.针对这个监听的ip和port将客户端的请求转发给目标服务器并将目标服务器的响应返回给客户端
 */
public class Rinetd {
    public static void start(String path) throws IOException {
    	//
    	TransItem [] items = FileParser.parseFile(path);
    	for(TransItem item:items)
    		new ListenThread(item).start();
    }
}
