package br.com.sistema.clientes.rest;

import br.com.sistema.clientes.exception.UsuarioCadastradoException;
import br.com.sistema.clientes.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.sistema.clientes.modelo.entity.Usuario;
import br.com.sistema.clientes.modelo.repository.UsuarioRepository;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService service;

	@Autowired
	private final UsuarioRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody @Valid Usuario usuario) {
		try {
			service.salvar(usuario);
		}
		catch (UsuarioCadastradoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	@GetMapping
	public List<Usuario> listarUsuarios() {
		List<Usuario> listaUsuarios = new ArrayList<>();
		listaUsuarios = repository.findAll();
		return listaUsuarios;
	}

}
