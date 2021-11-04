package com.api.consulta.cep;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.consulta.cep.domain.Endereco;
import com.api.consulta.cep.repositories.EnderecoRepository;

@SpringBootApplication
public class EnderecoApplication implements CommandLineRunner {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EnderecoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Endereco end1 = new Endereco(null, "24568920", "Rua Primeiro Endereço", "", "Endereco Um", "Zona Sul", "RJ", "21", "123");
		Endereco end2 = new Endereco(null, "29874920", "Rua Segundo Endereço", "", "Endereco Dois", "Zona Norte", "SP", "11", "321");
		
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

}
