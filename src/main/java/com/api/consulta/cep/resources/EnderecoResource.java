package com.api.consulta.cep.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.consulta.cep.domain.Endereco;
import com.api.consulta.cep.dto.EnderecoDTO;
import com.api.consulta.cep.services.EnderecoService;

@RestController
@RequestMapping(value = "api/endereco")
public class EnderecoResource {
	
	@Autowired
	EnderecoService enderecoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EnderecoDTO>> buscarTodosEnderecos() {
		return ResponseEntity.ok().body(this.enderecoService.buscarTodosEnderecos());
	}
	
	@RequestMapping(value = "/{cep}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> listarJogosPorId(@PathVariable final String cep) {
		return ResponseEntity.ok().body(this.enderecoService.buscarEnderecoPorCep(cep));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Endereco inserir(@RequestBody Endereco endereco) {
		return this.enderecoService.inserirEndereco(endereco);
	}

}
