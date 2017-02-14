package prancingzebra.account.repository;

import prancingzebra.account.model.Account;

/**
 * Created by jiaweizhang on 2/14/2017.
 */

public interface AdditionalAccountQueries {

	boolean emailExists(String email);

	boolean phoneNumberExists(String phoneNumber);

	Account findByEmail(String email);

	Account findByPhoneNumber(String phoneNumber);

}
