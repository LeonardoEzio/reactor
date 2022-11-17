package leonardo.ezio.personal.controller;

import leonardo.ezio.personal.entity.CommonResult;
import leonardo.ezio.personal.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description : 用户桩服务
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 17:05
 */
@RestController
@RequestMapping("stubUser")
public class StubUserController {

    private final Map<String, User> orders = new ConcurrentHashMap(){
        {
            put(UUID.randomUUID().toString().substring(0, 10), new User("aaaa"));
            put(UUID.randomUUID().toString().substring(0, 10), new User("bbbb"));
            put(UUID.randomUUID().toString().substring(0, 10), new User("cccc"));
        }

    };

    @GetMapping("all")
    public Flux<CommonResult> getAll(){
        Flux<User> userFlux = Flux.fromIterable(this.orders.values());
        return CommonResult.ok(userFlux);
    }
}
