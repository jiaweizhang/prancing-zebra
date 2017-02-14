package prancingzebra.global;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class StdResp {
	private int status;
	private long unixTime;
	private String message;
	private Object data;

	public StdResp(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public StdResp(int status, String message) {
		this(status, message, null);
	}

	public int getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}

	public void setUnixTime(long unixTime) {
		this.unixTime = unixTime;
	}
}
