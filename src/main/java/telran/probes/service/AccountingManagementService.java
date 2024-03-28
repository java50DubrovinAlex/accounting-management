package telran.probes.service;

import telran.probes.dto.AccountDto;
import telran.probes.model.Account;

public interface AccountingManagementService {
	AccountDto addAccount(AccountDto account);
	AccountDto removeAccount(String email);
}
