package prancingzebra.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import prancingzebra.exceptions.InternalException;
import prancingzebra.exceptions.LoginException;
import prancingzebra.exceptions.RequestValueException;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(RequestValueException.class)
	public ResponseEntity handleRequestValueException(RequestValueException e) {
		log.error(e.getMessage());
		return Resp.wrap(new StdResp(400, e.getMessage()));
	}

	@ExceptionHandler(LoginException.class)
	public ResponseEntity handleLoginException(LoginException e) {
		log.error(e.getMessage());
		return Resp.wrap(new StdResp(400, e.getMessage()));
	}

	@ExceptionHandler(InternalException.class)
	public ResponseEntity handleLoginException(InternalException e) {
		log.error(e.getMessage());
		return Resp.wrap(new StdResp(500, "Internal server error"));
	}

	/*
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		log.error(e.getMessage());
		return Resp.wrap(new StdResp(500, "Internal server error"));
	}
	*/
}