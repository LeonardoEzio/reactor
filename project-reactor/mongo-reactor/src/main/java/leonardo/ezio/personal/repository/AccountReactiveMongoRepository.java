package leonardo.ezio.personal.repository;

import leonardo.ezio.personal.entity.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 16:52
 */
@Repository
public interface AccountReactiveMongoRepository extends ReactiveMongoRepository<Account,String> {

}
