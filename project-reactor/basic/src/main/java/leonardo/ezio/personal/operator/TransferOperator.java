package leonardo.ezio.personal.operator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description : 转换类操作符示例
 * @Author : LeonardoEzio
 * @Date: 2021-12-24 15:55
 */
public class TransferOperator {

    public static void main(String[] args) {
//        bufferDemo();
//        windowDemo();
//        mapDemo();
//        flatMapDemo();
//        filterDemo();
//        firstAndLastDemo();
//        skipAndSkipLast();
//        takeAndTakeLast();
//        whenDemo();
//        mergerDemo();
//        zipDemo();
        zipDemo2();
    }

    /**
    * buffer示例，buffer 操作符的作用相当于把当前流中的元素统一收集到一个集合中，
    * 并把这个集合对象作为新的数据流。使用 buffer 操作符在进行元素收集时，
    * 可以指定集合对象所包含的元素的最大数量。
    **/
    public static void bufferDemo(){
        Flux.range(1, 25).buffer(10).subscribe(System.out::println);
    }

    /**
     * window 操作符的作用类似于 buffer，不同的是 window 操作符是把当前流中的元素收集到另外的 Flux 序列中，而不是一个集合。
     * */
    public static void windowDemo(){
        Flux.range(1,5).window(2).toIterable().forEach(f->{
            f.subscribe(System.out::println);
            System.out.println("============");
        });
    }

    /**
     * map 操作符相当于一种映射操作，它对流中的每个元素应用一个映射函数从而达到转换效果
     * */
    public static void mapDemo(){
        Flux.just(1, 5).map(num -> "hello : " + num).subscribe(System.out::println);
    }

    /**
     * flatMap 操作符执行的也是一种映射操作，但与 map 不同，该操作符会把流中的每个元素映射成一个流而不是一个元素，然后再把得到的所有流中的元素进行合并
     * */
    public static void flatMapDemo(){
        Flux.just(1, 5).flatMap(x -> Mono.just(x * 3)).subscribe(System.out::println);
    }

    /**
     * filter 操作符的含义与普通的过滤器类似，就是对流中包含的元素进行过滤，只留下满足指定过滤条件的元素，
     * */
    public static void filterDemo(){
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
    }

    /**
     * first 操作符的执行效果为返回流中的第一个元素，而 last 操作符的执行效果即返回流中的最后一个元素
     * */
    public static void firstAndLastDemo(){
        Flux<Integer> range = Flux.range(1, 10);
        System.out.println(range.blockFirst());
        range.last().subscribe(System.out::println);
    }

    /**
     * 如果使用 skip 操作符，将会忽略数据流的前 n 个元素。类似的，如果使用 skipLast 操作符，将会忽略流的最后 n 个元素。
     * */
    public static void skipAndSkipLast(){
        Flux<Integer> range = Flux.range(1, 10);
        range.skip(4).subscribe(System.out::println);
        System.out.println("=============================");
        range.skipLast(4).subscribe(System.out::println);
    }

    /**
     * take 系列操作符用来从当前流中提取元素。我们可以按照指定的数量来提取元素，也可以按照指定的时间间隔来提取元素。类似的，takeLast 系列操作符用来从当前流的尾部提取元素。
     * */
    public static void takeAndTakeLast(){
        Flux<Integer> range = Flux.range(1, 10);
        range.take(3).subscribe(System.out::println);
        System.out.println("==============================");
        range.takeLast(3).subscribe(System.out::println);
    }

    /**
     * 不太懂
     * */
    public static Flux<Void> whenDemo(){
        Flux<Integer> range = Flux.range(0, 10);
        return range.flatMap(num -> {
            Mono<String> save = Mono.just("save order : " + num);
            save.subscribe(System.out::println);
            Mono<String> send = Mono.just("send msg : " + num);
            send.subscribe(System.out::println);
            System.out.println("aaaaa");
            return Mono.when(save, save);
        });
    }

    /**
     * 不太懂
     * */
    public static void mergerDemo(){
        Flux<Integer> range = Flux.range(1, 5);
        Flux<Integer> range1 = Flux.range(10, 5);
//        Flux.mergeSequential(range, range1).subscribe(System.out::println);
        Flux.merge(range, range1).subscribe(System.out::println);
    }

    /**
     * zip 操作符的合并规则比较特别，是将当前流中的元素与另外一个流中的元素按照一对一的方式进行合并
     * */
    public static void zipDemo(){
        Flux<Integer> range = Flux.range(1, 5);
        Flux<Integer> range1 = Flux.range(10, 5);
        Flux.zip(range, range1).subscribe(System.out::println);
    }


    public static void zipDemo2(){
        Flux.range(1, 5).zipWith(Flux.range(10, 5), (n1, n2) -> {
            return String.format("%d + %d = %d", n1, n2, n1 + n2);
        }).subscribe(System.out::println);

    }

}
