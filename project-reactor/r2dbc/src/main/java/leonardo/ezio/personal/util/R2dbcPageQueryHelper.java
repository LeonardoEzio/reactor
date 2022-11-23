package leonardo.ezio.personal.util;

import leonardo.ezio.personal.common.PageData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;


@Component
public class R2dbcPageQueryHelper {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public R2dbcPageQueryHelper(@Lazy @Qualifier("readDatabaseR2dbcEntityOperations") R2dbcEntityTemplate r2dbcEntityTemplate){
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }

    /**
     * 进行分页查询
     * @param criteria 查询条件
     * @param pageable 分页信息
     * @param clz 查询的实体
     * @param <T> 对应实体类
     * */
    public <T> Mono<PageData<T>>  selectPage(Criteria criteria, Pageable pageable, Class<T> clz){
        Mono<List<T>> list = r2dbcEntityTemplate.select(clz)
                .matching(Query.query(criteria).with(pageable))
                .all()
                .collectList();
        Mono<Long> count = r2dbcEntityTemplate.select(clz)
                .matching(Query.query(criteria))
                .count();
        return Mono.zip(list, count)
                .map(tuple -> PageData.of(pageable.getPageNumber() + 1, pageable.getPageSize(), tuple.getT2(), tuple.getT1()));
    }


    /**
     * 进行分页查询并排序
     * @param criteria 查询条件
     * @param pageable 分页信息
     * @param sort 排序信息
     * @param clz 查询的实体
     * @param <T> 对应实体类
     * */
    public <T> Mono<PageData<T>> selectPage(Criteria criteria, Pageable pageable, Sort sort, Class<T> clz){
        Mono<List<T>> list = r2dbcEntityTemplate.select(clz)
                .matching(Query.query(criteria).with(pageable).sort(sort))
                .all()
                .collectList();
        Mono<Long> count = r2dbcEntityTemplate.select(clz)
                .matching(Query.query(criteria))
                .count();
        return Mono.zip(list, count)
                .map(tuple -> PageData.of(pageable.getPageNumber()+1, pageable.getPageSize(), tuple.getT2(), tuple.getT1()));
    }


    /**
     * 进行分页查询，并将查询的实体类列表，转换为列表Mono
     * @param criteria 查询条件
     * @param pageable 分页信息
     * @param clz 查询的实体
     * @param function 实体类列表转换为视图列表Mono的方法
     * @param <T> 对应实体类 入参
     * @param <V> 对应视图类 出参
     * */
    public <T,V> Mono<PageData<V>> selectPageAndConvert(Criteria criteria, Pageable pageable, Class<T> clz, Function<List<T>,Mono<List<V>>> function){
        Mono<List<V>> list = r2dbcEntityTemplate.select(clz)
                .matching(Query.query(criteria).with(pageable))
                .all()
                .collectList()
                .flatMap(function);

        Mono<Long> count = r2dbcEntityTemplate.select(clz)
                .matching(Query.query(criteria))
                .count();

        return Mono.zip(list, count)
                .map(tuple -> PageData.of(pageable.getPageNumber() + 1, pageable.getPageSize(), tuple.getT2(), tuple.getT1()));
    }


    /**
     * 进行分页查询，并排序，并将查询的实体类列表，转换为列表Mono
     * @param criteria 查询条件
     * @param pageable 分页信息
     * @param clz 查询的实体
     * @param function 实体类列表转换为视图列表Mono的方法
     * @param <T> 对应实体类 入参
     * @param <V> 对应视图类 出参
     * */
    public <T,V> Mono<PageData<V>> selectPageAndConvert(Criteria criteria, Pageable pageable, Sort sort, Class<T> clz, Function<List<T>,Mono<List<V>>> function){
        Mono<List<V>> list = r2dbcEntityTemplate.select(clz)
                .matching(Query.query(criteria).with(pageable).sort(sort))
                .all()
                .collectList()
                .flatMap(function);

        Mono<Long> count = r2dbcEntityTemplate.select(clz)
                .matching(Query.query(criteria))
                .count();

        return Mono.zip(list, count)
                .map(tuple -> PageData.of(pageable.getPageNumber() + 1, pageable.getPageSize(), tuple.getT2(), tuple.getT1()));
    }



}
