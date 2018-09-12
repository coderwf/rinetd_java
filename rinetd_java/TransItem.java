package rinetd_java;

import javax.xml.soap.Text;

//一个转发条目
public class TransItem {
    private String fromIp   = null;
    private String toIp     = null;
    private String fromPort = null;
    private String toPort   = null;
	public String getFromIp() {
		return fromIp;
	}
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}
	public String getToIp() {
		return toIp;
	}
	public void setToIp(String toIp) {
		this.toIp = toIp;
	}
	public String getFromPort() {
		return fromPort;
	}
	public void setFromPort(String fromPort) {
		this.fromPort = fromPort;
	}
	public String getToPort() {
		return toPort;
	}
	public void setToPort(String toPort) {
		this.toPort = toPort;
	}
    
	public String toString() {
		String text = "[ ";
		text += this.fromIp + ":" + fromPort + " to " + this.toIp+":"+this.toPort + " ]";
		return text;
	}
}
