package leonardo.ezio.personal.controller;

import leonardo.ezio.personal.entity.CommonResult;
import leonardo.ezio.personal.entity.Order;
import leonardo.ezio.personal.service.StubOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description : order桩服务
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 16:08
 */
@RestController
@RequestMapping("stubOrder")
public class StubOrderController {

    @Autowired
    private StubOrderService stubOrderService;

    @GetMapping("all")
    public Flux<Order> getAll(){
        return stubOrderService.getOrders();
    }

    @GetMapping("/{id}")
    public Mono<Order> getById(@PathVariable String id) {
        return stubOrderService.getOrderById(id);
    }

    @GetMapping("get/{id}")
    public Mono<CommonResult> getOrderById(@PathVariable String id) {
        Mono<Order> orderMono = stubOrderService.getOrderById(id);
        return CommonResult.ok(orderMono);
    }

    @PostMapping("create")
    public Mono<Void> createOrder(@RequestBody final Mono<Order> order){
        return stubOrderService.createOrUpdateOrder(order);
    }

    @DeleteMapping("/{id}")
    public Mono<Order> deleteOrder(@PathVariable String id){
        return stubOrderService.deleteOrder(id);
    }
}
