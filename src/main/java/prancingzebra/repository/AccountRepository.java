package prancingzebra.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prancingzebra.config.JinqSource;
import prancingzebra.exceptions.RequestValueException;
import prancingzebra.model.domain.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@Transactional
@Repository
public class AccountRepository {

	private final JinqSource source;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public AccountRepository(JinqSource source) {
		this.source = source;
	}

	public void save(Account account) {
		em.persist(account);
	}

	public List<Account> findAll() {
		return source.accounts(em).toList();
	}

	public boolean accountIsRegistered(String phoneNumber) {
		Optional<Account> result = source.accounts(em).where(a -> a.getPhoneNumber().equals(phoneNumber))
				.findAny();

		return result.map(Account::getRegistered).orElse(false);
	}

	public boolean phoneNumberExists(String phoneNumber) {
		return source.accounts(em).anyMatch(a -> a.getPhoneNumber().equals(phoneNumber));
	}

	public Account findByPhoneNumber(String phoneNumber) {
		Optional<Account> result = source.accounts(em).where(a -> a.getPhoneNumber().equals(phoneNumber))
				.findAny();

		if (result.isPresent()) {
			return result.get();
		}
		throw new RequestValueException("Invalid phone number");
	}

	public void updateVerificationCode(String phoneNumber, String verificationCode, Timestamp expirationTimestamp) {
		if (phoneNumberExists(phoneNumber)) {
			// update
			Account account = findByPhoneNumber(phoneNumber);
			account.setVerificationCode(verificationCode);
			account.setVerificationCodeExpiration(expirationTimestamp);
			// need to persist?
			em.persist(account);
		} else {
			// create new
			Account account = new Account(null, phoneNumber, null, verificationCode, expirationTimestamp, false);
			em.persist(account);
		}
	}

	/**
	 * Updates the passhash for a phone number
	 *
	 * @param phoneNumber Phone number
	 * @param passhash    Hashed password
	 */
	public void updatePassword(String phoneNumber, String passhash) {
		Account account = findByPhoneNumber(phoneNumber);
		account.setPasshash(passhash);
		account.setRegistered(true);
		em.persist(account);
	}

	public void updateVerificationCodeExpiration(String phoneNumber, Timestamp expirationTimestamp) {
		Account account = findByPhoneNumber(phoneNumber);
		account.setVerificationCodeExpiration(expirationTimestamp);
		em.persist(account);
	}
}
