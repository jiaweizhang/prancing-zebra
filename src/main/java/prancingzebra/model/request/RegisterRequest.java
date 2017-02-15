package prancingzebra.model.request;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class RegisterRequest {
	private String phoneNumber;
	private String email;
	private String password;
	private String firstName;
	private String lastName;

	public RegisterRequest() {

	}

	public RegisterRequest(String phoneNumber, String email, String password, String firstName, String lastName) {
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
