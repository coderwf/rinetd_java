package rinetd.pojo;

public class AddrAndPort {
    private String  soAddr  = null;
    private Integer soPort  = null;
	private String  deAddr  = null;
    private Integer dePort  = null;
	public String getSoAddr() {
		return soAddr;
	}
	public void setSoAddr(String soAddr) {
		this.soAddr = soAddr;
	}
	public Integer getSoPort() {
		return soPort;
	}
	public void setSoPort(Integer soPort) {
		this.soPort = soPort;
	}
	public String getDeAddr() {
		return deAddr;
	}
	public void setDeAddr(String deAddr) {
		this.deAddr = deAddr;
	}
	public Integer getDePort() {
		return dePort;
	}
	public void setDePort(Integer dePort) {
		this.dePort = dePort;
	}
    
	public String toString() {
		return "["+this.soAddr + ":" + this.soPort +
				","+this.deAddr + ":"+this.dePort + "]";
	}//toString
}
