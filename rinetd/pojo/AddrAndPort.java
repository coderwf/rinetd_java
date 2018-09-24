package rinetd.pojo;

//包装ip和端口
public class AddrAndPort {
    private String  ip;
    private Integer port;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
    
}
