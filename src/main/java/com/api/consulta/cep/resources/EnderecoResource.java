package com.api.consulta.cep.resources;

import java.text.MessageFormat;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
		Endereco endereco = this.enderecoService.buscarEnderecoPorCep(cep);
		
		if (endereco.equals(null)) {
			RestTemplate restTemplate = new RestTemplate();

			return ResponseEntity.ok().body(restTemplate.getForObject(this.getUrlApiCep(cep),	Endereco.class));
		}
		
		
		return ResponseEntity.ok().body(endereco);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Endereco inserir(@RequestBody Endereco endereco) {
		return this.enderecoService.inserirEndereco(endereco);
	}
	
	@RequestMapping(value = "/{cep}", method = RequestMethod.POST)
	public Endereco inserir(@RequestBody EnderecoDTO dto) {
		if (!dto.getCep().equals(null) && !dto.getCep().isEmpty()) {
			RestTemplate restTemplate = new RestTemplate();
			
			final Endereco endereco = restTemplate.getForObject(this.getUrlApiCep(dto.getCep()), Endereco.class);
			return this.enderecoService.inserirEndereco(endereco);
		} else {
			throw new RuntimeException("Cep inválido");
		}
		
	}

	private String getUrlApiCep(String cep) {
		return MessageFormat.format("https://viacep.com.br/ws/{0}/json/", cep);
	}

}
