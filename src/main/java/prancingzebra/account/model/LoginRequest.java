package prancingzebra.account.model;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class LoginRequest {
	private String phoneNumberOrEmail;
	private String password;

	public LoginRequest() {

	}

	public LoginRequest(String phoneNumberOrEmail, String password) {
		this.phoneNumberOrEmail = phoneNumberOrEmail;
		this.password = password;
	}

	public String getPhoneNumberOrEmail() {
		return phoneNumberOrEmail;
	}

	public void setPhoneNumberOrEmail(String phoneNumberOrEmail) {
		this.phoneNumberOrEmail = phoneNumberOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
