package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Account;

public interface AccountService {
	Account saveAccount(Account account, Long personId);

	Account updateAccount(Account account);

	void deleteAccountById(Long id);

	Account getAccount(Long id);

	List<Account> getAllAccounts();
}
