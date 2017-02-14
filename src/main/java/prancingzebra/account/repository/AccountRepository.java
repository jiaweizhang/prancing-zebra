package prancingzebra.account.repository;

import org.springframework.data.repository.Repository;
import prancingzebra.account.model.Account;

import java.util.List;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

public interface AccountRepository extends Repository<Account, Long>, AdditionalAccountQueries {

	void save(Account account);

	List<Account> findAll();

}
