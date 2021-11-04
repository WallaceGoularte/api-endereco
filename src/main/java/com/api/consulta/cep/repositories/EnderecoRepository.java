package com.api.consulta.cep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.consulta.cep.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	@Query("select e from Endereco e where e.cep = ?1")
	Endereco findByCep(String cep);
}
