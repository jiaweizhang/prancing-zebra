package prancingzebra.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import prancingzebra.exceptions.LoginException;
import prancingzebra.exceptions.RequestValueException;
import prancingzebra.global.StdResp;
import prancingzebra.model.domain.Account;
import prancingzebra.model.request.LoginRequest;
import prancingzebra.model.request.RegisterRequest;
import prancingzebra.model.response.LoginResponse;
import prancingzebra.repository.AccountRepository;
import prancingzebra.utilities.TokenUtility;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@Service
public class AccountService {

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private static EmailValidator emailValidator = EmailValidator.getInstance();
	private AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public StdResp register(RegisterRequest registerRequest) {
		// request validation
		if (registerRequest.getPhoneNumber() == null && registerRequest.getEmail() == null) {
			throw new RequestValueException("Either phone number or email must not be null");
		}
		if (registerRequest.getFirstName() == null || registerRequest.getFirstName().length() == 0) {
			throw new RequestValueException("First name must be filled out");
		}
		if (registerRequest.getLastName() == null || registerRequest.getLastName().length() == 0) {
			throw new RequestValueException("Last name must be filled out");
		}
		if (registerRequest.getPassword() == null || registerRequest.getPassword().length() == 0) {
			throw new RequestValueException("Password must be filled out");
		}
		if (accountRepository.phoneNumberExists(registerRequest.getPhoneNumber())) {
			throw new RequestValueException("Phone number already registered");
		}
		if (accountRepository.emailExists(registerRequest.getEmail())) {
			throw new RequestValueException("Email already registered");
		}

		String passhash = passwordEncoder.encode(registerRequest.getPassword());

		Account account = new Account(registerRequest.getFirstName(), registerRequest.getLastName(),
				registerRequest.getPhoneNumber(), registerRequest.getEmail(), passhash);

		accountRepository.save(account);

		return new StdResp(201, "Account registered");
	}

	public StdResp login(LoginRequest loginRequest) {
		// validate request

		Account account;

		if (loginRequest.getPhoneNumberOrEmail().matches("[0-9]+")) {
			// is a phone number
			account = accountRepository.findByPhoneNumber(loginRequest.getPhoneNumberOrEmail());
		} else if (emailValidator.isValid(loginRequest.getPhoneNumberOrEmail())) {
			// if an email
			account = accountRepository.findByEmail(loginRequest.getPhoneNumberOrEmail());
		} else {
			throw new LoginException("Not valid email or phone number");
		}

		if (passwordEncoder.matches(loginRequest.getPassword(), account.getPasshash())) {
			// generate JWT token
			String token = TokenUtility.generateToken(account.getAccountId());
			LoginResponse responseData = new LoginResponse(token);
			return new StdResp(200, "Successfully logged in", responseData);
		} else {
			throw new LoginException("Invalid password");
		}
	}

}
