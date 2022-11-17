package leonardo.ezio.personal.controller;

import org.reactivestreams.Publisher;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 14:29
 */
@RestController
public class TestController {

    private final RSocketRequester rSocketRequester;

    public TestController(RSocketRequester rSocketRequester){
        this.rSocketRequester = rSocketRequester;
    }

    /**调用服务端 hello 端点*/
    @GetMapping("test/{msg}")
    public Publisher<String> test(@PathVariable String msg){
        return rSocketRequester.route("hello")
                .data(msg)
                .retrieveMono(String.class);
    }

}
