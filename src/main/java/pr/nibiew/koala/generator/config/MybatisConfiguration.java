package pr.nibiew.koala.generator.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author JG.Hannibal
 * @since 2020/8/8 21:34
 */
@Configuration
@EnableTransactionManagement
@MapperScan("pr.nibiew.koala.generator.mapper")
public class MybatisConfiguration implements TransactionManagementConfigurer {
    @Autowired
    private Environment environment;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocationPattern;


    public static final Logger logger = LoggerFactory.getLogger(MybatisConfiguration.class);

    /**
     * 数据源
     *
     * @return
     */
    @Bean
    public DataSource dataSource(){
        logger.info("init Druid Servlet Configuration ");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(environment.getProperty("spring.datasource.url"));
        datasource.setDriverClassName(environment.getProperty("spring.datasource.driverClassName"));
        datasource.setUsername(environment.getProperty("spring.datasource.username"));
        datasource.setPassword(environment.getProperty("spring.datasource.password"));
        datasource.setInitialSize(Integer.valueOf(environment.getProperty("spring.datasource.initial-size")));
        datasource.setMinIdle(Integer.valueOf(environment.getProperty("spring.datasource.min-idle")));
        datasource.setMaxWait(Long.valueOf(environment.getProperty("spring.datasource.max-wait")));
        datasource.setMaxActive(Integer.valueOf(environment.getProperty("spring.datasource.max-active")));
        datasource.setValidationQuery(String.valueOf(environment.getProperty("spring.datasource.validation-query")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(environment.getProperty(
                "spring.datasource.min-evictable-idle-time-millis")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(environment.getProperty(
                "spring.datasource.max-pool-prepared-statement-per-connection-size")));
        return datasource;
    }


    @Bean
    public DataSourceTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * SqlSessionFactoryBean配置
     *
     * @param dataSource
     * @return
     */
    @Bean
    SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocationPattern));

        return sqlSessionFactoryBean;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
    