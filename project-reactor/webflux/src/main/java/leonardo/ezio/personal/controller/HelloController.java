package leonardo.ezio.personal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 14:49
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public Mono<String> hello(){
        return Mono.just("Hello WebFlux !");
    }
}
