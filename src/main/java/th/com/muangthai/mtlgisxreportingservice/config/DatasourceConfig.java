package th.com.muangthai.mtlgisxreportingservice.config;

import jakarta.persistence.SharedCacheMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import th.com.muangthai.mtlgisxreportingservice.service.jasper.SimpleReportExporter;
import th.com.muangthai.mtlgisxreportingservice.service.jasper.SimpleReportFiller;

import javax.sql.DataSource;
import java.util.Properties;

//@Configuration
//@PropertySource("classpath:application.properties")
//@EnableJpaRepositories("th.com.muangthai.mtlgisxreportingservice.model.repo")
public class DatasourceConfig {

//    @Resource
//    private Environment env;
//    @Bean
//    public SimpleReportFiller getReportDS(){
//        return new SimpleReportFiller(getDataSource());
//    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/mtl_gisx_poc_db");
//        dataSource.setUrl("jdbc:postgresql://localhost:5433/mtl_gisx_poc_db");
//        dataSource.setUrl("jdbc:postgresql://127.29.0.2:16/mtl_gisx_poc_db");
//        dataSource.setUsername("dynamic_rest");
        dataSource.setUsername("postgres");
        dataSource.setPassword("passw0rd");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        /*
         String datasourceJdniName = env.getProperty(PROPERTY_NAME_DATABASE_DATASOURCE);

        if(eformFlagMode == eformFlagMode_DEV){
            em.setDataSource(dataSource());
        }else{
            em.setDataSource(dataSourceWithJndi(datasourceJdniName));
        }*/
        em.setDataSource(getDataSource());

        em.setPackagesToScan(new String[] { "th.com.muangthai.mtlgisxreportingservice.model.entity" });
        em.setSharedCacheMode(SharedCacheMode.NONE);
        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        em.setJpaVendorAdapter(vendorAdapter);

        em.setJpaProperties(additionalProperties());
        return em;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("eclipselink.weaving", "false");
        properties.put("eclipselink.ddl-generation", "none");
        properties.put("eclipselink.query-results-cache", "false");
        properties.put("hibernate.exclude-unlisted-classes", "false");
        return properties;
    }


    @Bean
    public SimpleReportFiller reportFiller() {
        return new SimpleReportFiller(getDataSource());
    }

    @Bean
    public SimpleReportExporter reportExporter() {
        return new SimpleReportExporter();
    }
}
