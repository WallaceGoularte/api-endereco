package com.api.consulta.cep.resources;

import java.text.MessageFormat;
import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "api/endereco")
@Api("API para consultar endereços por ceps")
public class EnderecoResource {
	
	@Autowired
	EnderecoService enderecoService;

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation("Buscar todos endereço na base interna")
	public ResponseEntity<List<EnderecoDTO>> buscarTodosEnderecos() {
		return ResponseEntity.ok().body(this.enderecoService.buscarTodosEnderecos());
	}
	
	@RequestMapping(value = "/{cep}", method = RequestMethod.GET)
	@ApiOperation("Buscar endereço na base interna ou externa pelo Cep")
	public ResponseEntity<Endereco> listarJogosPorId(@PathVariable final String cep) {
		Endereco endereco = this.enderecoService.buscarEnderecoPorCep(cep);
		
		if (endereco == null) {
			return ResponseEntity.ok().body(restTemplateForObject(cep));
		}
		
		return ResponseEntity.ok().body(endereco);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation("Cadastrar endereço na base interna")
	public Endereco inserir(@RequestBody Endereco endereco) {
		return this.enderecoService.inserirEndereco(endereco);
	}
	
	@RequestMapping(value = "/{cep}", method = RequestMethod.POST)
	@ApiOperation("Cadastrar endereço utilizando apenas o Cep")
	public Endereco inserir(@RequestBody EnderecoDTO dto) {
		if (dto.getCep() != null && !dto.getCep().isEmpty()) {
			Endereco endereco = restTemplateForObject(dto.getCep());
			return this.enderecoService.inserirEndereco(endereco);
		} else {
			throw new RuntimeException("Cep inválido");
		}
		
	}

	private Endereco restTemplateForObject(final String cep) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(this.getUrlApiCep(cep), Endereco.class);
	}
	
	private String getUrlApiCep(String cep) {
		return MessageFormat.format("https://viacep.com.br/ws/{0}/json/", cep);
	}

}
