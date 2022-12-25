package br.com.sistema.clientes;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.context.annotation.Bean;

@SpringBootApplication() //(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories //("br.com.sistema.clientes.modelo.repository")
public class ClientesApplication {

	/*
	 * @Bean public CommandLineRunner run( @Autowired ClienteRepository repository)
	 * { return args -> { Cliente cliente =
	 * Cliente.builder().cpf("00000000000").nome("fulano").build();
	 * repository.save(cliente); }; }
	 */

	// A variável application.Name recebe o valor do application.Name do application.properties
	//@Value("${application.name}")
	//private String applicationName;

	// Endpoint chamado hello funcionando dentro do contexto sistema-cliente
	//@GetMapping("/hello")
	//public String helloWorld() {
	//	return applicationName;
	//}

	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

}
