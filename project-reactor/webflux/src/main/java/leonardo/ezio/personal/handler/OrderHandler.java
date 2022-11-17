package leonardo.ezio.personal.handler;

import leonardo.ezio.personal.entity.CommonResult;
import leonardo.ezio.personal.entity.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 17:40
 */
@Configuration
public class OrderHandler {


    public Mono<ServerResponse> getOrderById(ServerRequest request){
        String id = request.pathVariable("id");
        CommonResult commonResult = new CommonResult();
        Order order = new Order();
        order.setId(id);
        order.setOrderName("aaaa");
        commonResult.setData(order);
        Mono<CommonResult> just = Mono.just(commonResult);
        return ServerResponse.ok().body(just, CommonResult.class);
    }
}
