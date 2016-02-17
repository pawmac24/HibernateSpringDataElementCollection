package com.pm.hibernate;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by pmackiewicz on 2016-01-18.
 */

@Configuration
@EnableJpaRepositories(basePackages = {
    "com.pm.hibernate.repository"
})
@ComponentScan(basePackages = "com.pm.hibernate")
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
public class ApplicationConfiguration {

    private static final String[] ENTITY_PACKAGES = {
            "com.pm.hibernate"
    };
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String JNDI_NAME = "jndi.name";

    private static final String DRIVER_CLASS = "jdbc.driverClassName";
    private static final String DB_URL = "jdbc.url";
    private static final String DB_USER = "jdbc.username";
    private static final String DB_PASSWORD = "jdbc.password";

//    @Bean
//    PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
//        return new PersistenceAnnotationBeanPostProcessor();
//    }

    @Bean
    @Scope("singleton")
    DataSource dataSource(Environment env) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty(DRIVER_CLASS));
        dataSource.setUrl(env.getProperty(DB_URL));
        dataSource.setUsername(env.getProperty(DB_USER));
        dataSource.setPassword(env.getProperty(DB_PASSWORD));
        return dataSource;
    }
//    @Bean
//    @Scope("singleton")
//    DataSource dataSource(Environment env) throws NamingException {
//        JndiObjectFactoryBean dataSourceConfig = new JndiObjectFactoryBean();
//        dataSourceConfig.setJndiName(env.getProperty(JNDI_NAME));
////        dataSourceConfig.setProxyInterface(javax.sql.DataSource.class);
//        dataSourceConfig.setResourceRef(true);
//        dataSourceConfig.afterPropertiesSet();
//
//        return (DataSource) dataSourceConfig.getObject();
//    }

    @Bean(name = "org.dozer.Mapper")
    public DozerBeanMapper dozerBean() {
        List<String> mappingFiles = Arrays.asList(
            "mapper.xml"
        );

        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceUnitName("punit");
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGES);

        Properties jpaProperties = new Properties();

        jpaProperties.put(HIBERNATE_DIALECT, env.getProperty(HIBERNATE_DIALECT));
        //jpaProperties.put(HIBERNATE_HBM2DDL_AUTO, env.getProperty(HIBERNATE_HBM2DDL_AUTO));
        jpaProperties.put(HIBERNATE_SHOW_SQL, env.getProperty(HIBERNATE_SHOW_SQL));
        jpaProperties.put(HIBERNATE_FORMAT_SQL, env.getProperty(HIBERNATE_FORMAT_SQL));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
