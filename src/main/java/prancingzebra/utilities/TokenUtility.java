package prancingzebra.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Properties;

/**
 * Created by jiaweizhang on 2/14/2017.
 */
public class TokenUtility {
	public static String generateToken(long accountId) {
		String secretKey = getSecretKey();

		return Jwts.builder().setSubject(Long.toString(accountId))
				.setExpiration(new Date(System.currentTimeMillis() + 30L * 24L * 3600L * 1000L))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}

	public static Claims retrieveClaims(String jwt) {
		String secretKey = getSecretKey();

		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
	}

	public static long retrieveAccountId(String jwt) {
		return Long.parseLong(retrieveClaims(jwt).getSubject());
	}

	private static String getSecretKey() {
		Properties p = PropertiesLoader.loadPropertiesFromPackage("security.properties");
		return p.getProperty("secretKey");
	}
}
