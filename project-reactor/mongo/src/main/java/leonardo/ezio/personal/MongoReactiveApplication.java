package leonardo.ezio.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-20 16:43
 */
@SpringBootApplication
/** 默认情况下我们一般不需要在 Spring Boot 启动类中手工添加 @EnableReactiveMongoRepositories 注解。
 *  因为当添加 spring-boot-starter-data-mongodb-reactive 组件到 classpath 时，
 *  MongoReactiveRepositoriesAutoConfiguration 配置类会自动创建与 MongoDB 交互的核心类*/
//@EnableReactiveMongoRepositories
public class MongoReactiveApplication{

    public static void main(String[] args) {
        SpringApplication.run(MongoReactiveApplication.class, args);
    }

}
