package prancingzebra.exceptions;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class LoginException extends RuntimeException {
	private String message;

	public LoginException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
