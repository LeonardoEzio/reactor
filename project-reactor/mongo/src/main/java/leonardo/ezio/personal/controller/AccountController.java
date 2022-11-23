package leonardo.ezio.personal.controller;

import leonardo.ezio.personal.entity.Account;
import leonardo.ezio.personal.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 17:12
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{accountId}")
    public Mono<Account> getAccountById(@PathVariable("accountId") String accountId) {

        Mono<Account> account = accountService.findOne(accountId);
        return account;
    }

    @PostMapping(value = "/")
    public Mono<Account> addAccount(@RequestBody Account account) {
        return accountService.save(account);
    }
}
