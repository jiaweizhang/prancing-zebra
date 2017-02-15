package prancingzebra.model.request;

/**
 * Created by jiaweizhang on 2/15/2017.
 */
public class RegisterPasswordRequest {
	private String password;
	private String phoneNumber;
	private String verificationCode;

	public RegisterPasswordRequest() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
}
