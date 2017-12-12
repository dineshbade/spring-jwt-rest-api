/**
 * By Dinesh Oct 12, 2017
 */
package com.spring.java.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author User
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
@EnableJpaRepositories("com.spring.java.dao")

public class PersistenceJPAConfig {
	  @Autowired
	  private Environment env;
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.TRUE);

		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.spring.java.model");

	

		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		factory.setJpaProperties(additionalProperties());
		return factory;
	   }
	 
	   @Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
	      dataSource.setUrl(env.getProperty("hibernate.connection.url"));
	      dataSource.setUsername(env.getProperty("hibernate.connection.username"));
	      dataSource.setPassword(env.getProperty("hibernate.connection.password"));
	      return dataSource;
	   }
	 
	   @Bean
	   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(emf);
	 
	      return transactionManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	      return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	   Properties additionalProperties() {
	      Properties properties = new Properties();
	      properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	      properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
	      properties.setProperty("hibernate.current_session_context_class", env.getProperty("hibernate.current_session_context_class"));
	      return properties;
	   }
	}