package leonardo.ezio.personal.service;

import leonardo.ezio.personal.entity.Account;
import leonardo.ezio.personal.repository.AccountReactiveMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 17:07
 */
@Service
public class AccountService {

    @Autowired
    private AccountReactiveMongoRepository accountRepository;

    public Mono<Account> save(Account account){
        return accountRepository.save(account);
    }

    public Mono<Account> findOne(String id){
        return accountRepository.findById(id).log("find one account by id");
    }

}
