package prancingzebra;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import prancingzebra.account.AccountService;
import prancingzebra.account.model.Account;
import prancingzebra.account.model.LoginRequest;
import prancingzebra.account.model.LoginResponse;
import prancingzebra.account.model.RegisterRequest;
import prancingzebra.account.repository.AccountRepository;
import prancingzebra.global.StdResp;
import prancingzebra.utilities.TokenUtility;


/**
 * Created by jiaweizhang on 2/14/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class AccountTest {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void registerTest() {
		RegisterRequest registerRequest = new RegisterRequest(
				"1001001000",
				"one.zero@gmail.com",
				"password",
				"1",
				"0"
		);

		StdResp response = accountService.register(registerRequest);
		assert (response.getStatus() == 201);

		assert (accountRepository.findAll().stream().filter(a -> a.getPhoneNumber().equals("1001001000")).count() == 1);
	}

	@Test
	public void loginPhoneNumberTest() {
		RegisterRequest registerRequest = new RegisterRequest(
				"1001001001",
				"one.one@gmail.com",
				"password",
				"1",
				"1"
		);

		LoginRequest loginRequest = new LoginRequest(
				"1001001001",
				"password"
		);

		assert (accountService.register(registerRequest).getStatus() == 201);
		StdResp loginResponse = accountService.login(loginRequest);
		assert (loginResponse.getStatus() == 200);

		long accountId = accountRepository.findAll()
				.stream()
				.filter(a -> a.getPhoneNumber().equals("1001001001"))
				.map(Account::getAccountId)
				.findAny().get();

		LoginResponse data = (LoginResponse) loginResponse.getData();

		assert (TokenUtility.retrieveAccountId(data.getAuthToken()) == accountId);
	}

	@Test
	public void loginEmailTest() {
		RegisterRequest registerRequest = new RegisterRequest(
				"1001001002",
				"one.two@gmail.com",
				"password",
				"1",
				"2"
		);

		LoginRequest loginRequest = new LoginRequest(
				"one.two@gmail.com",
				"password"
		);

		assert (accountService.register(registerRequest).getStatus() == 201);
		StdResp loginResponse = accountService.login(loginRequest);
		assert (loginResponse.getStatus() == 200);

		long accountId = accountRepository.findAll()
				.stream()
				.filter(a -> a.getEmail().equals("one.two@gmail.com"))
				.map(Account::getAccountId)
				.findAny().get();

		LoginResponse data = (LoginResponse) loginResponse.getData();

		assert (TokenUtility.retrieveAccountId(data.getAuthToken()) == accountId);
	}
}
