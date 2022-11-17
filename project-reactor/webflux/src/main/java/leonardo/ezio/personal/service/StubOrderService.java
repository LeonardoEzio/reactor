package leonardo.ezio.personal.service;

import leonardo.ezio.personal.entity.Order;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description : 订单桩服务
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 16:00
 */
@Service
public class StubOrderService {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public Flux<Order> getOrders(){
        return Flux.fromIterable(this.orders.values());
    }

    public Flux<Order> getOrderByIds(final Flux<String> ids){
        return ids.flatMap(id -> Mono.justOrEmpty(this.orders.get(id)));
    }

    public Mono<Order> getOrderById(final String id){
        return Mono.justOrEmpty(this.orders.get(id));
    }

    public Mono<Void> createOrUpdateOrder(final Mono<Order> productMono){
        return productMono.doOnNext(order -> orders.put(order.getId(), order)).thenEmpty(Mono.empty());
    }

    public Mono<Order> deleteOrder(String id){
        return Mono.justOrEmpty(orders.remove(id));
    }
}
