package phoenics.wms.config;

//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/*@Autowired
	private DataSource dataSource;*/
	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Bean(name="myAuthenticationManager")
	   @Override
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	       return super.authenticationManagerBean();
	   }
	@Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception {
		/*
        registry
        .inMemoryAuthentication()
        .withUser("siva")
          .password("siva")
          .roles("USER")
          .and()
        .withUser("admin")
          .password("admin")
          .roles("ADMIN","USER");
        */
        
        //registry.jdbcAuthentication().dataSource(dataSource);
		registry.userDetailsService(myUserDetailsService);
	}
	 @Override
	  public void configure(WebSecurity web) throws Exception {
	      web.ignoring().antMatchers("/p/**","/s/**","/index.html");
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	    .csrf().disable()
	    .authorizeRequests()
	        .antMatchers("/home","/home/form**","/register","/logout").permitAll()
	        .antMatchers("/admin","/admin/**").hasRole(Roles.ADMIN.name())
	        .antMatchers("/self","/self/**").hasRole(Roles.USER.name())
	        .anyRequest().authenticated();/*
	       .and()
	    .formLogin()
	        .loginPage("/p/login.html")
	       // .loginProcessingUrl("/login")
	        .defaultSuccessUrl("/p/main.html")
	        .failureUrl("/p/login-error.html")
	        .permitAll();*/
	  }
//<form-login login-page="/login.jsp" authentication-failure-url="/login_fail.do" default-target-url="/login_success.do"/>  
}
