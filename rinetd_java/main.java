package rinetd_java;

import java.io.IOException;

public class main {
    public static void main(String []args) {
    	String path = "./bin/rinetd_java/rinetd.conf";
    	if(args.length != 0)
    		path = args[0];
    	try {
			new Rinetd().start(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
