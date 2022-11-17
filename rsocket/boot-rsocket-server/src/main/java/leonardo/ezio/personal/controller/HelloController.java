package leonardo.ezio.personal.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 11:38
 */
@Controller
public class HelloController {

    /**
     * @MessageMapping类似于@RequestMapping注解，用来指定WebSocket、RSocket等协议中消息处理的目的地
     * */
    @MessageMapping("hello")
    public Mono<String> hello(String input){
        return Mono.just("Hello : " + input + " from Rsocket server");
    }
}
