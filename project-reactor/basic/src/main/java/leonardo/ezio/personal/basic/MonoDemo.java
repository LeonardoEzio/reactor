package leonardo.ezio.personal.basic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @Description : mono示例
 * @Author : LeonardoEzio
 * @Date: 2021-12-14 16:25
 */
public class MonoDemo {

    public static Mono getMono(){
        return Mono.just("1");
    }

    public static void justOrEmptyDemo(){
        Mono.justOrEmpty(Optional.of("leonardo")).subscribe(System.out::println);
    }

    public static void createDemo(){
        Mono.create(monoSink -> {
            monoSink.success("success");
        }).subscribe(System.out::println);
    }

    public static void errorDemo(){
        //onErrorReturn("ezio") 创建流异常时返回默认
        Mono.just("leonardo").concatWith(Mono.error(new RuntimeException())).onErrorReturn("ezio").subscribe(System.out::println);
    }

    public static void errorDemo2(){
        //subscribe error处理
        Mono.just("leonardo").concatWith(Mono.error(new RuntimeException())).subscribe(System.out::println,System.err::println);
    }

    public static void lambdaDemo(){
        Flux.just("leonardo", "ezio").subscribe(System.out::println, System.out::println, ()->{
            System.err.println("Complete");
        });
    }

    public static void main(String[] args) {
//        justOrEmptyDemo();
//        createDemo();
//        errorDemo();
//        errorDemo2();
        lambdaDemo();
    }

//    //订阅流的最简单方法，忽略所有消息通知
//    subscribe();
// 
//    //对每个来自 onNext 通知的值调用 dataConsumer，但不处理 onError 和 onComplete 通知
//    subscribe(Consumer<T> dataConsumer);
// 
//    //在前一个重载方法的基础上添加对 onError 通知的处理
//    subscribe(Consumer<T> dataConsumer, Consumer<Throwable> errorConsumer);
// 
//    //在前一个重载方法的基础上添加对 onComplete 通知的处理
//    subscribe(Consumer<T> dataConsumer, Consumer<Throwable> errorConsumer,
//              Runnable completeConsumer);
// 
//    //这种重载方法允许通过请求足够数量的数据来控制订阅过程
//    subscribe(Consumer<T> dataConsumer, Consumer<Throwable> errorConsumer,
//              Runnable completeConsumer, Consumer<Subscription> subscriptionConsumer);
// 
//    //订阅序列的最通用方式，可以为我们的 Subscriber 实现提供所需的任意行为
//    subscribe(Subscriber<T> subscriber);
}
