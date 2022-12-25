package br.com.sistema.clientes.config;


import br.com.sistema.clientes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableWebSecurity
//@EnableResourceServer
//@EnableAuthorizationServer //Cria um client_id e um client-secret
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests((authz) -> authz
//                .anyRequest().authenticated()
//            )
//            .httpBasic(withDefaults());
//        return http.build();
//    }
	/*
	 * Arquivo de configuração para segurança com Oauth2
	 *
	 * No console do ambiente de desenvolvimento, é gerado uma senha de acesso no formato
	 * abaixo para acesso pela página de login:
	 *
	 * usuário: user (default)
	 * senha: 54c5a55e-0085-47c7-9e48-9247a2906500 (exemplo de senha)
	 *
	 * -- Gerar o access_token do tipo Bearer:
	 * Endereço criado pelo oauth: http://localhost:8080/oauth/token
	 * Via Postman, usa o Authorizarion / Basic Auth, preenchendo:
	 * user: coloca o client-id
	 * senha: coloca o client-secret.
	 *
	 * Na aba Headers, preenche o Authorization com Basic e a chave em base64.
	 *
	 * Na aba Body, marca a opção x-www-formencoded, preenchendo, Key e value:
	 * username: user
	 * password: senha gerada no console
	 * grant_type: password
	 *
	 * Depois é possivel fazer uma requisição usando o token Bearer
	 *
	 */

	@Autowired
	private UsuarioService usuarioService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// O AuthenticationManagerBuilder é um objeto que configura um AutenticationManager.
//		// AutenticationManager é uma classe que faz o gerenciamento de autenticação.
//
//		//Criar um usuário em memória (caso não tivesse um banco de dados)
//		auth.
//			inMemoryAuthentication()
//				.withUser("admin")
//				.password("admin")
//				.roles("USER");

		auth
				.userDetailsService(usuarioService)
				.passwordEncoder(passwordEncoder());

	}


	@Override
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		// Faz o gerenciamento de usuários.
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// HttpSecurity serve para Autorizar URL, contole de sessão, CORS.
		http
			.csrf().disable() //usado em aplicação Web. Por isso desativar.
			.cors() // Habilita o CORS.
		.and()
			//forma de gerenciamento de sessão. Desabilita a criação de sessões.
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); //Sem criptografia
	}

}
