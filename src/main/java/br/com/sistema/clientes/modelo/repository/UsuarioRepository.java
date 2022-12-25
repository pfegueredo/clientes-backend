package br.com.sistema.clientes.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.clientes.modelo.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
}
