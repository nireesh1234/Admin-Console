package com.mkyong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@PropertySource("classpath:casadmin.properties")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/*@Value( "${cas.admin.username}" )
	private String username;
	
	@Value("${cas.admin.password}")
	private String password;
	
	@Value("${cas.admin.role}")
	private String role;
	*/
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  
	  http.authorizeRequests()
	  	.antMatchers("/admin/**").access("hasRole('ADMIN')")
	  	.and().formLogin()
	  	.loginPage("/login")
	  	/*.loginProcessingUrl("/applogin")*/
	  	.failureUrl("/login?error")
	  	.usernameParameter("ssoId").passwordParameter("password") ; 
	  	//.and().csrf().disable();
	  	//.and().csrf();
	  	//.csrfTokenRepository(csrfTokenRepository());
	  	//.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
/*	private CsrfTokenRepository csrfTokenRepository() 
	{ 
	    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
	    repository.setSessionAttributeName("_csrf");
	    return repository; 
	}
	*/
}
