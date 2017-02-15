package prancingzebra.model.request;

/**
 * Created by jiaweizhang on 2/15/2017.
 */
public class RegisterPhoneRequest {
	private String phoneNumber;
	private String name;

	public RegisterPhoneRequest() {

	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
