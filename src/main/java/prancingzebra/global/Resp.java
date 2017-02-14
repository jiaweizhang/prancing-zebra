package prancingzebra.global;

import org.springframework.http.ResponseEntity;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class Resp {
	public static ResponseEntity wrap(StdResp stdResp) {
		stdResp.setUnixTime(System.currentTimeMillis() / 1000);
		return ResponseEntity.status(stdResp.getStatus()).body(stdResp);
	}
}
