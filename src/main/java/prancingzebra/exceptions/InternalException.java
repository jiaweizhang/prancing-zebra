package prancingzebra.exceptions;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class InternalException extends RuntimeException {
	private String message;

	public InternalException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
