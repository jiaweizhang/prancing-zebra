package prancingzebra.model.request;

/**
 * Created by jiaweizhang on 2/15/2017.
 */
public class RegisterVerificationRequest {
	private String verificationCode;
	private String phoneNumber;

	public RegisterVerificationRequest() {

	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
