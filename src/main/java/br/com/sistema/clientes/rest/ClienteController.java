package br.com.sistema.clientes.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sistema.clientes.modelo.entity.Cliente;
import br.com.sistema.clientes.modelo.repository.ClienteRepository;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
//@CrossOrigin("http://localhost:4200") case deseje liberar diretamente na controller. Nao recomendado.
public class ClienteController {

	@Autowired
	private final ClienteRepository repository;

	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return repository.save(cliente);
	}

	@GetMapping("{id}")
	public Cliente acharPorId(@PathVariable Integer id) {
//		String Nome = "Paulo Fegueredo";
//		String Endereco = "Rua Rio Jaguaribe, ";
//		int numero = 389;
//		System.out.printf("%s\n%-25s%d\n%s\n%s",Nome, Endereco, numero, "CEP 82840-330", "Curitiba/PR");
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@GetMapping()
	public List<Cliente> listarClientes() {
		//List<Cliente> clientes = ;repository.findAll()
		return repository.findAll();

	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Integer id) {
		// repository.deleteById(id); // Opção que dá erro se nao localizar o cliente
		repository.findById(id).map(cliente -> {
			repository.delete(cliente);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarCliente(@PathVariable Integer id, @Valid @RequestBody Cliente clienteAtualizado) {
		repository.findById(id).map(cliente -> {
			clienteAtualizado.setId(cliente.getId());
			 repository.save(clienteAtualizado);
			 return clienteAtualizado;
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

	}
}