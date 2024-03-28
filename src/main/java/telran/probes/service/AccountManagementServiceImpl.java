package telran.probes.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.DuplicateKeyException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.AccountDto;
import telran.probes.model.Account;
import telran.probes.repo.AccountRepo;
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountManagementServiceImpl implements AccountingManagementService{
	final MongoTemplate mongoTemplate;
	final AccountRepo accountRepo;
	
	@Override
	public AccountDto addAccount(AccountDto account) {
		String id = account.email();
		try {
			mongoTemplate.insert(new Account(account));
		}catch (DuplicateKeyException e) {
			log.debug("Account with id: {} already exists", id);
			throw new IllegalStateException();
		}
		log.debug("Account {} has been added", account);
		return account;	
	}

	@Override
	public AccountDto removeAccount(String email) {
		Query query =  new Query(Criteria.where("id").is(email));
		Account account = mongoTemplate.findAndRemove(query, Account.class);
		if(account == null) {
			log.error("Account whit id {} not found", email);
			throw new IllegalStateException();
		}
		log.debug("account with id: {} has been removed", email);
		account.setHashPassword("${DEFAULT_PASSWORD}");
		return account.build();
	}

}
