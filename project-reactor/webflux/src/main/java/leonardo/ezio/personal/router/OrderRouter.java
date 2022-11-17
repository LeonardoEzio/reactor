package leonardo.ezio.personal.router;

import leonardo.ezio.personal.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 17:46
 */
@Configuration
public class OrderRouter {

    @Bean
    public RouterFunction<ServerResponse> routerOrder(OrderHandler orderHandler){
        return RouterFunctions.route(
                RequestPredicates.GET("/funcOrder/{id}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                orderHandler::getOrderById);
    }
}
