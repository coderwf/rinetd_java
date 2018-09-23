package rinetd.exceptions;

public class ParseException extends RuntimeException{
	private String value = null;
	private String msg   = null;
    public ParseException(String value,String msg) {
    	this.value = value;
    	this.msg   = msg;
    }//
    public String toString() {
    	return "exception in parse "+value+",error "+msg;
    }//
}
