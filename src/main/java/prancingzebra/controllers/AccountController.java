package prancingzebra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import prancingzebra.global.Resp;
import prancingzebra.model.request.LoginRequest;
import prancingzebra.model.request.RegisterPasswordRequest;
import prancingzebra.model.request.RegisterPhoneRequest;
import prancingzebra.model.request.RegisterVerificationRequest;
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

	@RequestMapping(value = "/registerPhone",
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	public ResponseEntity registerPhone(@RequestBody RegisterPhoneRequest registerPhoneRequest) {
		return Resp.wrap(accountService.registerPhone(registerPhoneRequest));
	}

	@RequestMapping(value = "/registerVerification",
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	public ResponseEntity registerVerification(@RequestBody RegisterVerificationRequest registerVerificationRequest) {
		return Resp.wrap(accountService.registerVerification(registerVerificationRequest));
	}

	@RequestMapping(value = "/registerPassword",
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	public ResponseEntity registerPhone(@RequestBody RegisterPasswordRequest registerPasswordRequest) {
		return Resp.wrap(accountService.registerPassword(registerPasswordRequest));
	}

	@RequestMapping(value = "/login",
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
		return Resp.wrap(accountService.login(loginRequest));
	}

}
