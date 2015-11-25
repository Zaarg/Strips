package org.no_ip.stieflo.daos;

import javax.sql.DataSource;

import org.no_ip.stieflo.entities.Auteur;
import org.no_ip.stieflo.valueobjects.Plaats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan
@EnableJpaRepositories
public class CreateDAOBeans {
	
	@Autowired
	private DataSource dataSource; 
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan(Auteur.class.getPackage().getName(), Plaats.class.getPackage().getName());
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		factory.setJpaVendorAdapter(adapter);
		factory.getJpaPropertyMap().put("hibernate.format_sql", true);
		factory.getJpaPropertyMap().put("hibernate.use_sql_comments", true);
		return factory;
	} 
	
	@Bean
	JpaTransactionManager transactionManager() {   
		return new JpaTransactionManager(entityManagerFactory().getObject()); 
	}  

	@Bean
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslator() {
	  return new PersistenceExceptionTranslationPostProcessor();
	} 
} 