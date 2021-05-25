package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Account;
import application.hibernate.repos.AccountRepository;

public class AccountServiceImpl implements AccountService {
	AccountRepository accountRepository = new AccountRepository();

	@Override
	public Account saveAccount(Account account, Long personId) {
		return accountRepository.save(account, personId);
	}

	@Override
	public Account updateAccount(Account account) {
		return accountRepository.update(account);
	}

	@Override
	public void deleteAccountById(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Account getAccount(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public Account getRichestAccount() {
		return accountRepository.findRichest();
	}

	@Override
	public List<Account> getAllOverdraftAccounts() {
		return accountRepository.findAllOverdraft();
	}
}
