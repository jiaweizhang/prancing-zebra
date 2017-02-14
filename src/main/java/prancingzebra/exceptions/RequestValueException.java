package prancingzebra.exceptions;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class RequestValueException extends RuntimeException {
	private String message;

	public RequestValueException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
