package prancingzebra.model.response;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class LoginResponse {
	private String authToken;

	public LoginResponse(String authToken) {
		this.authToken = authToken;
	}

	public LoginResponse() {

	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
