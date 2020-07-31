package com.atli.provider.config;

/**
 * @author: LI
 * @Date: 2020/5/20 13:44
 * @Description: 使用seata进行数据源代理
 */
//@Configuration
//public class DataSourceProxyConfig {
//    @Value("${mybatis.mapperLocations}")
//    private String mapperLocations;
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource druidDatasource() {
//        return new DruidDataSource();
//    }
//
//    @Bean
//    public DataSourceProxy datasourceproxy(DataSource dataSource) {
//        return new DataSourceProxy(dataSource);
//    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
//        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
//        return sqlSessionFactoryBean.getObject();
//    }
//}
