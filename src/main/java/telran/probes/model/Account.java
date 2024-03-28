package telran.probes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.probes.dto.AccountDto;

@Document(collection="accounts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Id
	String email;
	String hashPassword;
	String roles[];

public Account(AccountDto accountDto) {
	email = accountDto.email();
	hashPassword = accountDto.password();
	roles = accountDto.roles();
}
public AccountDto build() {
	return new AccountDto(email, hashPassword, roles);
}
}