package prancingzebra.account.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import prancingzebra.account.model.Account;
import prancingzebra.config.JinqSource;
import prancingzebra.exceptions.RequestValueException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

@Transactional
@Repository
public class AccountRepository {

	@Autowired
	private JinqSource source;

	@PersistenceContext
	private EntityManager em;

	public void save(Account account) {
		em.persist(account);
	}

	public List<Account> findAll() {
		return source.accounts(em).toList();
	}

	public boolean emailExists(String email) {
		return source.accounts(em).anyMatch(a -> a.getEmail().equals(email));
	}

	public boolean phoneNumberExists(String phoneNumber) {
		return source.accounts(em).anyMatch(a -> a.getPhoneNumber().equals(phoneNumber));
	}

	public Account findByEmail(String email) {
		Optional<Account> result = source.accounts(em).where(a -> a.getEmail().equals(email))
				.findAny();

		if (result.isPresent()) {
			return result.get();
		}
		throw new RequestValueException("Invalid email");
	}

	public Account findByPhoneNumber(String phoneNumber) {
		Optional<Account> result = source.accounts(em).where(a -> a.getPhoneNumber().equals(phoneNumber))
				.findAny();

		if (result.isPresent()) {
			return result.get();
		}
		throw new RequestValueException("Invalid phone number");
	}
}
