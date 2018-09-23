package rinetd.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptinUtil {
    public static String getExceptionMsg(Exception e) {
    	try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String res = null ;
            res = sw.toString();
            sw.close();
            pw.close();
            return res;
        } catch (Exception e2) {
            return "ErrorInfoFromException";
        }
    }//String
}//ExceptionUtil
