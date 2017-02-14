package prancingzebra;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import prancingzebra.account.model.LoginRequest;
import prancingzebra.account.model.RegisterRequest;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AccountIntegrationTest {

	private final String urlPrefix = "http://localhost:8080/";
	private final Gson gson = new Gson();

	@Test
	public void registerAccount() throws UnirestException, JSONException {
		RegisterRequest registerRequest = new RegisterRequest(
				"1234567890",
				"a1234567890@gmail.com",
				"password",
				"A",
				"B"
		);

		HttpResponse<JsonNode> response = register(registerRequest);
		assert (response.getStatus() == 201);
	}

	@Test
	public void loginWithPhoneNumber() throws UnirestException {
		RegisterRequest registerRequest = new RegisterRequest(
				"2345678901",
				"a2345678901@gmail.com",
				"password",
				"B",
				"C"
		);
		assert (register(registerRequest).getStatus() == 201);

		LoginRequest loginRequest = new LoginRequest(
				"2345678901",
				"password"
		);

		HttpResponse<JsonNode> response = login(loginRequest);
		assert (response.getStatus() == 200);
	}

	@Test
	public void loginWithEmail() throws UnirestException {
		RegisterRequest registerRequest = new RegisterRequest(
				"3456789012",
				"a3456789012@gmail.com",
				"password",
				"C",
				"D"
		);
		assert (register(registerRequest).getStatus() == 201);

		LoginRequest loginRequest = new LoginRequest(
				"a3456789012@gmail.com",
				"password"
		);

		HttpResponse<JsonNode> response = login(loginRequest);
		assert (response.getStatus() == 200);
	}

	private HttpResponse<JsonNode> register(RegisterRequest registerRequest) throws UnirestException {
		return Unirest
				.post(urlPrefix + "api/account/register")
				.header("Content-type", "application/json")
				.body(gson.toJson(registerRequest, RegisterRequest.class))
				.asJson();
	}

	private HttpResponse<JsonNode> login(LoginRequest loginRequest) throws UnirestException {
		return Unirest
				.post(urlPrefix + "api/account/login")
				.header("Content-type", "application/json")
				.body(gson.toJson(loginRequest, LoginRequest.class))
				.asJson();
	}
}
