package prancingzebra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import prancingzebra.global.Resp;
import prancingzebra.model.request.LoginRequest;
import prancingzebra.model.request.RegisterRequest;
import prancingzebra.services.AccountService;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@RestController
@RequestMapping("/api/account")
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "/register",
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
		return Resp.wrap(accountService.register(registerRequest));
	}

	@RequestMapping(value = "/login",
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
		return Resp.wrap(accountService.login(loginRequest));
	}
}
