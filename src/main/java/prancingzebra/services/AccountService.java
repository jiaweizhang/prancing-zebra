package prancingzebra.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import prancingzebra.exceptions.RequestValueException;
import prancingzebra.global.StdResp;
import prancingzebra.model.domain.Account;
import prancingzebra.model.request.RegisterPasswordRequest;
import prancingzebra.model.request.RegisterPhoneRequest;
import prancingzebra.model.request.RegisterVerificationRequest;
import prancingzebra.repository.AccountRepository;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@Service
public class AccountService {

	private static final Logger log = LoggerFactory.getLogger(AccountService.class);


	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private static EmailValidator emailValidator = EmailValidator.getInstance();
	private static Random random = new Random();
	private AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public StdResp registerPhone(RegisterPhoneRequest registerPhoneRequest) {
		// validate phone number
		if (!registerPhoneRequest.getPhoneNumber().matches("[0-9]+") ||
				registerPhoneRequest.getPhoneNumber().length() != 10) {
			throw new RequestValueException("Not valid phone number");
		}

		// make sure user is not already registered
		if (accountRepository.accountIsRegistered(registerPhoneRequest.getPhoneNumber())) {
			throw new RequestValueException("Phone number is already registered");
		}

		// generate random number between 0 and 999999 (both inclusive)
		int randomInt = random.nextInt(1000000);

		// generate expiration timestamp
		Timestamp expirationTimestamp = new Timestamp(System.currentTimeMillis() + 60000);


		// store code
		accountRepository.updateVerificationCode(registerPhoneRequest.getPhoneNumber(),
				Integer.toString(randomInt), expirationTimestamp);

		// send SMS with the code to the phone number
		// TODO
		log.debug("Sending SMS to phone number " + registerPhoneRequest.getPhoneNumber() + " with code " + randomInt);

		return new StdResp(200, "SMS sent");
	}


	public StdResp registerVerification(RegisterVerificationRequest registerVerificationRequest) {
		// validate request
		// check that phone number is not registered and has a verification code
		if (accountRepository.accountIsRegistered(registerVerificationRequest.getPhoneNumber())) {
			throw new RequestValueException("Phone number is already registered");
		}

		if (!accountRepository.phoneNumberExists(registerVerificationRequest.getPhoneNumber())) {
			throw new RequestValueException("Please enter phone number first");
		}

		Account account = accountRepository.findByPhoneNumber(registerVerificationRequest.getPhoneNumber());
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		if (currentTimestamp.after(account.getVerificationCodeExpiration())) {
			throw new RequestValueException("Verification code is expired");
		}

		// verify verification code
		if (!registerVerificationRequest.getVerificationCode().equals(account.getVerificationCode())) {
			throw new RequestValueException("Invalid verification code");
		}

		// if this code is reached, phone number is verified
		// extend verification code expiration by 1 minute
		Timestamp extendedTimestamp = new Timestamp(System.currentTimeMillis() + 60000);
		accountRepository.updateVerificationCodeExpiration(registerVerificationRequest.getPhoneNumber(),
				extendedTimestamp);

		return new StdResp(200, "Successfully validated verification code");
	}

	public StdResp registerPassword(RegisterPasswordRequest registerPasswordRequest) {
		// make sure phone number exists
		if (!accountRepository.phoneNumberExists(registerPasswordRequest.getPhoneNumber())) {
			throw new RequestValueException("Please enter phone number first");
		}
		// make sure phone number is not yet registered
		if (accountRepository.accountIsRegistered(registerPasswordRequest.getPhoneNumber())) {
			throw new RequestValueException("Phone number is already registered");
		}

		// make sure verification code is not expired
		Account account = accountRepository.findByPhoneNumber(registerPasswordRequest.getPhoneNumber());
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
		if (currentTimestamp.after(account.getVerificationCodeExpiration())) {
			throw new RequestValueException("Verification code is expired");
		}

		// validate verification code
		if (!registerPasswordRequest.getVerificationCode().equals(account.getVerificationCode())) {
			throw new RequestValueException("Invalid verification code");
		}

		// hash password and update
		String passhash = passwordEncoder.encode(registerPasswordRequest.getPassword());
		accountRepository.updatePassword(registerPasswordRequest.getPhoneNumber(), passhash);

		return new StdResp(200, "Successfully registered");
	}
}
