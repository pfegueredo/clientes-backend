package br.com.sistema.clientes.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.clientes.modelo.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
