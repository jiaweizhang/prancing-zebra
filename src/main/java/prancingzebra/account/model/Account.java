package prancingzebra.account.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String passhash;

	public Account(String firstName, String lastName, String phoneNumber, String email, String passhash) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.passhash = passhash;
	}

	protected Account() {
	}

	@Override
	public String toString() {
		return String.format(
				"Account{accountId=%d, firstName='%s', lastName='%s', phoneNumber='%s', email='%s', passhash='%s'}",
				accountId, firstName, lastName, phoneNumber, email, passhash);
	}

	public Long getAccountId() {
		return accountId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPasshash() {
		return passhash;
	}
}

