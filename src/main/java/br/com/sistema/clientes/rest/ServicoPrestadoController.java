package br.com.sistema.clientes.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.sistema.clientes.modelo.entity.Cliente;
import br.com.sistema.clientes.modelo.entity.ServicoPrestado;
import br.com.sistema.clientes.modelo.repository.ClienteRepository;
import br.com.sistema.clientes.modelo.repository.ServicoPrestadoRepository;
import br.com.sistema.clientes.rest.dto.ServicoPrestadoDTO;
import br.com.sistema.clientes.util.BigDecimalConverter;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
//@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class ServicoPrestadoController {

	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository servicoPrestadoRepository;
	private final BigDecimalConverter bigDecimalConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();

		Cliente cliente = clienteRepository.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente"));

		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco() ));
		return servicoPrestadoRepository.save(servicoPrestado);
	}

	@GetMapping
	public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false, defaultValue="") String nome)
									//	@RequestParam(value = "mes", required = false) Integer mes)
							{
								return servicoPrestadoRepository.findByNomeClienteAndMes("%"+nome+"%");
							}

}
