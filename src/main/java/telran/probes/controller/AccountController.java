package telran.probes.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.probes.dto.AccountDto;
import telran.probes.service.AccountingManagementService;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
	final AccountingManagementService accountManagementService;
	@PostMapping
	AccountDto addAccount(@RequestBody @Valid AccountDto account) {
		log.debug("adding account: {}", account);
		return accountManagementService.addAccount(account);
	}
	@DeleteMapping("/{email}")
	AccountDto removeAccount(String email) {
		log.debug(email);
		return accountManagementService.removeAccount(email);	
	}
}
