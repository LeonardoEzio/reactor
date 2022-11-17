package leonardo.ezio.personal.basic;


import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @Description : flux示例
 * @Author : LeonardoEzio
 * @Date: 2021-12-14 15:34
 */
public class FluxDemo {

    public static void justDemo(){
        Flux.just("hello", "world").subscribe(System.out::println);
    }

    public static void fromArrayDemo(){
        Flux<String> stringFlux = Flux.fromArray(new String[]{"111", "2222", "3333"});
        stringFlux.subscribe(str -> {
            System.out.println("hello : " + str);
        });
    }

    public static void rangeDemo(){
        Flux.range(2000, 5).subscribe(System.out::println);
    }

    public static void intervalDemo(){
        Flux.interval(Duration.ofSeconds(2), Duration.ofSeconds(1)).subscribe(System.out::println);
    }

    public static void sinkDemo(){
        Flux.generate(sink -> {
            //next方法只能调用一次
            sink.next("leonardo");
            //complete方法得调用，要不一直输出leonardo
            sink.complete();
        }).subscribe(System.out::println);
    }


    public static void sinkDemo2(){
        Flux.generate(() -> 1, (i, sink) -> {
            sink.next(i);
            if (i == 5) {
                sink.complete();
            }
            return ++i;
        }).subscribe(System.out::println);
    }


    public static void createDemo(){
        Flux.create(sink -> {
            sink.next(1);
            sink.next(2);
        }).subscribe(System.out::println);
    }


    public static void main(String[] args) throws Exception{
//        justDemo();
//        fromArrayDemo();
//        rangeDemo();
//        intervalDemo();
//        sinkDemo();
//        sinkDemo2();
        createDemo();
    }
}
