package rinetd.pojo;

import rinetd.exceptions.ParseException;

public class TransferLink {
    private String  soAddr  = null;
    private Integer soPort  = null;
	private String  deAddr  = null;
    private Integer dePort  = null;
	public String getSoAddr() {
		return soAddr;
	}
	public void setSoAddr(String soAddr) {
		checkAddr(soAddr);
		this.soAddr = soAddr;
	}
	public Integer getSoPort() {
		return soPort;
	}
	public void setSoPort(Integer soPort) {
		checkPort(soPort);
		this.soPort = soPort;
	}
	public String getDeAddr() {
		return deAddr;
	}
	public void setDeAddr(String deAddr) {
		checkAddr(deAddr);
		this.deAddr = deAddr;
	}
	public Integer getDePort() {
		return dePort;
	}
	public void setDePort(Integer dePort) {
		checkPort(dePort);
		this.dePort = dePort;
	}
    
	public String toString() {
		return "["+this.soAddr + ":" + this.soPort +
				","+this.deAddr + ":"+this.dePort + "]";
	}//toString
	
	private void checkPort(Integer port) {
		if(port <= 0 || port >= 65536)
			throw new ParseException(String.valueOf(port), "incorrect port should 1 - 65535");
	}//checkPort
	
	private void checkAddr(String addr) {
		String datas[] = addr.split("\\.");
		if(datas.length != 4)
			throw new ParseException(addr, "incorrect addr should *.*.*.*");
		for(String data:datas) {
			Integer d = Integer.valueOf(data);
			if(d < 0 || d >= 255)
				throw new ParseException(addr, "incorrect addr");
		}///
	}//checkAddr
}
