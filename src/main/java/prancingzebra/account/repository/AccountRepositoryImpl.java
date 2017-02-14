package prancingzebra.account.repository;

import org.springframework.beans.factory.annotation.Autowired;
import prancingzebra.account.model.Account;
import prancingzebra.config.JinqSource;
import prancingzebra.exceptions.RequestValueException;

import java.util.Optional;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

public class AccountRepositoryImpl implements AdditionalAccountQueries {

	@Autowired
	private JinqSource source;

	@Override
	public boolean emailExists(String email) {
		return source.accounts().anyMatch(a -> a.getEmail().equals(email));
	}

	@Override
	public boolean phoneNumberExists(String phoneNumber) {
		return source.accounts().anyMatch(a -> a.getPhoneNumber().equals(phoneNumber));
	}

	@Override
	public Account findByEmail(String email) {
		Optional<Account> result = source.accounts().where(a -> a.getEmail().equals(email))
				.findAny();

		if (result.isPresent()) {
			return result.get();
		}
		throw new RequestValueException("Invalid email");
	}

	@Override
	public Account findByPhoneNumber(String phoneNumber) {
		Optional<Account> result = source.accounts().where(a -> a.getPhoneNumber().equals(phoneNumber))
				.findAny();

		if (result.isPresent()) {
			return result.get();
		}
		throw new RequestValueException("Invalid phone number");
	}
}
