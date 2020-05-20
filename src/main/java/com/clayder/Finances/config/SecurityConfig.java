package com.clayder.Finances.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Rotas que não vão possuir segurança
	 */
	private static final String[] PUBLIC_MATCHES = { "/users/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/**
		 * Define as rotas que não vão possuir segurança.
		 */
		http.authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll().anyRequest().authenticated();
	}
}
