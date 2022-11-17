package leonardo.ezio.personal.entity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 16:44
 */
public class CommonResult {

    private int code = 200;

    private String msg = "success";

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Mono<CommonResult> ok(Mono<? extends Object> data){
        return data.flatMap(da -> {
            CommonResult commonResult = new CommonResult();
            commonResult.setData(da);
            return Mono.just(commonResult);
        });
    }

    public static Flux<CommonResult> ok(Flux<? extends Object> data){
        CommonResult commonResult = new CommonResult();
        List<Object> datas = new ArrayList<>();
        return data.flatMap(da -> {
            return Mono.just(commonResult);
        });
//        Mono<Order> orderMono = Mono.just(new Order());
//        Mono<Order> orderMono2 = Mono.just(new Order());
//        Mono<User> userMono = Mono.just(new User());
//        Mono.zip(orderMono, userMono, orderMono2).flatMap(tem -> {
//            Order t1 = tem.getT1();
//            User t2 = tem.getT2();
//            Order t3 = tem.getT3();
//        });
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
