package com.api.consulta.cep.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.consulta.cep.domain.Endereco;
import com.api.consulta.cep.dto.EnderecoDTO;
import com.api.consulta.cep.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public List<EnderecoDTO> buscarTodosEnderecos(){
		final List<Endereco> enderecos = this.enderecoRepository.findAll();
		
		return enderecos.stream().map(end -> new EnderecoDTO(end)).collect(Collectors.toList());
	}

	public Endereco buscarEnderecoPorCep(String cep) {
		return enderecoRepository.findByCep(cep);
	}

	public Endereco inserirEndereco(Endereco endereco) {
		return this.enderecoRepository.save(endereco);
	}

}
