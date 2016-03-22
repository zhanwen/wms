package phoenics.wms.config;


import java.util.Properties;

//import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"phoenics.wms"},
								excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern={"phoenics.wms.controller"}))
@PropertySource("classpath:db.props")
@EnableTransactionManagement
@EnableJpaRepositories("phoenics.wms.repositories")
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
public class AppConfig {
	 @Value("${db.driver}")
      private String driverClassName;
	 @Value("${db.jdbcUrl}")
     private String jdbcUrl;
	 @Value("${db.username}")
     private String username;
	 @Value("${db.password}")
     private String password;
	 
	 //@Autowired
	//private Environment env;

	 @Bean 
	 public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() { 
		 return new PropertySourcesPlaceholderConfigurer(); 
	 } 
	 @Bean
	 public DataSource dataSource(){
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 dataSource.setDriverClassName(driverClassName);
		 dataSource.setUrl(jdbcUrl);
		 dataSource.setUsername(username);
		 dataSource.setPassword(password);
		 return dataSource;
	 }
	 
	 @Bean
	 public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		 LocalContainerEntityManagerFactoryBean $=new LocalContainerEntityManagerFactoryBean();
		 $.setDataSource(dataSource());
		 $.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		 $.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		 $.setPackagesToScan("phoenics.wms.entities");
		 $.setJpaProperties(hibProperties());
		 return $;
	 }
	 private Properties hibProperties() {
		 Properties properties = new Properties();
		 properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		 properties.put("hibernate.show_sql","true");
		 return properties;
	 }
	 @Bean
	 public JpaTransactionManager transactionManager(){
		 JpaTransactionManager transactionManager = new JpaTransactionManager();
		 transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		 return transactionManager;
	 }
	 @Bean
		public CacheManager cacheManager()
		{
			return new ConcurrentMapCacheManager();
		}

	 
	
}
