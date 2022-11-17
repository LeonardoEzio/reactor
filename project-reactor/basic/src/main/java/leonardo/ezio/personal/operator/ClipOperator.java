package leonardo.ezio.personal.operator;

import reactor.core.publisher.Flux;

/**
 * @Description : 裁剪类操作符示例
 * @Author : LeonardoEzio
 * @Date: 2022-01-05 10:39
 */
public class ClipOperator {

    public static void main(String[] args) {
//        takeUntilDemo();
//        takeWhileDemo();
//        bufferUntilDemo();
//        bufferWhileDemo();
//        skipUntilDemo();
//        skipWhileDemo();
//        anyDemo();
//        allDemo();
//        concatDemo();
//        reduceDemo();
        reduceWithDemo();
    }

    /**
     * takeUntil 操作符的基本用法是 takeUntil (Predicate<? super T> predicate)，
     * 其中 Predicate 代表一种断言条件，该操作符将从数据流中提取元素直到断言条件返回 true
     * */
    private static void takeUntilDemo(){
        Flux.range(1, 100).takeUntil(i -> i == 10).subscribe(System.out::println);
    }

    /**
     * 与 takeUntil 不同的是，takeWhile 会在 continuePredicate 条件返回 true 时才进行元素的提取。
     *
     * 起始元素要满足表达式，要不会提前结束
     * */
    private static void takeWhileDemo(){
        Flux.range(20, 100).takeWhile(i -> i >= 20 && i < 30).subscribe(System.out::println);
    }


    private static void bufferUntilDemo(){
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
    }


    private static void bufferWhileDemo(){
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }


    private static void skipUntilDemo(){
        Flux.range(1, 10).skipUntil(i -> i > 5).subscribe(System.out::println);
    }


    /**
     * skipWhile 操作符的基本用法是 skipWhile (Predicate<? super T> continuePredicate)，
     * 当 continuePredicate 返回 true 时才进行元素的丢弃。
     *
     * 大于号不生效？？？？？  起始元素要满足条件，否则流会提前结束
     * */
    private static void skipWhileDemo(){
//        Flux.range(1, 10).skipWhile(i -> i > 5).subscribe(System.out::println);
        Flux.range(5, 5).skipWhile(i -> i >= 5 && i <= 7).subscribe(System.out::println);
    }


    /**
     * any 操作符用于检查是否至少有一个元素具有所指定的属性
     * */
    private static void anyDemo(){
        Flux.just(3, 5, 7, 9, 11, 16)
                .any(num -> num % 2 == 0)
                .subscribe(existed -> System.out.println(existed));
    }


    /**
     * all 操作符，用来检查流中元素是否都满足同一属性
     * */
    private static void allDemo(){
        Flux.just("abc", "ela", "ade", "pqa", "kang")
                .all(str -> str.contains("a"))
                .subscribe(allContains -> System.out.println(allContains));
    }


    /**
     * concat 操作符用来合并来自不同 Flux 的数据。
     * 与上一讲中所介绍的 merge 操作符不同，这种合并采用的是顺序的方式，
     * */
    private static void concatDemo(){
        Flux.concat(
                Flux.just(1, 3),
                Flux.just(2, 4),
                Flux.just(6, 9)
        ).subscribe(System.out::println);
    }

    /**
     * reduce 操作符对来自 Flux 序列中的所有元素进行累积操作并得到一个 Mono 序列，该 Mono 序列中包含了最终的计算结果。
     * */
    private static void reduceDemo(){
        Flux.range(1, 10)
                .reduce((a, b) -> a + b)
                .subscribe(System.out::println);
    }

    /**
     * reduceWith 操作符，用来在 reduce 操作时指定一个初始值。
     * */
    private static void reduceWithDemo(){
        Flux.range(1, 10)
                .reduceWith(() -> 5, (a, b) -> a + b)
                .subscribe(System.out::println);
    }
}
