package br.com.sistema.clientes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/*
 * Arquivo de configuração para segurança com Oauth2
 * Resources são as nossas APIs.
 * Os usuários são dividos em Roles.
 */


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/api/usuarios").permitAll()
				.antMatchers("/api/clientes/**", "/api/servicos-prestados").authenticated()
				.anyRequest().denyAll();

				//.antMatchers("/api/clientes/**").hasAnyRole("USER", "ADMIN")

	}

}
