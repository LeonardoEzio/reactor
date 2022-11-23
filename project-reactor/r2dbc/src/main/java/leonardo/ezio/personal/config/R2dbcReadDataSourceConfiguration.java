package leonardo.ezio.personal.config;

import cn.hutool.core.util.StrUtil;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;

/**
 * 读数据源配置
 * */
@Configuration
@EnableR2dbcRepositories(
        basePackages = {"leonardo.ezio.personal.*.readRepository"},
        entityOperationsRef = "readDatabaseR2dbcEntityOperations"
)
public class R2dbcReadDataSourceConfiguration {

    private final R2dbcReadDataSourceProperties r2dbcReadDataSourceProperties;

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public R2dbcReadDataSourceConfiguration(R2dbcReadDataSourceProperties r2dbcReadDataSourceProperties, R2dbcEntityTemplate r2dbcEntityTemplate) {
        this.r2dbcReadDataSourceProperties = r2dbcReadDataSourceProperties;
        this.r2dbcEntityTemplate = r2dbcEntityTemplate;
    }


    @Bean
    @Qualifier("readDatabaseR2dbcEntityOperations")
    public R2dbcEntityOperations readDatabaseR2dbcEntityOperations(){
        DatabaseClient databaseClient = null;
        if (StrUtil.isNotBlank(r2dbcReadDataSourceProperties.getUrl())
                && StrUtil.isNotBlank(r2dbcReadDataSourceProperties.getUserName())
                && StrUtil.isNotBlank(r2dbcReadDataSourceProperties.getPassword())
                && StrUtil.isNotBlank(r2dbcReadDataSourceProperties.getDatabase())){
            final ConnectionFactoryOptions connectionFactoryOptions = ConnectionFactoryOptions.parse(r2dbcReadDataSourceProperties.getUrl());
            databaseClient = DatabaseClient.create(ConnectionFactories.get(
                    connectionFactoryOptions.mutate()
                            .option(ConnectionFactoryOptions.DRIVER, "mysql")
                            .option(ConnectionFactoryOptions.USER, r2dbcReadDataSourceProperties.getUserName())
                            .option(ConnectionFactoryOptions.PASSWORD, r2dbcReadDataSourceProperties.getPassword())
                            .option(ConnectionFactoryOptions.DATABASE, r2dbcReadDataSourceProperties.getDatabase())
                            .build()));
        }else {
            databaseClient = r2dbcEntityTemplate.getDatabaseClient();
        }
        return new R2dbcEntityTemplate(databaseClient, MySqlDialect.INSTANCE);
    }


}
