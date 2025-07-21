//package com.stpl.dimonex.config;
//
//
//
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackages = "com.example")
//public class AppConfig {
//
//    // DataSource bean
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // For H2 DB
//        dataSource.setUrl("jdbc:mysql://localhost:3306/myhiber");
//        dataSource.setUsername("parth");
//        dataSource.setPassword("Parth@123");
//        return dataSource;
//    }
//
//    // SessionFactory bean for Hibernate
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan("com.example.model"); // Your model package
//        factoryBean.setHibernateProperties(hibernateProperties());
//        return factoryBean;
//    }
//
//    // Hibernate properties
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.show_sql", "true");
//        return properties;
//    }
//
//    // Transaction Manager
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }
//}




package com.stpl.dimonex.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.stpl.dimonex")  // Corrected base package name to match your project structure
public class AppConfig {

    // DataSource bean
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dimonex");
        dataSource.setUsername("dhruv");
        dataSource.setPassword("zd465678");
        return dataSource;
    }

    // SessionFactory bean for Hibernate
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.stpl.dimonex.model"); // Your model package
        factoryBean.setHibernateProperties(hibernateProperties());
        return factoryBean;
    }

    // Hibernate properties
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    // HibernateTemplate bean
    @Bean
    public HibernateTemplate hibernateTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(sessionFactory().getObject()); // Attach the sessionFactory
        return hibernateTemplate;
    }

    // Transaction Manager
    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    
    
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // SMTP server (e.g., smtp.gmail.com)
        mailSender.setPort(587); // SMTP port (587 for Gmail)

        mailSender.setUsername(""); // Your email
        mailSender.setPassword(""); // Your email password or App password

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        props.put("mail.debug", "true"); // For debugging

        return mailSender;
    }
}

