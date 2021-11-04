package com.api.consulta.cep.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.consulta.cep.dto.EnderecoDTO;

@Entity(name = "Endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="cep", nullable = false)
	private String cep;
	
	@Column(name="logradouro", nullable = false)
	private String logradouro;

	@Column(name="complemento", nullable = true)
	private String complemento;

	@Column(name="bairro", nullable = false)
	private String bairro;
	
	@Column(name="localidade", nullable = true)
	private String localidade;

	@Column(name="uf", nullable = false)
	private String uf;
	
	@Column(name="ddd", nullable = true)
	private String ddd;

	@Column(name="ibge", nullable = false)
	private String ibge;

	public Endereco() {
		super();
	}

	public Endereco(Integer id, String cep, String logradouro, String complemento, String bairro, String localidade,
			String uf, String ddd, String ibge) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ddd = ddd;
		this.ibge = ibge;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static void novo (EnderecoDTO dto) {
		Endereco endereco = new Endereco();
		endereco.setCep(dto.getCep());
		endereco.setLogradouro(dto.getLogradouro());
		endereco.setComplemento(dto.getComplemento());
		endereco.setBairro(dto.getBairro());
		endereco.setLocalidade(dto.getLocalidade());
		endereco.setUf(dto.getUf());
		endereco.setDdd(dto.getDdd());
		endereco.setIbge(dto.getIbge());
	}
	
}
