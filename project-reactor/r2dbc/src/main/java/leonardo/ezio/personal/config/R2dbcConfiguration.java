package leonardo.ezio.personal.config;

import cn.hutool.core.util.IdUtil;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import leonardo.ezio.personal.domain.Entity;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;

import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.mapping.R2dbcMappingContext;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.Parameter;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 主数据源配置
 */
@Configuration
@EnableR2dbcRepositories
public class R2dbcConfiguration {

    private final R2dbcProperties r2dbcProperties;

    public R2dbcConfiguration(R2dbcProperties r2dbcProperties) {
        this.r2dbcProperties = r2dbcProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public R2dbcMappingContext r2dbcMappingContext(ObjectProvider<NamingStrategy> namingStrategy, R2dbcCustomConversions r2dbcCustomConversions) {
        R2dbcMappingContext mappingContext = new R2dbcMappingContext(namingStrategy.getIfAvailable(() -> NamingStrategy.INSTANCE));
        mappingContext.setSimpleTypeHolder(r2dbcCustomConversions.getSimpleTypeHolder());
        mappingContext.setForceQuote(true);
        return mappingContext;
    }

    @Bean
    @Primary
    public ConnectionFactory connectionFactory() {
        final ConnectionFactoryOptions connectionFactoryOptions = ConnectionFactoryOptions.parse(r2dbcProperties.getUrl());
        return ConnectionFactories.get(
                connectionFactoryOptions.mutate()
                        .option(ConnectionFactoryOptions.DRIVER, "mysql")
                        .option(ConnectionFactoryOptions.USER, r2dbcProperties.getUsername())
                        .option(ConnectionFactoryOptions.PASSWORD, r2dbcProperties.getPassword())
                        .option(Option.valueOf("allowLoadLocalInfile"), true)
                        .build()
        );
    }


    @Bean
    @Primary
    public R2dbcEntityOperations r2dbcEntityOperations(ConnectionFactory connectionFactory){
        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);
        return new R2dbcEntityTemplate(databaseClient, MySqlDialect.INSTANCE);
    }


    @Bean(destroyMethod = "dispose")
    @Primary
    public ConnectionPool connectionPool(R2dbcProperties properties,ConnectionFactory connectionFactory){
        R2dbcProperties.Pool pool = properties.getPool();
        PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
        ConnectionPoolConfiguration.Builder builder = ConnectionPoolConfiguration.builder(connectionFactory);
        propertyMapper.from(pool.getMaxIdleTime()).to(builder::maxIdleTime);
        propertyMapper.from(pool.getMaxLifeTime()).to(builder::maxLifeTime);
        propertyMapper.from(pool.getMaxAcquireTime()).to(builder::maxAcquireTime);
        propertyMapper.from(pool.getMaxCreateConnectionTime()).to(builder::maxCreateConnectionTime);
        propertyMapper.from(pool.getInitialSize()).to(builder::initialSize);
        propertyMapper.from(pool.getMaxSize()).to(builder::maxSize);
        propertyMapper.from(pool.getValidationQuery()).whenHasText().to(builder::validationQuery);
        propertyMapper.from(pool.getValidationDepth()).to(builder::validationDepth);
        return new ConnectionPool(builder.build());
    }


    /**
     * 使用雪花算法自动生成id进行保存
     *
     */
    @Bean
    public BeforeSaveCallback<Entity> beforeSaveCallback(){
        return (entity, mutableAggregateChange, table) -> {
            if (Objects.isNull(entity.getId())) {
                final long snowflakeNextId = IdUtil.getSnowflakeNextId();
                mutableAggregateChange.append("`id`", Parameter.from(snowflakeNextId));
                entity.setId(snowflakeNextId);
            }
            return Mono.just(entity);
        };
    }

}
