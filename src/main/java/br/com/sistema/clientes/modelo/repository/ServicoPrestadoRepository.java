package br.com.sistema.clientes.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sistema.clientes.modelo.entity.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer>{

	/**
	 * Query para busca de serviço prestado por nome do cliente e mês
	 */

	@Query(" select	s from ServicoPrestado s join s.cliente c  where upper( c.nome ) like upper ( :nome )" ) // and MONTH( s.data ) : mes")
	List<ServicoPrestado> findByNomeClienteAndMes(@Param("nome") String nome);
			//@Param("mes") Integer mes);

}
