package prancingzebra.model.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	private String name;
	private String phoneNumber;
	private String passhash;
	private String verificationCode;
	private Timestamp verificationCodeExpiration;
	private Boolean registered;

	public Account(String name, String phoneNumber, String passhash, String verificationCode, Timestamp verificationCodeExpiration, Boolean registered) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.passhash = passhash;
		this.verificationCode = verificationCode;
		this.verificationCodeExpiration = verificationCodeExpiration;
		this.registered = registered;
	}

	protected Account() {
	}

	@Override
	public String toString() {
		return accountId.toString();
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPasshash() {
		return passhash;
	}

	public void setPasshash(String passhash) {
		this.passhash = passhash;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Timestamp getVerificationCodeExpiration() {
		return verificationCodeExpiration;
	}

	public void setVerificationCodeExpiration(Timestamp verificationCodeExpiration) {
		this.verificationCodeExpiration = verificationCodeExpiration;
	}

	public Boolean getRegistered() {
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}
}

